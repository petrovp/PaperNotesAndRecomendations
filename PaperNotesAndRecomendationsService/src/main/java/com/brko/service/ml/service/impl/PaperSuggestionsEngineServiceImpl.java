package com.brko.service.ml.service.impl;

import com.brko.service.ml.models.PaperSummaryAndNoteSimilarity;
import com.brko.service.ml.models.PaperSummaryScoreForUser;
import com.brko.service.ml.service.PaperSuggestionsEngineService;
import com.brko.service.ml.service.TextComparatorService;
import com.brko.service.persistance.datamodel.Note;
import com.brko.service.persistance.datamodel.PaperSummary;
import com.brko.service.persistance.datamodel.User;
import com.brko.service.persistance.repository.PaperSummaryRepository;
import com.brko.service.services.notes.NotesService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ppetrov on 11/12/2016.
 */
@Service
public class PaperSuggestionsEngineServiceImpl implements PaperSuggestionsEngineService {

    final Logger logger = Logger.getLogger(this.getClass().getName());

    private final static int HOW_MANY_PAPERS_TO_INCLUDE_IN_WHOLE_SCORE = 10;

    @Autowired
    private PaperSummaryRepository paperSummaryRepository;

    @Autowired
    private NotesService notesService;

    @Autowired
    private TextComparatorService textComparatorService;

    public List<PaperSummary> computeSuggestedPapers(User user, List<PaperSummary> allPaperSummaries) {

        User userForSearch = new User();
        userForSearch.setId(user.getId());
        userForSearch.setAuthority(user.getAuthority());
        userForSearch.setEmail(user.getEmail());
        userForSearch.setPassword(user.getPassword());
        userForSearch.setName(user.getName());
        userForSearch.setFirstName(user.getFirstName());
        userForSearch.setLastName(user.getLastName());

        List<Note> notesByUser = notesService.getNotesByUserEmail(user.getEmail());

        Map<PaperSummary, PaperSummaryScoreForUser> paperSummaryWithScoresMap = Maps.newHashMap();
        for (PaperSummary paperSummary : allPaperSummaries) {
            PaperSummaryScoreForUser paperSummaryScoreForUser = new PaperSummaryScoreForUser(paperSummary, user, 0.0);
            paperSummaryWithScoresMap.put(paperSummary, paperSummaryScoreForUser);
        }

        for (Note note : notesByUser) {
            List<PaperSummaryAndNoteSimilarity> similarities = Lists.newArrayList();
            for (PaperSummary paperSummary : allPaperSummaries) {
                try {
                    double similarity =
                            textComparatorService.computeSimilarity(paperSummary.getAbstractContent(), note.getText());

                    PaperSummaryAndNoteSimilarity paperSummaryAndNoteSimilarity =
                            new PaperSummaryAndNoteSimilarity(paperSummary, note, similarity);
                    similarities.add(paperSummaryAndNoteSimilarity);
                } catch (RuntimeException e) {
                    logger.error(e);
                }
            }

            for (PaperSummaryAndNoteSimilarity similarity : similarities) {
                PaperSummaryScoreForUser paperSummaryScoreForUser =
                        paperSummaryWithScoresMap.get(similarity.getPaperSummary());
                double newScore = similarity.getSimilarity() + paperSummaryScoreForUser.getScore();
                paperSummaryScoreForUser.setScore(newScore);
            }
        }

        List<PaperSummaryScoreForUser> scoresForUser = Lists.newArrayList(paperSummaryWithScoresMap.values());
        Collections.sort(scoresForUser);

        List<PaperSummary> rankedSummaries = Lists.newArrayList();
        for (PaperSummaryScoreForUser paperSummaryScoreForUser : scoresForUser) {
            rankedSummaries.add(paperSummaryScoreForUser.getPaperSummary());
        }

        return rankedSummaries.subList(0, 100);
    }
}
package com.brko.service.ml.service.impl;

import com.brko.service.ml.models.PaperSummaryAndNoteDistance;
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
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        List<Note> notesByUser = notesService.getNotesByUserEmail(user.getEmail());

        Map<PaperSummary, PaperSummaryScoreForUser> paperSummaryWithScoresMap = Maps.newHashMap();
        for (PaperSummary paperSummary : allPaperSummaries) {
            PaperSummaryScoreForUser paperSummaryScoreForUser = new PaperSummaryScoreForUser(paperSummary, user, 0.0);
            paperSummaryWithScoresMap.put(paperSummary, paperSummaryScoreForUser);
        }

        for (Note note : notesByUser) {
            List<PaperSummaryAndNoteDistance> distances = Lists.newArrayList();
            for (PaperSummary paperSummary : allPaperSummaries) {
                try {
                    double distance =
                            textComparatorService.computeDistance(paperSummary.getAbstractContent(), note.getText());

                    PaperSummaryAndNoteDistance paperSummaryAndNoteDistance =
                            new PaperSummaryAndNoteDistance(paperSummary, note, distance);
                    distances.add(paperSummaryAndNoteDistance);
                } catch (RuntimeException e) {
                    logger.error(e);
                }
            }

            double noteImportance = calculateNoteImportance(note);

            normalizeDistances(distances);
            for (PaperSummaryAndNoteDistance distance : distances) {
                PaperSummaryScoreForUser paperSummaryScoreForUser =
                        paperSummaryWithScoresMap.get(distance.getPaperSummary());
                double newScore = distance.getSimilarity() * noteImportance + paperSummaryScoreForUser.getScore();
                paperSummaryScoreForUser.setScore(newScore);
            }
        }

        List<PaperSummaryScoreForUser> scoresForUser = Lists.newArrayList(paperSummaryWithScoresMap.values());
        Collections.sort(scoresForUser);

        return scoresForUser
                .stream()
                .map(PaperSummaryScoreForUser::getPaperSummary)
                .collect(Collectors.toList())
                .subList(0, 100);
    }

    private double calculateNoteImportance(Note note) {
        Date createdOn = note.getCreatedOn();

        if (createdOn.after(DateUtils.addMonths(new Date(), -1))) { // first group
            return 1.0;
        }
        if (createdOn.after(DateUtils.addMonths(new Date(), -6))) { // first group
            return 0.8;
        }
        return 0.5;
    }


    private void normalizeDistances(List<PaperSummaryAndNoteDistance> distances) {
        double maxDistance =
                distances
                .stream()
                .max((o1, o2) -> Double.compare(o1.getDistance(), o2.getDistance()))
                .get()
                .getDistance();

        for (PaperSummaryAndNoteDistance distance : distances) {
            distance.setDistance(distance.getDistance() / maxDistance);
        }
    }
}

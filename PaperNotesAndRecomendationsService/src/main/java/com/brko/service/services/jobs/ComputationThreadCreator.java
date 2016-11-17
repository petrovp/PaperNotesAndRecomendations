package com.brko.service.services.jobs;

import com.brko.service.ml.service.PaperSuggestionsEngineService;
import com.brko.service.persistance.datamodel.PaperSummary;
import com.brko.service.persistance.datamodel.User;
import com.brko.service.persistance.repository.PaperSummaryRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by ppetrov on 11/17/2016.
 */
@Component
public class ComputationThreadCreator {

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    private PaperSuggestionsEngineService paperSuggestionsEngineService;

    @Autowired
    private PaperSummaryRepository paperSummaryRepository;

    public void runSuggestionsForUserOnAddedNote(User user) {
        List<PaperSummary> summaries = paperSummaryRepository.findAll();

        Date executionTime = DateUtils.addSeconds(new Date(), 30);
        taskScheduler.schedule(createRunnableForComputation(user, summaries), executionTime);
    }

    private Runnable createRunnableForComputation(final User user, final List<PaperSummary> summaries) {
        return new Runnable() {
            @Override
            public void run() {
                paperSuggestionsEngineService.computeSuggestedPapers(user, summaries);
            }
        };
    }
}

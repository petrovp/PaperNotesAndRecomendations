package com.brko.service.services.papers;

import com.brko.service.ml.service.PaperSuggestionsComputerService;
import com.brko.service.persistance.datamodel.PaperSummary;
import com.brko.service.persistance.datamodel.User;
import com.brko.service.persistance.repository.PaperSummaryRepository;
import com.brko.service.persistance.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ppetrov on 11/13/2016.
 */
@Component
public class ComputeSuggestedPapersScheduledJob {

    final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaperSummaryRepository paperSummaryRepository;

    @Autowired
    private PaperSuggestionsComputerService paperSuggestionsComputerService;

    @Scheduled(cron = "0 0 0 * * *")
    public void preComputeSuggestionsForAllUsers() {

        logger.info("Start with computing of suggestions for all users");

        List<User> allUsers = userRepository.findAll();
        List<PaperSummary> allSummaries = paperSummaryRepository.findAll();

        for (User user : allUsers) {
            try {
                List<PaperSummary> suggestedPapers = paperSuggestionsComputerService.computeSuggestedPapers(user, allSummaries);
                user.setSuggestedPapers(suggestedPapers);
            } catch (RuntimeException e) {
                logger.error(e);
            }
        }

        userRepository.save(allUsers);

        logger.info("Precomputing finished successfully.");
    }
}

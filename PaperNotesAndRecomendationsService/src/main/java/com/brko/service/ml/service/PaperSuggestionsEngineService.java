package com.brko.service.ml.service;

import com.brko.service.persistance.datamodel.PaperSummary;
import com.brko.service.persistance.datamodel.User;

import java.util.List;

/**
 * Created by ppetrov on 11/12/2016.
 */
public interface PaperSuggestionsEngineService {

    /**
     * Compute score for papers on specific user and returns the best 100.
     * @return the list from 50 best scored papers
     */
    List<PaperSummary> computeSuggestedPapers(User user, List<PaperSummary> allPaperSummaries);
}

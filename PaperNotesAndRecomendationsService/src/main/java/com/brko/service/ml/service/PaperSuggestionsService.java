package com.brko.service.ml.service;

import com.brko.service.persistance.datamodel.PaperSummary;
import com.brko.service.persistance.datamodel.User;

import java.util.List;

/**
 * Created by ppetrov on 11/12/2016.
 */
public interface PaperSuggestionsService {

    List<PaperSummary> computeSuggestedPapers(User user);
}

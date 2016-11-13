package com.brko.service.services.papers;

import com.brko.service.persistance.datamodel.PaperSummary;
import com.brko.service.persistance.datamodel.User;

import java.util.List;

/**
 * Created by ppetrov on 11/13/2016.
 */
public interface PaperSummaryService {

    List<PaperSummary> getSuggestedPapersForUser(User user);
}

package com.brko.service.services.papers.impl;

import com.brko.service.persistance.datamodel.PaperSummary;
import com.brko.service.persistance.datamodel.User;
import com.brko.service.persistance.repository.PaperSummaryRepository;
import com.brko.service.services.papers.PaperSummaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ppetrov on 11/13/2016.
 */
public class PaperSummaryServiceImpl implements PaperSummaryService {

    @Autowired
    private PaperSummaryRepository paperSummaryRepository;

    @Override
    public List<PaperSummary> getSuggestedPapersForUser(User user) {
        return null;
    }
}

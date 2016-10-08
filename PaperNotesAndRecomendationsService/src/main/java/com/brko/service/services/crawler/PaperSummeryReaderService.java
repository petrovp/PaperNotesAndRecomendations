package com.brko.service.services.crawler;

import com.brko.service.persistance.datamodel.PaperSummary;
import com.brko.service.services.crawler.exceptions.ReadPaperSummaryFromHtmlException;
import org.jsoup.HttpStatusException;

/**
 * Service for reading paper summery from its link.
 *
 * Created by Petre on 9/3/2016.
 */
public interface PaperSummeryReaderService {

    PaperSummary readAbstractFromArxivHtml(String url) throws ReadPaperSummaryFromHtmlException, HttpStatusException;
}

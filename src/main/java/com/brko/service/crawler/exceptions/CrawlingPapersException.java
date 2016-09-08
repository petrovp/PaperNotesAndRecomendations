package com.brko.service.crawler.exceptions;

/**
 * Created by Petre on 9/4/2016.
 */
public class CrawlingPapersException extends Throwable {

    public CrawlingPapersException(String message, ReadPaperSummaryFromHtmlException e) {
        super(message, e);
    }
}

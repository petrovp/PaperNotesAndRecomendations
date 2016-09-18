package com.brko.service.crawler.exceptions;

/**
 * Created by Petre on 9/3/2016.
 */
public class ReadPaperSummaryFromHtmlException extends Exception {
    public ReadPaperSummaryFromHtmlException(String message, Exception e) {
        super(message, e);
    }
}

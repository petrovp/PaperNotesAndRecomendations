package com.brko.service.services.crawler.exceptions;

/**
 * Created by Petre on 9/3/2016.
 */
public class ReadPdfContentException extends Throwable {
    public ReadPdfContentException(String message, Exception e) {
        super(message, e);
    }
}

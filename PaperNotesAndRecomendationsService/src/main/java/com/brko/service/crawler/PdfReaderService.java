package com.brko.service.crawler;

import com.brko.service.crawler.exceptions.ReadPdfContentException;

/**
 * Service for reading pdfs.
 *
 * Created by Petre on 9/3/2016.
 */
public interface PdfReaderService {

    /**
     * Read the content from pdf on the given url.
     * @param url - pdf url
     * @return String - content
     * @throws ReadPdfContentException
     */
    String readPdfContent(String url) throws ReadPdfContentException;
}

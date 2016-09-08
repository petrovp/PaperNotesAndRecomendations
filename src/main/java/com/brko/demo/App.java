package com.brko.demo;

import com.brko.service.crawler.exceptions.CrawlingPapersException;
import com.brko.service.crawler.exceptions.ReadPaperSummaryFromHtmlException;
import com.brko.service.crawler.exceptions.ReadPdfContentException;
import com.brko.service.crawler.impl.ArxivPaperSearcherServiceImpl;
import com.brko.service.crawler.interfaces.ArxivPaperSearcherService;

import java.io.IOException;

public class App {

    public static void main(String[] args ) throws IOException,
            ReadPdfContentException, ReadPaperSummaryFromHtmlException, CrawlingPapersException {

        ArxivPaperSearcherService arxivPaperSearcherService = new ArxivPaperSearcherServiceImpl();
        arxivPaperSearcherService.searchPapersByMonthAndYearInArxiv(2016, 9);
    }
}

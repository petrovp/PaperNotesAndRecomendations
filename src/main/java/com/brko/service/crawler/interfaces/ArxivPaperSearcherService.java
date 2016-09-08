package com.brko.service.crawler.interfaces;

import com.brko.service.crawler.exceptions.CrawlingPapersException;

import java.io.IOException;

/**
 * Search for papers in arxiv.org site.
 *
 * Created by Petre on 9/4/2016.
 */
public interface ArxivPaperSearcherService {

    /**
     * Search papers in Arxiv repository for specific month.
     *
     * @param year
     * @param month
     */
    void searchPapersByMonthAndYearInArxiv(int year, int month) throws CrawlingPapersException, IOException;
}

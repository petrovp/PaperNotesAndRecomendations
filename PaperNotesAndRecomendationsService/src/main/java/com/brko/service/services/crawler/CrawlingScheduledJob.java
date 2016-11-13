package com.brko.service.services.crawler;

import com.brko.service.services.crawler.exceptions.CrawlingPapersException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by ppetrov on 11/12/2016.
 */
@Component
public class CrawlingScheduledJob {

    final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private ArxivPaperSearcherService arxivPaperSearcherService;

    //@Scheduled(initialDelay = 5*1000, fixedDelay = 24*60*60*1000)
    public void crawlArxivJob() {
        logger.info("Crawling starts now!!");
        try {
            arxivPaperSearcherService.searchPapersByMonthAndYearInArxiv(2016, 9);
        } catch (CrawlingPapersException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
        logger.info("Crawling has finished now!!");
    }
}

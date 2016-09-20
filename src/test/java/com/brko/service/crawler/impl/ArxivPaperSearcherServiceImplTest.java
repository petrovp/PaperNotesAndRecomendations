package com.brko.service.crawler.impl;

import com.brko.service.AbstractServiceIntegrationTest;
import com.brko.service.crawler.exceptions.CrawlingPapersException;
import com.brko.service.crawler.ArxivPaperSearcherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 *  Tests for {@link ArxivPaperSearcherServiceImpl} class.
 *
 * Created by Petre on 9/8/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ArxivPaperSearcherServiceImplTest extends AbstractServiceIntegrationTest {

    @Autowired
    private ArxivPaperSearcherService arxivPaperSearcherService;

    @Test
    public void shouldCrawlAllPaperAfter2015() throws IOException, CrawlingPapersException {
        
    }

}

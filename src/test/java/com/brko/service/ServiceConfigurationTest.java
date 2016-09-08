package com.brko.service;

import com.brko.service.crawler.exceptions.ReadPaperSummaryFromHtmlException;
import org.jsoup.HttpStatusException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test class for Spring configuration.
 *
 * Created by Petre on 9/5/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceConfigurationTest extends AbstractServiceIntegrationTest{

    @Test
    public void testConfiguration() throws HttpStatusException, ReadPaperSummaryFromHtmlException {

    }
}

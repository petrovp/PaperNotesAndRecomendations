package com.brko.service.crawler.impl;

import com.brko.config.BrkoTestConfiguration;
import com.brko.service.crawler.exceptions.ReadPaperSummaryFromHtmlException;
import org.jsoup.HttpStatusException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Petre on 9/5/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BrkoTestConfiguration.class)
public class ServiceConfigurationTest {

    @Test
    public void testConfiguration() throws HttpStatusException, ReadPaperSummaryFromHtmlException {

    }
}

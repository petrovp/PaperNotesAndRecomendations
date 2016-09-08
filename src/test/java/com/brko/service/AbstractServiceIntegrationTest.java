package com.brko.service;

import com.brko.config.BrkoTestConfiguration;
import org.springframework.test.context.ContextConfiguration;

/**
 * Abstract class for all integration tests.
 *
 * Created by Petre on 9/8/2016.
 */
@ContextConfiguration(classes = BrkoTestConfiguration.class)
public abstract class AbstractServiceIntegrationTest {
}

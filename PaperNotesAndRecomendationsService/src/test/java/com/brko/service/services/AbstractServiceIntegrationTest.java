package com.brko.service.services;

import com.brko.service.config.BrkoTestConfiguration;
import org.springframework.test.context.ContextConfiguration;

/**
 * Abstract class for all integration tests.
 *
 * Created by Petre on 9/8/2016.
 */
@ContextConfiguration(classes = BrkoTestConfiguration.class)
public abstract class AbstractServiceIntegrationTest {
}

package com.brko.web.config;

import com.brko.service.config.BrkoServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by ppetrov on 10/8/2016.
 */

@Configuration
@Import(BrkoServiceConfiguration.class)
public class RootConfig {

}

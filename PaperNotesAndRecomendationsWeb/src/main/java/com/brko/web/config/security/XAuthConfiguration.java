package com.brko.web.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * XAuth configuration.
 */
@Configuration
public class XAuthConfiguration {

    private int tokenValidityInSeconds = 60*60*24*7;

    private String secretKey = "bCpbn4767yPlmO2BwoES6laeCa2VZPu5";

    @Bean
    public TokenProvider tokenProvider() {
        return new TokenProvider(secretKey, tokenValidityInSeconds);
    }
}

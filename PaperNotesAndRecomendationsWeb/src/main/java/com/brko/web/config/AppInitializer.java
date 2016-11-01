package com.brko.web.config;

import com.brko.web.config.security.SecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by ppetrov on 10/8/2016.
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
            RootConfig.class,
            SecurityConfig.class
        };
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
            MvcConfig.class
        };
    }

    protected String[] getServletMappings() {
        return new String[]{
            "/"
        };
    }
}

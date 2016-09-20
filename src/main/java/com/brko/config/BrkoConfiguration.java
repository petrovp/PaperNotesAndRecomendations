package com.brko.config;

import com.brko.ml.BrkoMashineLearningScanMarker;
import com.brko.service.BrkoServiceScanMarker;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by Petre on 9/4/2016.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackageClasses = {
    BrkoConfigScanMarker.class,
    BrkoServiceScanMarker.class,
    BrkoMashineLearningScanMarker.class
})
@Import(value = {
        PersistenceConfig.class
})
public class BrkoConfiguration {

    @Bean
    PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

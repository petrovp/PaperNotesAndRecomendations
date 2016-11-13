package com.brko.service.config;

import com.brko.service.ml.BrkoMashineLearningScanMarker;
import com.brko.service.services.BrkoServiceScanMarker;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Petre on 9/4/2016.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableScheduling
@ComponentScan(basePackageClasses = {
    BrkoConfigScanMarker.class,
    BrkoServiceScanMarker.class,
    BrkoMashineLearningScanMarker.class
})
@Import(value = {
        PersistenceConfig.class
})
public class BrkoServiceConfiguration {

    @Bean
    PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates ThreadPoolTaskScheduler bean.
     */
    @Bean(destroyMethod = "shutdown")
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(4);
        return executor;
    }
}

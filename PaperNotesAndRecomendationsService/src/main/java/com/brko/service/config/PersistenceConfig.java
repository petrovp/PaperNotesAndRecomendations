package com.brko.service.config;

import com.brko.service.persistance.BrkoPersistanceScanMarker;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableMongoRepositories(basePackageClasses = BrkoPersistanceScanMarker.class)
@EnableTransactionManagement
public class PersistenceConfig extends AbstractMongoConfiguration{

    @Override
    protected String getDatabaseName() {
        return "brko_papers";
    }

    @Bean
    @Override
    public Mongo mongo() throws Exception {
        MongoClient mongo = new MongoClient("127.0.0.1");
        return mongo;
    }
}

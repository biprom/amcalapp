package com.biprom.bram.app;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

@EnableMongoRepositories("com.biprom.bram.backend")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoURI;

    String databaseName = "DBAmcalPompen";


    @Override
    protected String getDatabaseName() {
        return databaseName;
    }


    @Bean
    public MongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(mongoClient(), getDatabaseName());
    }

    @Override
    public MongoClient mongoClient() {
        MongoClientURI uri = new MongoClientURI(mongoURI);

        return new MongoClient(uri);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), mappingMongoConverter());

        return mongoTemplate;

    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

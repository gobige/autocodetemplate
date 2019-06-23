package com.example.autocodetemplate.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
//启用MongoDB的Repository功能
@EnableMongoRepositories(basePackages = "org.springframework.data.mongodb",repositoryImplementationPostfix = "Impl")
@ComponentScan(basePackages = "org.springframework.data.mongodb")
@PropertySource("classpath:mongo.properties")
public class MongoConfig {
    /*
     * MongoClientFactoryBean 工厂bean 会负责创建Mongo实例。
     * */
    @Bean(name = "mongo")
    public MongoClientFactoryBean mongoClientFactoryBean(Environment env) {
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        MongoClientOptions build = builder.build();
        MongoCredential credential = MongoCredential.createCredential(
                env.getProperty("mongo.user", String.class),
                env.getProperty("mongo.database", String.class),
                env.getProperty("mongo.password", String.class).toCharArray());
        MongoClientFactoryBean mongoClientFactoryBean = new MongoClientFactoryBean();
        mongoClientFactoryBean.setHost(env.getProperty("mongo.host", String.class));
        mongoClientFactoryBean.setPort(env.getProperty("mongo.port", Integer.class));
        mongoClientFactoryBean.setCredentials(new MongoCredential[]{credential});
        mongoClientFactoryBean.setMongoClientOptions(build);
        return mongoClientFactoryBean;
    }

    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate(Mongo mongo, Environment env) {
        return new MongoTemplate(mongo, env.getProperty("mongo.database", String.class));
    }
}

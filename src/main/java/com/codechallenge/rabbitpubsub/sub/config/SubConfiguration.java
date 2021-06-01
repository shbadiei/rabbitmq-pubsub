package com.codechallenge.rabbitpubsub.sub.config;

import com.codechallenge.rabbitpubsub.common.Branch;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@Profile("sub")
public class SubConfiguration {

    private String[] topicsToListen;

    public String[] getTopicsToListen() {
        return topicsToListen;
    }

    @Autowired
    HikariDataSource dataSource;

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db-changelog/liquibase-changeLog.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

    @PostConstruct
    public void init() {
        topicsToListen = Arrays.stream(Branch.values()).map(Branch::getQueueName).toArray(String[]::new);
    }

}

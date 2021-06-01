package com.codechallenge.rabbitpubsub.sub.config;

import com.codechallenge.rabbitpubsub.common.Branch;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
@Profile("sub")
public class SubConfiguration {

    private String[] topicsToListen;

    public String[] getTopicsToListen() {
        return topicsToListen;
    }

    @PostConstruct
    public void init() {
        topicsToListen = Arrays.stream(Branch.values()).map(Branch::getQueueName).toArray(String[]::new);
    }

}

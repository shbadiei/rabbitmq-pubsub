package com.codechallenge.rabbitpubsub.common.config;

import com.codechallenge.rabbitpubsub.common.exception.InValidActiveProfileException;
import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@Profile("!test & !pub & !sub")
public class ActiveProfileAlert {

    @PostConstruct
    public void init(){
      throw new InValidActiveProfileException("use: pub (and/or) sub");
    }

}

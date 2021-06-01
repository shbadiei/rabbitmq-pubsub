package com.codechallenge.rabbitpubsub.sub.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("sub")
public class MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    int c = 0;

    @RabbitListener(queues = "#{subConfiguration.topicsToListen}")
    public void consume(String payload) {
        logger.info("Recieved Message From RabbitMQ: " + payload);
        c++;
        if (c < 3)
            throw new RuntimeException("For-DLQ-TEST"+c);
    }

}

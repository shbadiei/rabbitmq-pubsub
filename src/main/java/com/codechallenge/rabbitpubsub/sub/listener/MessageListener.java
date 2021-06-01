package com.codechallenge.rabbitpubsub.sub.listener;

import com.codechallenge.rabbitpubsub.sub.exception.DeliberateErrorForDLQTestException;
import com.codechallenge.rabbitpubsub.sub.service.MessageLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Profile("sub")
public class MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    private final Random random = new Random();

    private final MessageLogService messageLogService;

    public MessageListener(MessageLogService messageLogService) {
        this.messageLogService = messageLogService;
    }

    @RabbitListener(queues = "#{subConfiguration.topicsToListen}")
    public void consume(String payload) {
        Integer indexToSimulateError = random.nextInt(10);
        logger.info("Received Payload: {} ###", payload);
        if (indexToSimulateError % 3 == 0) {
            logger.error("Random indexToSimulateError: {} % 3 == 0," +
                    "So throws dummy error to test dead-letter-queue", indexToSimulateError);
            throw new DeliberateErrorForDLQTestException(indexToSimulateError.toString());
        }
        messageLogService.storeMessage(payload);
    }

}

package com.codechallenge.rabbitpubsub.pub.service.impl;

import com.codechallenge.rabbitpubsub.common.Branch;
import com.codechallenge.rabbitpubsub.pub.dto.Message;
import com.codechallenge.rabbitpubsub.pub.service.MQPublishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("pub")
public class MQPublishServiceImpl implements MQPublishService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void publish(Message message) {
        String routingKey = message.getDestination().getRoutingKey();
        log.info("publishing {} with routingKey: {}", message.getPayload(), routingKey);
        rabbitTemplate.convertAndSend(routingKey, message.getPayload());
    }
}

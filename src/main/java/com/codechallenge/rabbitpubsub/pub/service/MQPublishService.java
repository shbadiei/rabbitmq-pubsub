package com.codechallenge.rabbitpubsub.pub.service;

import com.codechallenge.rabbitpubsub.pub.dto.Message;

public interface MQPublishService {
    public void publish(Message message);
}

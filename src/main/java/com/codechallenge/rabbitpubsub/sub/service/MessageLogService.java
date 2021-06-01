package com.codechallenge.rabbitpubsub.sub.service;

import com.codechallenge.rabbitpubsub.pub.dto.Message;
import com.codechallenge.rabbitpubsub.sub.model.entity.MessageLog;

public interface MessageLogService {

    MessageLog storeMessage(String payload);

}

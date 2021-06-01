package com.codechallenge.rabbitpubsub.sub.service.impl;

import com.codechallenge.rabbitpubsub.sub.model.entity.MessageLog;
import com.codechallenge.rabbitpubsub.sub.model.repository.MessageLogRepository;
import com.codechallenge.rabbitpubsub.sub.service.MessageLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Profile("sub")
@Slf4j
public class MessageLogServiceImpl implements MessageLogService {

    private final MessageLogRepository messageLogRepository;

    public MessageLogServiceImpl(MessageLogRepository messageLogRepository) {
        this.messageLogRepository = messageLogRepository;
    }

    @Override
    @Transactional
    public MessageLog storeMessage(String payload) {
        MessageLog msgLog = messageLogRepository.save(new MessageLog(UUID.randomUUID(), new Date(), payload));
        log.info("{} has bean successfully stored", msgLog);
        return msgLog;
    }
}

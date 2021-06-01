package com.codechallenge.rabbitpubsub.sub.model.repository;

import com.codechallenge.rabbitpubsub.sub.model.entity.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageLogRepository extends JpaRepository<MessageLog, UUID> {
}

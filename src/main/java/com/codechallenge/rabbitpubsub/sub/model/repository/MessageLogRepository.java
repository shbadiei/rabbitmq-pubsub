package com.codechallenge.rabbitpubsub.sub.model.repository;

import com.codechallenge.rabbitpubsub.sub.model.entity.MessageLog;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Profile("sub")
@Repository
public interface MessageLogRepository extends JpaRepository<MessageLog, UUID> {
}

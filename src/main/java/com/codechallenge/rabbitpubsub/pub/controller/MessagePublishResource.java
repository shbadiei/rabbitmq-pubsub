package com.codechallenge.rabbitpubsub.pub.controller;

import com.codechallenge.rabbitpubsub.pub.dto.Message;
import com.codechallenge.rabbitpubsub.pub.service.MQPublishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@Profile("pub")
@RequestMapping("/api")
public class MessagePublishResource {

    @Autowired
    private MQPublishService mqPublishService;

    @PostMapping(value = "/putmessage", consumes = "application/json")
    public ResponseEntity putMessage(@Valid @RequestBody Message message) {
        mqPublishService.publish(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}

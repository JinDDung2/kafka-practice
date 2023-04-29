package com.example.kafka.service;

import com.example.kafka.dto.MessageDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducerService {

    private final KafkaTemplate kafkaTemplate;
    private final KafkaTemplate jsonKafkaTemplate;

    private final static String TOPIC_NAME = "topic5";

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, MessageDto> jsonKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.jsonKafkaTemplate = jsonKafkaTemplate;
    }

    public void sendJson(MessageDto messageDto) {
        jsonKafkaTemplate.send(TOPIC_NAME, messageDto);
    }

    public void send(String msg) {
        kafkaTemplate.send(TOPIC_NAME, msg);
    }

    public void sendWithCallback(String msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, msg);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println(String.format("Failed %s due to : %s", msg, ex.getMessage()));
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println(String.format("Sent %s  offset : %s", msg, result.getRecordMetadata().offset()));
            }
        });
    }
}

package com.example.kafka.controller;

import com.example.kafka.dto.MessageDto;
import com.example.kafka.service.KafkaProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProducerController {

    private final KafkaProducerService kafkaProducerService;

    public ProducerController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/publish")
    public String publish(@RequestParam String msg) {
        kafkaProducerService.send(msg);
        return "published a message : " + msg;
    }

    @GetMapping("/publish2")
    public String publishWithCallback(String msg) {
        kafkaProducerService.sendWithCallback(msg);
        return "published a message with callback : " + msg;
    }

    @PostMapping("/publish3")
    public String publishJson(@RequestBody MessageDto messageDto) {
        kafkaProducerService.sendJson(messageDto);
        return String.format("published a message with callback : messageDto=%s", messageDto.toString());
    }
}

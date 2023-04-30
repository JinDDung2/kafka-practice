package com.example.kafka.service;

import com.example.kafka.dto.CheckoutDto;
import com.example.kafka.entity.CheckoutEntity;
import com.example.kafka.repository.CheckoutRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckoutWriteService {

    private final static String CHECKOUT_COMPLETE_TOPIC_NAME = "checkout.complete.v1";

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final ModelMapper modelMapper = new ModelMapper();

    private final CheckoutRepository checkoutRepository;

    public Long saveCheckoutData(CheckoutDto checkoutDto) {
        CheckoutEntity checkoutEntity = saveDataBase(checkoutDto);

        checkoutDto.setCheckoutId(checkoutEntity.getCheckoutId());
        checkoutDto.setCreatedAt(new Date(checkoutEntity.getCreatedAt().getTime()));
        sendToKafka(checkoutDto);

        return checkoutEntity.getCheckoutId();
    }

    private void sendToKafka(CheckoutDto checkoutDto) {
        try {
            String jsonInString = objectMapper.writeValueAsString(checkoutDto);
            kafkaTemplate.send(CHECKOUT_COMPLETE_TOPIC_NAME, jsonInString);
            log.info("success sendToKafka");
        } catch (JsonProcessingException e) {
            log.error("sendToKafka", e);
        }
    }

    private CheckoutEntity saveDataBase(CheckoutDto checkoutDto) {
        return checkoutRepository.save(modelMapper.map(checkoutDto, CheckoutEntity.class));
    }


}

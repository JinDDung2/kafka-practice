package com.example.kafka.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MessageDto {

    private String name;
    private String message;

    public MessageDto(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public MessageDto() {}

    @Override
    public String toString() {
        return String.format("{name=%s, message=%s}", name, message);
    }
}

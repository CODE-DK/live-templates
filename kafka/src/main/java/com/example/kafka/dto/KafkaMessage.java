package com.example.kafka.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class KafkaMessage {
    private UUID id = UUID.randomUUID();
    private String author;
    private String message;
}

package com.example.ibmmq.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class IbmMessage {
    private UUID id = UUID.randomUUID();
    private String author;
    private String message;
}

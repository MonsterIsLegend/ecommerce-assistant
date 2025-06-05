package com.jansolski.ecommerceassistant.cohere.dto;

import lombok.Data;

@Data
public class AssistantChatRequest {
    private String message;
    private String productName;
}

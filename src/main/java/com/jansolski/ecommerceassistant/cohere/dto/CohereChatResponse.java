package com.jansolski.ecommerceassistant.cohere.dto;

import lombok.Data;

@Data
public class CohereChatResponse {
    private Message message;

    @Data
    public static class Message {
        private String role;
        private Content[] content;
    }

    @Data
    public static class Content {
        private String type;
        private String text;
    }
}

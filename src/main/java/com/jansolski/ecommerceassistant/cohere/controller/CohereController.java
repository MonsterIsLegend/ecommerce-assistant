package com.jansolski.ecommerceassistant.cohere.controller;

import com.jansolski.ecommerceassistant.cohere.dto.AssistantChatRequest;
import com.jansolski.ecommerceassistant.cohere.service.CohereService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/assistant")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CohereController {

    private final CohereService cohereService;

    @PostMapping("/chat")
    public Mono<ResponseEntity<String>> chat(@RequestBody AssistantChatRequest request) {
        return cohereService.chatWithCohere(request.getMessage(), request.getProductName())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().body("Empty input"));
    }
}

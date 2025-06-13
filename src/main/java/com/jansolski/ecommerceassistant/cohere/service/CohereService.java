package com.jansolski.ecommerceassistant.cohere.service;

import com.jansolski.ecommerceassistant.cohere.dto.CohereChatRequest;
import com.jansolski.ecommerceassistant.cohere.dto.CohereChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CohereService {

    private final WebClient cohereWebClient;

    public Mono<String> chatWithCohere(String userInput, String productName) {
        List<CohereChatRequest.Message> messages = new ArrayList<>();
        messages.add(new CohereChatRequest.Message(
                "system",
                "Jesteś asystentem sprzedaży w sklepie z laptopami. Odpowiadasz klientom na pytania o produkt: \""
                        + productName + "\". Staraj się być zwięzły, uprzejmy i pomocny."
        ));
        messages.add(new CohereChatRequest.Message("user", userInput));

        CohereChatRequest request = CohereChatRequest.builder()
                .model("command-r-plus")
                .stream(false)
                .messages(messages)
                .build();

        return cohereWebClient.post()
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CohereChatResponse.class)
                .map(response -> {
                    if (response.getMessage() != null && response.getMessage().getContent() != null && response.getMessage().getContent().length > 0) {
                        return response.getMessage().getContent()[0].getText();
                    }
                    return "No answer from Cohere";
                });
    }

}

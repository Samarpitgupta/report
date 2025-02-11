package com.iconnate.genai_enginer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.Map;

@RestController
@RequestMapping("/report/ai")
public class GenAIController {

    private static final Logger logger = LoggerFactory.getLogger(GenAIController.class);
    private final OllamaChatModel chatModel;

    @Autowired
    public GenAIController(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @PostMapping("/generateResponse")
    public ResponseEntity<Map<String, Object>> generateResponse(@Valid @RequestBody MessagePayload payload) {
        try {
            String message = payload.getMessage();
            logger.info("Generating response for message: {}", message);
            return ResponseEntity.ok(Map.of("generation", chatModel.call(message)));
        } catch (Exception e) {
            logger.error("Error generating response", e);
            return ResponseEntity.status(500).body(Map.of("error", "Internal Server Error"));
        }
    }

    @PostMapping("/generateResponseStream")
    public Flux<ChatResponse> generateStream(@Valid @RequestBody MessagePayload payload) {
        String message = payload.getMessage();
        logger.info("Generating stream response for message: {}", message);
        Prompt prompt = new Prompt(new UserMessage(message));
        return chatModel.stream(prompt);
    }

    public static class MessagePayload {
        @NotEmpty(message = "Message cannot be empty")
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
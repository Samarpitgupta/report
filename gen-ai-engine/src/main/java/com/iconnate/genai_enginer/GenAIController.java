package com.iconnate.genai_enginer;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("/report/ai")
public class GenAIController {

    private final OllamaChatModel chatModel;

    @Autowired
    public GenAIController(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/generateResponse")
    public Map generateResponse(@RequestParam(value = "message", defaultValue = "What can you can help me with?") String message) {
        return Map.of("generation", chatModel.call(message));
    }

    @GetMapping("/generateResponseStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "What can you can help me with?") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return chatModel.stream(prompt);
    }


}

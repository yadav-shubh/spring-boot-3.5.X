package com.shubham.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    public String chat(String tellMeAJoke) {
        ChatOptions chatOptions = ChatOptions.builder().model("llama-3.3-70b-versatile").maxTokens(50).build();

        return chatClient.prompt().user(tellMeAJoke).options(chatOptions).call().content();
    }
}

package com.shubham.ai;

import com.shubham.ai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chat")
    public String chat() {
        return chatService.chat("Tell me a joke");
    }
}

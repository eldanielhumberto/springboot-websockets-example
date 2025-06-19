package com.websockets.springboot_websockets_demo.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.websockets.springboot_websockets_demo.models.Message;

@Controller
public class MessageController {
    @MessageMapping("/new_message")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) {
        return message;
    }
}

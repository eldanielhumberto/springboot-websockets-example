package com.websockets.springboot_websockets_demo.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.websockets.springboot_websockets_demo.models.Message;

@Controller
public class MessageController {
    @MessageMapping("/new_message") // Route to receive
    @SendTo("/topic/messages") // Route to where I will send data
    public Message sendMessage(Message message) {
        return message;
    }
}

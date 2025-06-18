package com.websockets.springboot_websockets_demo.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.websockets.springboot_websockets_demo.utils.GlobalData;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UsersController {

    @MessageMapping("/new_user")
    @SendTo("/topic/users")
    public int newUser(int count) {
        return count;
    }

    @GetMapping("/api/usersCount")
    public int usersCount() {
        return GlobalData.getInstance().getUsersCount();
    }

}

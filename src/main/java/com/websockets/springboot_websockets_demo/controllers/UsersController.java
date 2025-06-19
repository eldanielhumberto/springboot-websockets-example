package com.websockets.springboot_websockets_demo.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.websockets.springboot_websockets_demo.utils.GlobalData;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UsersController {

    @MessageMapping("/new_user") // Route to receive
    @SendTo("/topic/users") // Route to where I will send data
    public int newUser(int count) {
        return count;
    }

    // To send how many users are connected
    @GetMapping("/api/usersCount")
    public int usersCount() {
        return GlobalData.getInstance().getUsersCount();
    }

}

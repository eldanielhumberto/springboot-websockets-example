package com.websockets.springboot_websockets_demo.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.websockets.springboot_websockets_demo.utils.GlobalData;

@Component
public class WebSocketEventListener {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        GlobalData.getInstance().setUsersCount(GlobalData.getInstance().getUsersCount() + 1);
        simpMessagingTemplate.convertAndSend("/topic/users", GlobalData.getInstance().getUsersCount());
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        GlobalData.getInstance().setUsersCount(GlobalData.getInstance().getUsersCount() - 1);
        simpMessagingTemplate.convertAndSend("/topic/users", GlobalData.getInstance().getUsersCount());
    }
}

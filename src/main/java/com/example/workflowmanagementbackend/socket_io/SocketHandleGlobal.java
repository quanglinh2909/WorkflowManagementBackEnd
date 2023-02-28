package com.example.workflowmanagementbackend.socket_io;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.workflowmanagementbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component

public class SocketHandleGlobal {
    private final SocketIONamespace namespace;

    private final UserService userService;
    private SocketIOServer server;

    @Autowired
    public SocketHandleGlobal(SocketIOServer server, UserService userService) {
        // muốn nhận tất cả thì server.addNamespace("")
        this.namespace = server.addNamespace("/chat");
        this.userService = userService;
        this.server = server;
        this.namespace.addConnectListener(onConnected());
        this.namespace.addDisconnectListener(onDisconnected());
//        this.namespace.addEventListener("chat", ChatMessage.class, onChatReceived());
        //onevent join
        this.namespace.addEventListener("join", Long.class, onJoin());

    }

    private DataListener<Long> onJoin() {
        return (client, data, ackSender) -> {
            System.out.println("onJoin: " + data);
            userService.setIdSocket(data, client.getSessionId().toString());
        };
    }


    private ConnectListener onConnected() {
        return client -> {
            HandshakeData handshakeData = client.getHandshakeData();
            String id = client.getSessionId().toString();
            System.out.println("userConnect: " + id);
            client.sendEvent("connect", id);
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            String id = client.getSessionId().toString();
            userService.deleteIdSocketOnIdSocket(id);

            System.out.println("onDisconnected: " + id);
//            log.debug("Client[{}] - Disconnected from chat module.", client.getSessionId().toString());
        };
    }
}

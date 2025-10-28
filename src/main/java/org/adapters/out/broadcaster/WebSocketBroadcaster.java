package org.adapters.out.broadcaster;

import org.application.port.out.GameStateBroadcaster;
import org.java_websocket.WebSocket;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketBroadcaster implements GameStateBroadcaster {
    private final Set<WebSocket> connections = ConcurrentHashMap.newKeySet();

    public void addConnection(WebSocket conn) {
        connections.add(conn);
    }

    public void removeConnection(WebSocket conn) {
        connections.remove(conn);
    }

    @Override
    public void broadcast(String message) {
        for (WebSocket conn : connections) {
            if (conn.isOpen()) {
                conn.send(message);
            }
        }
    }
}



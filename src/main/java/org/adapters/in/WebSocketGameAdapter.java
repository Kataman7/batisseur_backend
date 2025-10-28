package org.adapters.in;


import org.adapters.out.broadcaster.WebSocketBroadcaster;
import org.application.port.in.GameCommandHandler;
import org.domain.events.game.AddMessageEvent;
import org.domain.events.game.GameEvent;
import org.domain.events.game.JoinGameEvent;
import org.domain.events.game.LeaveGameEvent;
import org.domain.events.server.CreateServerEvent;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketGameAdapter extends WebSocketServer {
    private final GameCommandHandler handler;
    private final WebSocketBroadcaster broadcaster;
    private final ConcurrentHashMap<WebSocket, String> connectedPlayers = new ConcurrentHashMap<>();

    public WebSocketGameAdapter(int port, GameCommandHandler handler, WebSocketBroadcaster broadcaster) {
        super(new InetSocketAddress(port));
        this.handler = handler;
        this.broadcaster = broadcaster;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        broadcaster.addConnection(conn);
        conn.send("true");
        System.out.println("Nouvelle connexion de " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        String playerName = connectedPlayers.remove(conn);
        broadcaster.removeConnection(conn);
        if (playerName != null) {
            handler.handle(new LeaveGameEvent(playerName));
        }
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        GameEvent event = parseCommand(message, conn);
        if (event != null) {
            handler.handle(event);
        } else {
            conn.send("Commande invalide : " + message);
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("Serveur WebSocket démarré sur le port " + getPort());
    }

    private GameEvent parseCommand(String message, WebSocket conn) {
        if (message == null || message.trim().isEmpty()) {
            conn.send("Message vide reçu.");
            return null;
        }

        javax.json.JsonObject jsonObject;
        try (javax.json.JsonReader reader = javax.json.Json.createReader(new java.io.StringReader(message))) {
            jsonObject = reader.readObject();
        } catch (Exception e) {
            conn.send("Format JSON invalide : " + e.getMessage());
            return null;
        }

        String eventType = jsonObject.getString("event", "");
        if (eventType.isEmpty()) {
            conn.send("Champ 'event' manquant dans le JSON.");
            return null;
        }

        switch (eventType.toLowerCase()) {
            //{"event": "addMessage", "playerName": "Alice", "messageContent": "Hello everyone!"}
            case "addmessage": {
                String playerName = jsonObject.getString("playerName", "");
                String messageContent = jsonObject.getString("messageContent", "");
                return new AddMessageEvent(playerName, messageContent);
            }
            case "join": {
                String playerName = jsonObject.getString("playerName", "");
                // Ajoute d'autres paramètres optionnels ici si besoin
                connectedPlayers.put(conn, playerName);
                return new JoinGameEvent(playerName);
            }
            case "leave": {
                String playerName = jsonObject.getString("playerName", "");
                // Ajoute d'autres paramètres optionnels ici si besoin
                connectedPlayers.remove(conn);
                return new LeaveGameEvent(playerName);
            }
            case "createserver": {
                // Paramètres optionnels pour createServer si besoin
                return new CreateServerEvent();
            }
            default:
                conn.send("Type d'événement inconnu : " + eventType);
                return null;
        }
    }
}

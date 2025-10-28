package org;

import org.adapters.in.WebSocketGameAdapter;
import org.adapters.out.broadcaster.WebSocketBroadcaster;
import org.application.service.ServerService;

public class MainWebSocket {

    private static final int LOBBY_PORT = 8080; // Port pour le lobby principal

    public static void main(String[] args) {
        // Créer un broadcaster pour le lobby
        WebSocketBroadcaster lobbyBroadcaster = new WebSocketBroadcaster();

        // Créer le ServerService avec le broadcaster du lobby
        ServerService serverService = new ServerService(lobbyBroadcaster);

        // Créer le WebSocket du lobby
        WebSocketGameAdapter lobbyAdapter = new WebSocketGameAdapter(LOBBY_PORT, serverService, lobbyBroadcaster);

        // Démarrer le lobby
        System.out.println("Démarrage du lobby sur le port " + LOBBY_PORT);
        lobbyAdapter.start();

        System.out.println("Serveur prêt !");
    }
}
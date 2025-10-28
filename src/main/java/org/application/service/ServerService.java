package org.application.service;

import org.adapters.in.WebSocketGameAdapter;
import org.adapters.out.broadcaster.WebSocketBroadcaster;
import org.adapters.out.saver.InMemoryGameSaver;
import org.application.port.in.GameCommandHandler;
import org.application.port.out.GameStateSaver;
import org.domain.events.game.GameEvent;
import org.domain.events.server.CreateServerEvent;
import org.domain.model.Board;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerService implements GameCommandHandler {
    private static final Map<Integer, WebSocketGameAdapter> adapters = new ConcurrentHashMap<>();
    private static final int PORT_START = 1024;
    private static final int PORT_END = 65535;
    private final WebSocketBroadcaster lobbyBroadcaster;

    public ServerService(WebSocketBroadcaster lobbyBroadcaster) {
        this.lobbyBroadcaster = lobbyBroadcaster;
    }

    private static int findFreePort(int startPort) {
        for (int port = startPort; port <= PORT_END; port++) {
            try (ServerSocket ignored = new ServerSocket(port)) {
                return port;
            } catch (IOException e) {
                // Port occupé, on continue
            }
        }
        throw new RuntimeException("Aucun port libre trouvé dans la plage autorisée.");
    }

    public static int createSession() {
        int port = findFreePort(PORT_START);

        Board board = new Board();
        GameStateSaver saver = new InMemoryGameSaver();
        WebSocketBroadcaster broadcaster = new WebSocketBroadcaster();
        GameService service = new GameService(board, saver, broadcaster);
        WebSocketGameAdapter adapter = new WebSocketGameAdapter(port, service, broadcaster);

        adapters.put(port, adapter);
        adapter.start();
        return port;
    }

    public static WebSocketGameAdapter getAdapter(int port) {
        return adapters.get(port);
    }

    public static List<Integer> getActivePorts() {
        return new ArrayList<>(adapters.keySet());
    }

    @Override
    public void handle(GameEvent event) {
        if (event instanceof CreateServerEvent) {
        try {
            // Création d'un nouveau jeu
            int port = createSession();
            // Informer le client du nouveau port via le lobby
            lobbyBroadcaster.broadcast("NEW_GAME:" + port);
        } catch (RuntimeException e) {
            lobbyBroadcaster.broadcast("ERREUR_CREATION_JEU:" + e.getMessage());
        }
        } else {
            // Par défaut, on ne sait pas quel jeu est concerné
            // Cette méthode ne devrait être appelée que pour des commandes globales
            lobbyBroadcaster.broadcast("Commande non reconnue au niveau du serveur");
        }
    }
}

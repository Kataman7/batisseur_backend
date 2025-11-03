package org;

import org.adapters.in.ConsoleCommandAdapter;
import org.adapters.out.broadcaster.ConsoleBroadcaster;
import org.adapters.out.saver.ConsoleGameSaver;
import org.application.port.out.GameStateBroadcaster;
import org.application.port.out.GameStateSaver;
import org.application.service.GameService;
import org.domain.event.JoinBoardEvent;
import org.domain.model.Board;

public class Main {
    public static void main(String[] args) {
        // Création du modèle de jeu
        Board game = new Board();
        (new JoinBoardEvent("Alice")).apply(game);
        (new JoinBoardEvent("Bob")).apply(game);

        // Adaptateurs sortants
        GameStateSaver saver = new ConsoleGameSaver();         // Affiche l'état après chaque action
        GameStateBroadcaster broadcaster = new ConsoleBroadcaster(); // Affiche le message de chaque événement

        // Service qui gère les événements du jeu
        GameService service = new GameService(game, saver, broadcaster);

        // Adaptateur entrant : lit les commandes depuis la console
        ConsoleCommandAdapter adapter = new ConsoleCommandAdapter(service);

        // Démarre la boucle de jeu
        adapter.startListening();
    }
}
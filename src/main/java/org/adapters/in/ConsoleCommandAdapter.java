package org.adapters.in;

import org.application.port.in.GameCommandHandler;
import org.domain.events.GameEvent;

import java.util.Scanner;

public class ConsoleCommandAdapter implements GameCommandHandler {

    private final GameCommandHandler handler;  // Cette ligne permet de déléguer à GameService

    public ConsoleCommandAdapter(GameCommandHandler handler) {
        this.handler = handler;  // L'injection de dépendance, ici c'est GameService
    }

    // Méthode pour commencer à écouter les commandes utilisateur
    public void startListening() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Entrez une commande : ");
            String command = scanner.nextLine();
            GameEvent event = parseCommand(command);
            if (event != null) {
                handler.handle(event);
            } else {
                System.out.println("Commande invalide.");
            }
        }
    }

    // Cette méthode analyse la commande texte et crée l'événement correspondant
    private GameEvent parseCommand(String command) {
       /* if (command.equals("draw")) {
            return new DrawCardEvent("Alice", 1);  // Exemple d'événement pour tirer une carte
        } else if (command.equals("endturn")) {
            return new EndTurnEvent();  // Exemple d'événement pour finir le tour
        }*/
        return null;  // Commande non reconnue
    }

    // Méthode héritée de GameCommandHandler (délégation à handler)
    @Override
    public void handle(GameEvent event) {
        handler.handle(event);  // Délégation à la couche service pour appliquer l'événement
    }
}
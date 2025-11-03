package org.application.service;

import org.application.port.in.GameCommandHandler;
import org.application.port.out.GameStateBroadcaster;
import org.application.port.out.GameStateSaver;
import org.domain.event.GameEvent;
import org.domain.model.Board;

public class GameService implements GameCommandHandler {

    private final Board game;
    private final GameStateSaver saver;
    private final GameStateBroadcaster broadcaster;

    public GameService(Board game, GameStateSaver saver, GameStateBroadcaster broadcaster) {
        this.game = game;
        this.saver = saver;
        this.broadcaster = broadcaster;
    }

    @Override
    public void handle(GameEvent event) {
        // logique du jeu
        boolean allRulesApplicable = event.getRules().stream()
                .allMatch(gameRule -> gameRule.isApplicable(game));
        if (!allRulesApplicable) {
            broadcaster.broadcast("Une ou plusieurs règles ne sont pas applicables.");
            return;
        }
        event.apply(game);
        saver.save(game);
        // notification: on envoie d'abord l'événement puis l'état complet du board
        broadcaster.broadcast(event.toJson().toString());
        broadcaster.broadcast(game.toJson().toString());
    }
}

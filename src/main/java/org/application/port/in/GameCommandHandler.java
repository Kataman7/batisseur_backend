package org.application.port.in;

import org.domain.events.game.GameEvent;

public interface GameCommandHandler {
    void handle(GameEvent event);
}
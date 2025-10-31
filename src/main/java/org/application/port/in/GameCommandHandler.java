package org.application.port.in;

import org.domain.events.GameEvent;

public interface GameCommandHandler {
    void handle(GameEvent event);
}
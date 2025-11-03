package org.application.port.in;

import org.domain.event.GameEvent;

public interface GameCommandHandler {
    void handle(GameEvent event);
}
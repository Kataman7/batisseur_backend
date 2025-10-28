package org.application.port.out;

import org.domain.model.Board;

public interface GameStateSaver {
    void save(Board game);
}
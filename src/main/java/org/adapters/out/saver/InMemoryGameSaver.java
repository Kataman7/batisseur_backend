package org.adapters.out.saver;

import org.application.port.out.GameStateSaver;
import org.domain.model.Board;

public class InMemoryGameSaver implements GameStateSaver {

    private Board lastSavedGame;

    @Override
    public void save(Board game) {
        this.lastSavedGame = game;
    }

    public Board getLastSavedGame() {
        return lastSavedGame;
    }
}
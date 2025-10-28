package org.adapters.out.saver;

import org.application.port.out.GameStateSaver;
import org.domain.model.Board;

public class ConsoleGameSaver implements GameStateSaver {

    @Override
    public void save(Board board) {
        System.out.println("=== État du jeu ===");

        System.out.println(board);

        System.out.println("===================");
    }
}
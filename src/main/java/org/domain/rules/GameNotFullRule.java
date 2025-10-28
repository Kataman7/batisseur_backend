package org.domain.rules;

import org.domain.enums.ErrorCodes;
import org.domain.model.Board;

public class GameNotFullRule implements GameRule
{
    @Override
    public boolean isApplicable(Board board) {
        return board.getPlayers().size() <= 16;
    }

    @Override
    public int getCode() {
        return ErrorCodes.GAME_FULL.ordinal();
    }
}

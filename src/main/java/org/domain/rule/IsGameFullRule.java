package org.domain.rule;

import org.domain.enums.ErrorCodes;
import org.domain.model.Board;

public class IsGameFullRule implements GameRule
{
    @Override
    public boolean isApplicable(Board board) {
        return board.getPlayers().size() <= 8;
    }

    @Override
    public int getCode() {
        return ErrorCodes.GAME_FULL.ordinal();
    }
}

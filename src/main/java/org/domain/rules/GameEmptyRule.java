package org.domain.rules;

import org.domain.enums.ErrorCodes;
import org.domain.model.Board;

public class GameEmptyRule implements GameRule
{
    @Override
    public boolean isApplicable(Board board) {
        return board.getPlayers().size() < 2;
    }

    @Override
    public int getCode() {
        return ErrorCodes.GAME_EMPTY.ordinal();
    }


}

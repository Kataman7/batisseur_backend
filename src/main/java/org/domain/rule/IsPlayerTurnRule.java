package org.domain.rule;

import org.domain.enums.ErrorCodes;
import org.domain.model.Board;

public class IsPlayerTurnRule extends AbstractPlayerRule
{
    public IsPlayerTurnRule(String playerName) {
        super(playerName);
    }

    @Override
    public boolean isApplicable(Board board) {
        return board.getPlayers().getCurrent().getName().equals(getPlayerName());
    }

    @Override
    public int getCode() {
        return ErrorCodes.PLAYER_TURN.ordinal();
    }
}

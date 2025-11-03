package org.domain.rule;

import org.domain.enums.ErrorCodes;
import org.domain.model.Board;

public class ValidPlayerRule extends AbstractPlayerRule
{
    public ValidPlayerRule(String playerName) {
        super(playerName);
    }

    @Override
    public boolean isApplicable(Board board) {
        return board.getPlayers().getByName(super.getPlayerName()) != null;
    }

    @Override
    public int getCode() {
        return ErrorCodes.VALID_PLAYER.ordinal();
    }
}

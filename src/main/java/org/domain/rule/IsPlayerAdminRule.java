package org.domain.rule;

import org.domain.model.Board;

public class IsPlayerAdminRule extends AbstractPlayerRule
{
    public IsPlayerAdminRule(String playerName) {
        super(playerName);
    }

    @Override
    public boolean isApplicable(Board board) {
        return board.getAdminPlayerName().equals(getPlayerName());
    }

    @Override
    public int getCode() {
        return 0;
    }
}

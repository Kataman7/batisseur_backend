package org.domain.rules;

import org.domain.model.Board;

public class PlayerAdminRule extends PlayerRule
{
    public PlayerAdminRule(String playerName) {
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

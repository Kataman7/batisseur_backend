package org.domain.rule;

import org.domain.model.Board;

public class isPlayerAlive extends AbstractPlayerRule
{

    public isPlayerAlive(String playerName) {
        super(playerName);
    }

    @Override
    public boolean isApplicable(Board board) {
        return !board.getPlayers().getByName(getPlayerName()).isDead();
    }

    @Override
    public int getCode() {
        return 0;
    }
}

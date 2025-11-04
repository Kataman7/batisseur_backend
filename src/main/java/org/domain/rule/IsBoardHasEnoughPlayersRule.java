package org.domain.rule;

import org.domain.model.Board;

public class IsBoardHasEnoughPlayersRule implements GameRule
{
    private final int numberOfPlayers;

    public IsBoardHasEnoughPlayersRule(int numberOfPlayers)
    {
        this.numberOfPlayers = numberOfPlayers;
    }
    @Override
    public boolean isApplicable(Board board)
    {
        return board.getPlayers().size() >= numberOfPlayers;
    }

    @Override
    public int getCode() {
        return 0;
    }
}

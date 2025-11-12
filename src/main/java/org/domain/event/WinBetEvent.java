package org.domain.event;

import org.domain.model.Board;
import org.domain.model.Player;

import javax.json.JsonObject;

public class WinBetEvent extends PlayerEvent
{

    public WinBetEvent(String playerName, String playerToken)
    {
        super(playerName, playerToken);
    }

    @Override
    public void apply(Board board)
    {
        Player bettor = board.getBettor();

        board.setBetValue(board.getBetValue() - 1);
        if (board.getBetValue() == 0)
        {
            bettor.setScore(bettor.getScore() + 1);
            if (bettor.getScore() >= 2)
            {
                board.setGameOver(true);
                board.setWinner(bettor);
            }
        }
    }

    @Override
    public JsonObject toJson()
    {
        return null;
    }
}

package org.domain.event;

import org.domain.model.Board;
import org.domain.model.Player;
import org.domain.rule.*;

import javax.json.JsonObject;
import java.util.List;

public class RaiseBetEvent extends PlayerEvent
{
    private final int betValue;

    public RaiseBetEvent(String playerName, String playerToken, int betValue) {
        super(playerName, playerToken);
        this.betValue = betValue;

        super.getRules().addAll(List.of(
                new NotRule(new IsGameOverRule()),
                new IsPlayerTurnRule(getPlayerName()),
                new IsPlayerHasBulletLoadedRule(getPlayerName()),
                new ValidBetRule(betValue),
                new NotRule(new ValidBettorRule(playerName))
        ));
    }

    @Override
    public void apply(Board board) {
        Player player = board.getPlayers().getCurrent();
        board.setBetValue(betValue);
        board.setBettor(player);
    }

    @Override
    public JsonObject toJson() {
        return null;
    }
}

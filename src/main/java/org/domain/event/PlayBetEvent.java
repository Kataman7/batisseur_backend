package org.domain.event;

import org.domain.model.Board;
import org.domain.rule.*;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class PlayBetEvent extends PlayerEvent
{
    private static final String NAME = "PlayBetEvent";

    private final String targetPlayerName;

    public PlayBetEvent(String playerName, String playerToken, String targetPlayerName) {
        super(playerName, playerToken);
        this.targetPlayerName = targetPlayerName;

        super.getRules().addAll(List.of(
                new IsPlayerTurnRule(super.getPlayerName()),
                new NotRule(new IsGameOverRule()),
                new ValidPlayerRule(targetPlayerName),
                new IsPlayerHasBulletLoadedRule(targetPlayerName)
        ));
    }

    @Override
    public void apply(Board board) {

    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", NAME)
                .add("player", getPlayerName())
                .add("targetPlayer", targetPlayerName)
                .build();
    }
}

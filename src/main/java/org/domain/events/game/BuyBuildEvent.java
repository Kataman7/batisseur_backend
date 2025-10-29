package org.domain.events.game;

import org.domain.enums.Phases;
import org.domain.model.Board;
import org.domain.model.Build;
import org.domain.rules.*;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class BuyBuildEvent extends PlayerEvent{
    private final int buildIndex;
    private final int cost;

    public BuyBuildEvent(String playerName, int buildIndex, int cost) {
        super(playerName);
        this.buildIndex = buildIndex;
        this.cost = cost;

        super.getRules().addAll(List.of(
                new ValidGamePhaseRule(Phases.BUY_BUILDS),
                new ValidDeckBuildIndexRule(buildIndex),
                new ValidPlayerRule(getPlayerName()),
                new PlayerTurnRule(getPlayerName()),
                new PlayerHaveEnoughtMoneyRule(getPlayerName(), cost)
        ));
    }

    @Override
    public void apply(Board board) {
        var player = board.getPlayers().getByName(getPlayerName());
        Build build = board.getBuildDeck().get(buildIndex);
        player.setMoney(player.getMoney() - build.getReward());
        player.getBuilds().add(build);
        board.setPlayedPlayersCount(board.getPlayedPlayersCount() + 1);
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", "buyBuild")
                .add("player", getPlayerName())
                .add("buildIndex", buildIndex)
                .add("cost", cost)
                .build();
    }
}

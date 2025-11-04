package org.domain.event;

import org.domain.enums.Phases;
import org.domain.model.Board;
import org.domain.model.Build;
import org.domain.rule.*;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class BuyBuildEvent extends PlayerEvent
{
    public static final String NAME = "BuyBuildEvent";

    private final int buildIndex;
    private final int cost;

    public BuyBuildEvent(String playerName, int buildIndex, int cost)
    {
        super(playerName);
        this.buildIndex = buildIndex;
        this.cost = cost;

        super.getRules().addAll(List.of(
                new ValidGamePhaseRule(Phases.BUY_BUILDS),
                new ValidDeckBuildIndexRule(buildIndex),
                new ValidPlayerRule(getPlayerName()),
                new IsPlayerTurnRule(getPlayerName()),
                new IsPlayerRichEnoughRule(getPlayerName(), cost)
        ));
    }
    @Override
    public void apply(Board board)
    {
        var player = board.getPlayers().getByName(getPlayerName());
        Build build = board.getBuildDeck().remove(buildIndex);
        player.setMoney(player.getMoney() - build.getCost());
        player.getBuilds().add(build);
    }
    @Override
    public JsonObject toJson()
    {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", NAME)
                .add("player", getPlayerName())
                .add("buildIndex", buildIndex)
                .add("cost", cost)
                .build();
    }
}

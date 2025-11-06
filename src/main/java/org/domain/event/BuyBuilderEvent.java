package org.domain.event;

import org.domain.enums.Phases;
import org.domain.model.Board;
import org.domain.model.Builder;
import org.domain.rule.*;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class BuyBuilderEvent extends PlayerEvent
{
    public static final String NAME = "BuyBuilderEvent";

    private final int builderIndex;
    private final int cost;

    public BuyBuilderEvent(String playerName, String playerToken, int builderIndex, int cost)
    {
        super(playerName, playerToken);
        this.builderIndex = builderIndex;
        this.cost = cost;

        super.getRules().addAll(List.of(
                new ValidGamePhaseRule(Phases.BUY_BUILDERS),
                new ValidDeckBuilderIndexRule(builderIndex),
                new IsPlayerTurnRule(getPlayerName()),
                new IsPlayerRichEnoughRule(getPlayerName(), cost)
        ));
    }
    @Override
    public void apply(Board board)
    {
        var player = board.getPlayers().getByName(getPlayerName());
        Builder build = board.getBuidlerDeck().remove(builderIndex);
        player.setMoney(player.getMoney() - build.getCost());
        player.getBuilders().add(build);
    }
    @Override
    public JsonObject toJson()
    {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", NAME)
                .add("player", getPlayerName())
                .add("value", builderIndex)
                .add("cost", cost)
                .build();
    }
}

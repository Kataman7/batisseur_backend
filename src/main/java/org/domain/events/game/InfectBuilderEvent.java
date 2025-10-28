package org.domain.events.game;

import org.domain.enums.Phases;
import org.domain.model.Board;
import org.domain.model.Builder;
import org.domain.model.Player;
import org.domain.rules.*;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class InfectBuilderEvent extends PlayerEvent
{
    private final int builderIndex;

    public InfectBuilderEvent(String playerName, int builderIndex, int cost) {
        super(playerName);
        this.builderIndex = builderIndex;

        super.getRules().addAll(List.of(
                new ValidGamePhaseRule(Phases.BUY_BUILDERS),
                new ValidDeckBuilderIndexRule(builderIndex),
                new ValidPlayerRule(playerName),
                new PlayerTurnRule(playerName),
                new PlayerHaveEnoughtMoneyRule(playerName, cost)
        ));
    }

    @Override
    public void apply(Board board) {
        Builder builder = board.getBuilders().get(builderIndex);
        Player player = board.getPlayers().getByName(getPlayerName());
        player.setMoney(player.getMoney() - builder.getCost());
        builder.setInfected(true);
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", "infectBuilder")
                .add("player", getPlayerName())
                .add("builderIndex", builderIndex)
                .build();
    }
}

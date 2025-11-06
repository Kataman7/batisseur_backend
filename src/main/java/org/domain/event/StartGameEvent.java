package org.domain.event;

import org.domain.enums.Phases;
import org.domain.model.Board;
import org.domain.rule.IsBoardHasEnoughPlayersRule;
import org.domain.rule.IsPlayerAdminRule;
import org.domain.rule.ValidGamePhaseRule;
import org.domain.rule.ValidPlayerRule;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class StartGameEvent extends PlayerEvent
{
    public static final String NAME = "StartGameEvent";

    public StartGameEvent(String playerName, String playerToken)
    {
        super(playerName, playerToken);

        this.getRules().addAll(
                List.of(
                        new ValidGamePhaseRule(Phases.LOBBY),
                        new IsPlayerAdminRule(playerName),
                        new IsBoardHasEnoughPlayersRule(2)
                )
        );
    }
    @Override
    public void apply(Board board)
    {
        board.setPhase(Phases.BUY_BUILDERS);
    }
    @Override
    public JsonObject toJson()
    {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", NAME)
                .build();
    }
}

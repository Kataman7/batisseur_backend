package org.domain.event;

import org.domain.enums.Phases;
import org.domain.model.Board;
import org.domain.model.Player;
import org.domain.rule.IsPlayerTurnRule;
import org.domain.rule.NotRule;
import org.domain.rule.ValidGamePhaseRule;
import org.domain.rule.ValidPlayerRule;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class PassToNextPlayerEvent extends PlayerEvent
{
    public static final String NAME = "PassToNextPlayerEvent";

    private final Phases phase;
    public PassToNextPlayerEvent(String playerName, String playerToken, int gamePhase)
    {
        super(playerName, playerToken);
        this.phase = Phases.values()[gamePhase];

        super.getRules().addAll(List.of(
                new ValidGamePhaseRule(phase),
                new NotRule(new ValidGamePhaseRule(Phases.BUILD)),
                new NotRule(new ValidGamePhaseRule(Phases.LOBBY)),
                new IsPlayerTurnRule(playerName)
        ));
    }
    @Override
    public void apply(Board board)
    {
        Player player = board.getPlayers().getCurrent();
        player.setPlayedPhase(phase);
        Phases phase = board.getPhase();

        Player nextPlayer = board.getPlayers().next();
        if (phase.equals(nextPlayer.getPlayedPhase()))
        {
            if (phase.equals(Phases.BUY_BUILDERS))
            {
                board.setPhase(Phases.BUY_BUILDS);
            }
            else if (phase.equals(Phases.BUY_BUILDS))
            {
                board.setPhase(Phases.BUILD);
            }
        }

    }
    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", NAME)
                .build();
    }
}

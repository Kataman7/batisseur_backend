package org.domain.events.game;

import org.domain.enums.Phases;
import org.domain.model.Board;
import org.domain.model.Player;
import org.domain.rules.GameNotFullRule;
import org.domain.rules.NotRule;
import org.domain.rules.ValidGamePhaseRule;
import org.domain.rules.ValidPlayerRule;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class JoinGameEvent extends PlayerEvent
{
    public JoinGameEvent(String playerName)
    {
        super(playerName);
        super.getRules().addAll(List.of(
                new ValidGamePhaseRule(Phases.LOBBY),
                new GameNotFullRule(),
                new NotRule(new ValidPlayerRule(getPlayerName()))
        ));
    }
    @Override
    public void apply(Board board)
    {
        board.getPlayers().add(new Player(getPlayerName()));
    }
    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", "join")
                .add("player", getPlayerName())
                .build();
    }
}

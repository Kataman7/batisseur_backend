package org.domain.event;

import org.domain.enums.Phases;
import org.domain.model.Board;
import org.domain.model.Player;
import org.domain.rule.IsGameFullRule;
import org.domain.rule.NotRule;
import org.domain.rule.ValidGamePhaseRule;
import org.domain.rule.ValidPlayerRule;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class JoinBoardEvent extends PlayerEvent
{
    public static final String NAME = "JoinGameEvent";

    public JoinBoardEvent(String playerName)
    {
        super(playerName);
        super.getRules().addAll(List.of(
                new ValidGamePhaseRule(Phases.LOBBY),
                new NotRule(new IsGameFullRule()),
                new NotRule(new ValidPlayerRule(getPlayerName()))
        ));
    }
    @Override
    public void apply(Board board)
    {
        board.getPlayers().add(new Player(getPlayerName()));

        if (board.getPlayers().size() == 1)
        {
            board.setAdminPlayerName(getPlayerName());
        }
    }
    @Override
    public JsonObject toJson()
    {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", NAME)
                .add("player", getPlayerName())
                .build();
    }
}

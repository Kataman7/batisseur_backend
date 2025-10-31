package org.domain.events;

import org.domain.model.Board;
import org.domain.model.Player;
import org.domain.rules.ValidPlayerRule;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class LeaveGameEvent extends PlayerEvent
{
    public static final String NAME = "LeaveGameEvent";

    public LeaveGameEvent(String playerName)
    {
        super(playerName);

        super.getRules().addAll(List.of(
                new ValidPlayerRule(getPlayerName())
        ));

    }
    @Override
    public void apply(Board board)
    {
        board.getPlayers().remove(new Player(super.getPlayerName()));

        if (getPlayerName().equals(board.getAdminPlayerName()) && !board.getPlayers().isEmpty()) {
            board.setAdminPlayerName(board.getPlayers().getCurrent().getName());
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

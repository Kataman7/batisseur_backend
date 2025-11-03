package org.domain.event;

import org.domain.model.Board;
import org.domain.model.Player;
import org.domain.rule.ValidPlayerRule;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class LeaveBoardEvent extends PlayerEvent
{
    public static final String NAME = "LeaveGameEvent";

    public LeaveBoardEvent(String playerName)
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

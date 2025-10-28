package org.domain.events.server;

import org.domain.events.game.GameEvent;
import org.domain.model.Board;

import javax.json.Json;
import javax.json.JsonObject;

public class CreateServerEvent extends GameEvent
{
    @Override
    public void apply(Board board)
    {
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", "create")
                .build();
    }
}

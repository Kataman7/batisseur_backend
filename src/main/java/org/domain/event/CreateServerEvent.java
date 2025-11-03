package org.domain.event;

import org.domain.model.Board;

import javax.json.Json;
import javax.json.JsonObject;

public class CreateServerEvent extends GameEvent
{
    public static final String NAME = "CreateServerEvent";

    @Override
    public void apply(Board board)
    {
    }
    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", NAME)
                .build();
    }
}

package org.domain.event;

import lombok.Setter;
import org.domain.model.Board;
import javax.json.Json;
import javax.json.JsonObject;


public class CreateServerEvent extends GameEvent
{
    public static final String NAME = "CreateServerEvent";
    @Setter
    public String port;

    @Override
    public void apply(Board board)
    {
    }
    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", NAME)
                .add("port", port)
                .build();
    }
}

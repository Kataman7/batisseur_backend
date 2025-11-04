package org.domain.model;

import lombok.Getter;

import javax.json.Json;
import javax.json.JsonObject;

@Getter
public class Message implements Model
{
    private final String playerName;
    private final String content;

    public Message(String playerName, String content)
    {
        this.playerName = playerName;
        this.content = content;
    }
    @Override
    public JsonObject toJson()
    {
        return Json.createObjectBuilder()
                .add("expeditor", playerName)
                .add("content", content)
                .build();
    }
}

package org.domain.events;

import lombok.Getter;
import org.domain.model.Board;
import org.domain.rules.GameRule;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class GameEvent
{
    private final List<GameRule> rules;

    public GameEvent()
    {
        rules = new ArrayList<>();
    }

    public abstract void apply(Board board);

    public abstract JsonObject toJson();


}
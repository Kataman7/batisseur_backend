package org.domain.events.game;

import org.domain.model.Board;
import org.domain.rules.GameRule;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public abstract class GameEvent
{
    private final List<GameRule> rules;

    public GameEvent()
    {
        rules = new ArrayList<>();
    }

    public abstract void apply(Board board);

    public List<GameRule> getRules() {
        return rules;
    }

    public boolean requireNextTurnEvent() {
        return false;
    }

    public abstract JsonObject toJson();
}
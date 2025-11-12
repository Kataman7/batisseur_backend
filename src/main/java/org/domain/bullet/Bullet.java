package org.domain.bullet;

import lombok.Getter;
import lombok.Setter;
import org.domain.event.GameEvent;
import org.domain.model.Board;
import org.domain.model.Model;
import org.domain.rule.GameRule;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Bullet implements Model
{

    public abstract void apply(Board board);

    @Override
    public JsonObject toJson() {
        return null;
    }
}

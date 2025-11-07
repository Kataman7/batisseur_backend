package org.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.json.JsonObject;

@Getter
@Setter
public class Bullet implements Model
{
    private final boolean deadly;

    public Bullet(boolean deadly) {
        this.deadly = deadly;
    }

    @Override
    public JsonObject toJson() {
        return null;
    }
}

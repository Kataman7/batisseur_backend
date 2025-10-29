package org.domain.model;

import javax.json.Json;
import javax.json.JsonObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Build implements Model {
    private final String name;
    private int reward;
    private final Ressources ressources;
    private final int sizeX;
    private final int sizeY;
    private final boolean replaceable;
    private boolean isInfected = false;

    public Build(String name, int coast, int[] ressources, int sizeX, int sizeY, boolean isreplaceable) {
        this.name = name;
        this.reward = coast;
        this.ressources = new Ressources(ressources);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.replaceable = isreplaceable;
    }

    public int clearReward() {
        int r = reward;
        reward = 0;
        return r;
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("name", name)
                .add("reward", reward)
                .add("ressources", ressources.toJson())
                .add("sizeX", sizeX)
                .add("sizeY", sizeY)
                .add("isreplaceable", replaceable)
                .add("isInfected", isInfected)
                .build();
    }
}

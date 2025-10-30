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
    private int cost;
    private final Ressources ressources;
    private final int sizeX;
    private final int sizeY;
    private final boolean replaceable;
    private boolean isInfected = false;

    public Build(String name, int reward, int cost, int[] ressources, int sizeX, int sizeY, boolean isreplaceable) {
        this.name = name;
        this.reward = reward;
        this.cost = cost;
        this.ressources = new Ressources(ressources);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.replaceable = isreplaceable;
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

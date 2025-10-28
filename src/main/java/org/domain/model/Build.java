package org.domain.model;

import javax.json.Json;
import javax.json.JsonObject;

public class Build implements Model {
    private final String name;
    private int reward;
    private final Ressources ressources;
    private final int sizeX;
    private final int sizeY;
    private final boolean isreplaceable;
    private boolean isInfected = false;

    public Build(String name, int coast, int[] ressources, int sizeX, int sizeY, boolean isreplaceable) {
        this.name = name;
        this.reward = coast;
        this.ressources = new Ressources(ressources);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.isreplaceable = isreplaceable;
    }
    public String getName() {
        return name;
    }
    public int getReward() {
        return reward;
    }
    public int clearReward() {
        int r = reward;
        reward = 0;
        return r;
    }
    public Ressources getRessources() {
        return ressources;
    }
    public int getSizeX() {
        return sizeX;
    }
    public int getSizeY() {
        return sizeY;
    }
    public boolean isReplaceable() {
        return isreplaceable;
    }
    public boolean isBuilt() {
        return ressources.isEmpty();
    }
    public boolean isInfected() {
        return isInfected;
    }
    public void setInfected(boolean infected) {
        isInfected = infected;
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("name", name)
                .add("reward", reward)
                .add("ressources", ressources.toJson())
                .add("sizeX", sizeX)
                .add("sizeY", sizeY)
                .add("isreplaceable", isreplaceable)
                .add("isInfected", isInfected)
                .build();
    }
}

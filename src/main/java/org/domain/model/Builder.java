package org.domain.model;

import javax.json.Json;
import javax.json.JsonObject;

public class Builder implements Model {
    private String name;
    private int cost;
    private final Ressources ressources;
    private Build assignedBuild = null;
    private boolean isInfected = false;

    public Builder(String name, int cost, int[] ressources) {
        this.name = name;
        this.cost = cost;
        this.ressources = new Ressources(ressources);
    }
    public String getName() {
        return name;
    }
    public int getCost() {
        return cost;
    }
    public Ressources getRessources() {
        return ressources;
    }
    public Build getAssignedBuild() {
        return assignedBuild;
    }
    public void setAssignedBuild(Build assignedBuild) {
        this.assignedBuild = assignedBuild;
    }
    public boolean isInfected() {
        return isInfected;
    }
    public void setInfected(boolean infected) {
        isInfected = infected;
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = Json.createObjectBuilder()
                .add("name", name)
                .add("cost", cost)
                .add("ressources", ressources.toJson())
                .build();
        if (assignedBuild != null) {
            json = Json.createObjectBuilder(json)
                    .add("assignedBuild", assignedBuild.toJson())
                    .build();
        }
        return json;
    }
}

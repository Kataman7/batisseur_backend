package org.domain.model;

import lombok.Getter;
import lombok.Setter;
import javax.json.Json;
import javax.json.JsonObject;

@Getter
@Setter
public class Builder implements Model
{
    private String name;
    private int cost;
    private final Ressources ressources;
    private Build assignedBuild = null;
    private boolean isInfected = false;

    public Builder(String name, int cost, int[] ressources)
    {
        this.name = name;
        this.cost = cost;
        this.ressources = new Ressources(ressources);
    }

    @Override
    public JsonObject toJson()
    {
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

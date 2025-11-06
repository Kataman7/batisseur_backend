package org.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.domain.enums.Phases;
import javax.json.*;
import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter
public class Player implements Model
{
    private final String name;
    private int score;
    private ArrayList<Build> builds;
    private ArrayList<Builder> builders;
    private int money;
    private Phases playedPhase;
    private String secretToken;

    public Player(String name)
    {
        this.name = name;
        score = 0;
        builds = new ArrayList<>();
        builders = new ArrayList<>();
        money = 0;
        this.secretToken = java.util.UUID.randomUUID().toString().replace("-", "");;
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);  //compare uniquement le nom lors des égalités
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
    public Build getBuildByName(String name)
    {
        for (Build build : builds)
        {
            if (build.getName().equals(name)) {
                return build;
            }
        }
        return null;
    }
    public Builder getBuilderByName(String name)
    {
        for (Builder builder : builders) {
            if (builder.getName().equals(name)) {
                return builder;
            }
        }
        return null;
    }
    @Override
    public JsonObject toJson()
    {
        JsonArrayBuilder buildsArray = Json.createArrayBuilder();
        for (Build build : builds) {
            buildsArray.add(build.toJson());
        }
        JsonArrayBuilder buildersArray = Json.createArrayBuilder();
        for (Builder builder : builders) {
            buildersArray.add(builder.toJson());
        }
        return Json.createObjectBuilder()
                .add("name", name)
                .add("score", score)
                .add("money", money)
                .add("builds", buildsArray)
                .add("builders", buildersArray)
                .build();
    }
}
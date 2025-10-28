package org.domain.model;

import javax.json.*;
import java.util.ArrayList;
import java.util.Objects;

public class Player implements Model
{
    private final String name;
    private int score;
    private ArrayList<Build> builds;
    private ArrayList<Builder> builders;
    private int money;

    public Player(String name)
    {
        this.name = name;
        score = 0;
        builds = new ArrayList<>();
        builders = new ArrayList<>();
        money = 0;
    }
    public String getName()
    {
        return name;
    }
    public int getScore()
    {
        return score;
    }
    public void setScore(int score)
    {
        this.score = score;
    }
    //compare uniquement le nom lors des égalités
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }

    public ArrayList<Build> getBuilds() {
        return builds;
    }
    public Build getBuildByName(String name) {
        for (Build build : builds) {
            if (build.getName().equals(name)) {
                return build;
            }
        }
        return null;
    }
    public ArrayList<Builder> getBuilders() {
        return builders;
    }
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Builder getBuilderByName(String name) {
        for (Builder builder : builders) {
            if (builder.getName().equals(name)) {
                return builder;
            }
        }
        return null;
    }

    @Override
    public JsonObject toJson() {
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
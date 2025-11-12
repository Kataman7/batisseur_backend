package org.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.domain.bullet.BlankBullet;
import org.domain.bullet.Bullet;
import org.domain.bullet.LethalBullet;

import javax.json.*;
import java.util.*;

@Getter
@Setter
public class Player implements Model
{
    private final String name;
    private int score;
    private String secretToken;
    private boolean skipping;
    private boolean dead;
    private final List<Bullet> unloadedBullets;
    private final List<Bullet> loadedBullets;

    public Player(String name)
    {
        this.name = name;
        score = 0;
        secretToken = java.util.UUID.randomUUID().toString().replace("-", "");;
        skipping = false;
        dead = false;

        unloadedBullets = new ArrayList<>();
        loadedBullets = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            unloadedBullets.add(new BlankBullet());
        unloadedBullets.add(new LethalBullet());
    }

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

    @Override
    public JsonObject toJson()
    {
        return Json.createObjectBuilder()
                .add("name", name)
                .add("score", score)
                .add("skipping", skipping)
                .add("dead", dead)
                .add("loadedBulletsCount", loadedBullets.size())
                .add("unloadedBulletsCount", unloadedBullets.size())
                .build();
    }
}
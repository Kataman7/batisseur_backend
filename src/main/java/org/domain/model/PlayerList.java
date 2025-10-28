package org.domain.model;

import org.utils.CircularList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class PlayerList extends CircularList<Player> implements Model
{
    public Player getByName(String name)
    {
        return super.items.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
    @Override
    public JsonObject toJson() {
        JsonArrayBuilder playersArray = Json.createArrayBuilder();
        for (Player player : items) {
            playersArray.add(player.toJson());
        }
        return Json.createObjectBuilder()
                .add("players", playersArray)
                .build();
    }
}

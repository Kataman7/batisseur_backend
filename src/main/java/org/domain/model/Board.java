package org.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.domain.enums.Phases;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;

@Getter
@Setter
public class Board implements Model {

    private final PlayerList players;
    private Player winner;
    private final ArrayList<Build> builds;
    private final ArrayList<Builder> builders;
    private final int deckNumber;
    private Phases phase;
    private int playedPlayersCount;
    private final Chat chat;
    private final GameMap gameMap;

    public Board()
    {
        players = new PlayerList();
        winner = null;
        builds = new ArrayList<>();
        builders = new ArrayList<>();
        phase = Phases.LOBBY;
        chat = new Chat();

        playedPlayersCount = 0;
        gameMap = new GameMap(40, 40);
        deckNumber = 4;
    }

    public ArrayList<Builder> getBuidlerDeck()
    {
        ArrayList<Builder> res = new ArrayList<>();
        for (int i = 0; i < deckNumber; i++) {
            res.add(builders.get(i));
        }
        return res;
    }
    public ArrayList<Build> getBuildDeck()
    {
        ArrayList<Build> res = new ArrayList<>();
        for (int i = 0; i < deckNumber; i++) {
            res.add(builds.get(i));
        }
        return res;
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
        JsonObjectBuilder boardBuilder = Json.createObjectBuilder()
                .add("content", "boardUpdate")
                .add("players", players.toJson())
                .add("builds", buildsArray)
                .add("builders", buildersArray)
                .add("deckNumber", deckNumber)
                .add("phase", phase.name())
                .add("playedPlayersCount", playedPlayersCount)
                .add("chat", chat.toJson())
                .add("gameMap", gameMap.toJson());
        if (winner != null) {
            boardBuilder.add("winner", winner.toJson());
        } else {
            boardBuilder.addNull("winner");
        }
        return boardBuilder.build();
    }
}

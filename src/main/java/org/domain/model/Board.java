package org.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

@Getter
@Setter
public class Board implements Model {

    private final PlayerList players;
    private Player winner;
    private final Chat chat;
    private String adminPlayerName;
    private int betValue;
    private Player bettor;

    public Board()
    {
        players = new PlayerList();
        winner = null;
        chat = new Chat();
    }
    @Override
    public JsonObject toJson()
    {
        JsonObjectBuilder boardBuilder = Json.createObjectBuilder()
                .add("content", "boardUpdate")
                .add("players", players.toJson())
                .add("chat", chat.toJson());
        if (winner != null) {
            boardBuilder.add("winner", winner.toJson());
        } else {
            boardBuilder.addNull("winner");
        }
        return boardBuilder.build();
    }
}

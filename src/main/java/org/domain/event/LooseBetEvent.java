package org.domain.event;

import org.domain.model.Board;

import javax.json.Json;
import javax.json.JsonObject;

public class LooseBetEvent extends PlayerEvent
{
    private static final String NAME = "LooseBetEvent";

    private boolean dead;

    public LooseBetEvent(String playerName, String playerToken) {
        super(playerName, playerToken);
    }

    @Override
    public void apply(Board board) {
        board.getBettor().getUnloadedBullets()
                .addAll(board.getBettor().getLoadedBullets());
        board.getBettor().getLoadedBullets().clear();

        var unloaded = board.getBettor().getUnloadedBullets();
        if (!unloaded.isEmpty()) {
            int idx = (int) (Math.random() * unloaded.size());
            unloaded.remove(idx);
        }
        else {
            board.getBettor().setDead(true);
            this.dead = true;
        }
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", NAME)
                .add("player", getPlayerName())
                .add("isDead", dead)
                .build();
    }
}

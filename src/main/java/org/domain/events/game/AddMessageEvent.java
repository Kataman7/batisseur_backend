package org.domain.events.game;

import org.domain.model.Board;
import org.domain.model.Message;
import org.domain.rules.ValidPlayerRule;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class AddMessageEvent extends PlayerEvent
{
    private final Message message;

    public AddMessageEvent(String playerName, String messageContent) {
        super(playerName);
        this.message = new Message(playerName, messageContent);

        super.getRules().addAll(List.of(
                new ValidPlayerRule(getPlayerName()) // il faudra faire des rules pour traiter les messages
        ));
    }

    @Override
    public void apply(Board board) {
        board.getChat().addMessage(message);
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", "addMessage")
                .add("message", message.toJson())
                .build();
    }
}

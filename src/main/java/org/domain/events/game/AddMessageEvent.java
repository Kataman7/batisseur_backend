package org.domain.events.game;

import org.domain.model.Board;
import org.domain.model.Chat;
import org.domain.model.Message;
import org.domain.rules.ValidMessageLenghtRule;
import org.domain.rules.ValidPlayerRule;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class AddMessageEvent extends PlayerEvent
{
    private final Message message;

    public AddMessageEvent(String playerName, String messageContent)
    {
        super(playerName);
        this.message = new Message(playerName, messageContent);

        super.getRules().addAll(List.of(
                new ValidMessageLenghtRule(messageContent),
                new ValidPlayerRule(getPlayerName())
        ));
    }
    @Override
    public void apply(Board board)
    {
        Chat chat = board.getChat();
        if (chat.getMessages().size() >= Chat.MAX_MESSAGES) {
            chat.getMessages().remove(0);
        }
        chat.getMessages().add(message);
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

package org.domain.event;

import org.domain.model.Board;
import org.domain.model.Chat;
import org.domain.model.Message;
import org.domain.rule.ValidMessageLengthRule;
import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class AddMessageEvent extends PlayerEvent
{
    public static final String NAME = "AddMessageEvent";

    private final Message message;

    public AddMessageEvent(String playerName, String playerToken, String messageContent)
    {
        super(playerName, playerToken);
        this.message = new Message(playerName, messageContent);

        super.getRules().addAll(List.of(
                new ValidMessageLengthRule(messageContent)
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
                .add("event", NAME)
                .add("message", message.toJson())
                .build();
    }
}

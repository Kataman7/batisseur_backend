package org.domain.model;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;

public class Chat implements Model {
    private ArrayList<Message> messages;

    public Chat() {
        this.messages = new ArrayList<Message>();
    }
    public ArrayList<Message> getMessages() {
        return messages;
    }
    public void addMessage(Message message) {
        this.messages.add(message);
    }

    @Override
    public JsonObject toJson() {
        JsonArrayBuilder messagesArray = Json.createArrayBuilder();
        for (Message msg : messages) {
            messagesArray.add(msg.toJson());
        }
        return Json.createObjectBuilder()
                .add("messages", messagesArray)
                .build();
    }
}

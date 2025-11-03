package org.domain.event;

import lombok.Getter;

@Getter
public abstract class PlayerEvent extends GameEvent {
    private final String playerName;

    public PlayerEvent(String playerName)
    {
        this.playerName = playerName;
    }
}
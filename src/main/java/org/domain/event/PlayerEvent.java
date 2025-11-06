package org.domain.event;

import lombok.Getter;
import org.domain.rule.ValidPlayerRule;
import org.domain.rule.ValidPlayerTokenRule;

import java.util.List;

@Getter
public abstract class PlayerEvent extends GameEvent {
    private final String playerName;
    private final String playerToken;

    public PlayerEvent(String playerName, String playerToken)
    {
        this.playerName = playerName;
        this.playerToken = playerToken;

        getRules().addAll(List.of(
                    new ValidPlayerRule(playerName),
                    new ValidPlayerTokenRule(playerName, playerToken)
                )
        );
    }
}
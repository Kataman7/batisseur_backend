package org.domain.rule;

public abstract class AbstractPlayerRule implements GameRule
{
    private final String playerName;

    public AbstractPlayerRule(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}

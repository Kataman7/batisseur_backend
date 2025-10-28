package org.domain.rules;

import org.domain.enums.ErrorCodes;
import org.domain.model.Player;

public class PlayerHaveEnoughtMoneyRule extends PlayerRule{
    private final int amount;

    public PlayerHaveEnoughtMoneyRule(String playerName, int amount) {
        super(playerName);
        this.amount = amount;
    }

    @Override
    public boolean isApplicable(org.domain.model.Board board) {
        Player player = board.getPlayers().getByName(getPlayerName());
        return player.getMoney() >= amount;
    }

    @Override
    public int getCode() {
        return ErrorCodes.PLAYER_HAS_ENOUGHT_MONEY.ordinal();
    }
}

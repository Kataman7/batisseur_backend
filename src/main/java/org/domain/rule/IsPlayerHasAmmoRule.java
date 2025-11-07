package org.domain.rule;

import org.domain.model.Board;
import org.domain.model.Player;

public class IsPlayerHasAmmoRule extends AbstractPlayerRule
{

    public IsPlayerHasAmmoRule(String playerName) {
        super(playerName);
    }

    @Override
    public boolean isApplicable(Board board) {
        Player player = board.getPlayers().getByName(getPlayerName());
        return !player.getUnloadedBullets().isEmpty();
    }

    @Override
    public int getCode() {
        return 0;
    }
}

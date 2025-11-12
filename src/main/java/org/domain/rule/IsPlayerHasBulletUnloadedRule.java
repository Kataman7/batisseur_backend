package org.domain.rule;

import org.domain.model.Board;
import org.domain.model.Player;

public class IsPlayerHasBulletUnloadedRule extends AbstractPlayerRule
{

    public IsPlayerHasBulletUnloadedRule(String playerName) {
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

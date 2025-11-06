package org.domain.rule;

import org.domain.model.Board;
import org.domain.model.Player;

public class ValidPlayerTokenRule extends AbstractPlayerRule
{
    private final String playerToken;

    public ValidPlayerTokenRule(String playerName, String playerToken)
    {
        super(playerName);
        this.playerToken = playerToken;
    }

    @Override
    public boolean isApplicable(Board board) {
        Player player = board.getPlayers().getByName(super.getPlayerName());
        return player.getSecretToken().equals(this.playerToken) || System.getenv("GAME_SECRET_ADMIN_TOKEN").equals(playerToken);
    }

    @Override
    public int getCode() {
        return 0;
    }
}

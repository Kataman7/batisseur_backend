package org.domain.rule;

import org.domain.model.Board;
import org.domain.model.Player;

public class ValidPlayerBuildIndexRule extends AbstractPlayerRule
{
    private final int buildIndex;

    public ValidPlayerBuildIndexRule(String playerName, int buildIndex) {
        super(playerName);
        this.buildIndex = buildIndex;
    }

    @Override
    public boolean isApplicable(Board board) {
        Player player = board.getPlayers().getByName(getPlayerName());
        return buildIndex >= 0 && buildIndex < player.getBuilds().size();
    }

    @Override
    public int getCode() {
        return 0;
    }
}

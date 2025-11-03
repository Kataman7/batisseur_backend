package org.domain.rule;

import org.domain.model.Board;
import org.domain.model.Build;
import org.domain.model.Player;

public class IsBuildResourcesEmptyRule extends AbstractPlayerRule
{
    private final int buildIndex;

    public IsBuildResourcesEmptyRule(String playerName, int buildIndex) {
        super(playerName);
        this.buildIndex = buildIndex;
    }

    @Override
    public boolean isApplicable(Board board) {
        Player player = board.getPlayers().getByName(super.getPlayerName());
        Build build = player.getBuilds().get(buildIndex);
        return build.getResources().isEmpty();
    }

    @Override
    public int getCode() {
        return 0;
    }
}

package org.domain.rules;

import org.domain.model.Board;

public class ValidDeckBuildIndexRule implements GameRule {
    private final int buildIndex;

    public ValidDeckBuildIndexRule(int buildIndex) {
        this.buildIndex = buildIndex;
    }

    @Override
    public boolean isApplicable(Board board) {
        return buildIndex >= 0 && buildIndex < board.getDeckNumber();
    }

    @Override
    public int getCode() {
        return 0;
    }
}

package org.domain.rule;

import org.domain.model.Board;

public class IsDataPackLoadedRule implements GameRule
{
    @Override
    public boolean isApplicable(Board board) {
        return !board.getBuilders().isEmpty() && !board.getBuilds().isEmpty();
    }

    @Override
    public int getCode() {
        return 0;
    }
}

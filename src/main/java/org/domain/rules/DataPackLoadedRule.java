package org.domain.rules;

import org.domain.model.Board;

public class DataPackLoadedRule implements GameRule
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

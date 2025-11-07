package org.domain.rule;

import org.domain.model.Board;

public class isGameOverRule implements GameRule
{

    @Override
    public boolean isApplicable(Board board) {
        return board.isGameOver();
    }

    @Override
    public int getCode() {
        return 0;
    }
}

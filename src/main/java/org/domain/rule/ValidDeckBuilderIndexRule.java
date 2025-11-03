package org.domain.rule;

import org.domain.model.Board;

public class ValidDeckBuilderIndexRule implements GameRule
{
    private final int builderIndex;

    public ValidDeckBuilderIndexRule(int builderIndex) {
        this.builderIndex = builderIndex;
    }

    @Override
    public boolean isApplicable(Board board) {
        return builderIndex >= 0 && builderIndex < board.getDeckNumber();
    }

    @Override
    public int getCode() {
        return 0;
    }
}

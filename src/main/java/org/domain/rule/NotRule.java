package org.domain.rule;

import org.domain.model.Board;

public class NotRule implements GameRule
{
    private final GameRule rule;

    public NotRule(GameRule rule) {
        this.rule = rule;
    }

    @Override
    public boolean isApplicable(Board board) {
        return !rule.isApplicable(board);
    }

    @Override
    public int getCode() {
        return rule.getCode() * -1;
    }
}

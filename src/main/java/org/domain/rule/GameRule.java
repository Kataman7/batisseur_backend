package org.domain.rule;

import org.domain.model.Board;

//Format: [Valid/Is/Check][Condition/Entity]Rule
public interface GameRule
{
    boolean isApplicable (Board board);
    int getCode();
}

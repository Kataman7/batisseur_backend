package org.domain.rules;

import org.domain.model.Board;

public interface GameRule
{
    boolean isApplicable (Board board);
    int getCode();
}

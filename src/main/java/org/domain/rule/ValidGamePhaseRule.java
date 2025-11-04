package org.domain.rule;

import org.domain.enums.Phases;

public class ValidGamePhaseRule implements GameRule
{
    private final Phases phase;

    public ValidGamePhaseRule(Phases phase)
    {
        this.phase = phase;
    }
    @Override
    public boolean isApplicable(org.domain.model.Board board)
    {
        return board.getPhase().equals(phase);
    }
    @Override
    public int getCode() {
        return org.domain.enums.ErrorCodes.GAME_EMPTY.ordinal();
    }
}

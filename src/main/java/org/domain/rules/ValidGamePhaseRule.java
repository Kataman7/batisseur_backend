package org.domain.rules;

import org.domain.enums.Phases;

public class ValidGamePhaseRule implements GameRule
{
    private final Phases phases;

    public ValidGamePhaseRule(Phases phases)
    {
        this.phases = phases;
    }

    @Override
    public boolean isApplicable(org.domain.model.Board board)
    {
        return board.getPhase().equals(phases);
    }

    @Override
    public int getCode() {
        return org.domain.enums.ErrorCodes.GAME_EMPTY.ordinal();
    }
}

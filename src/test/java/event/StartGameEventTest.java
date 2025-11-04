package event;

import org.domain.enums.Phases;
import org.domain.event.JoinBoardEvent;
import org.domain.event.PassToNextPlayerEvent;
import org.domain.event.StartGameEvent;
import org.domain.model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartGameEventTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        (new JoinBoardEvent("bob")).apply(board);
        (new JoinBoardEvent("alice")).apply(board);
    }
    @Test
    public void shouldSetPhaseToBuyBuildersWhenPlayerIsAdmin()
    {
        (new StartGameEvent("bob")).apply(board);
        assertEquals(Phases.BUY_BUILDERS, board.getPhase());
    }
}

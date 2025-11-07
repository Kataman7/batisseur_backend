package event;

import org.domain.event.JoinBoardEvent;
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
        (new JoinBoardEvent("bob", "token1")).apply(board);
        (new JoinBoardEvent("alice", "token2")).apply(board);
    }
    @Test
    public void shouldSetPhaseToBuyBuildersWhenPlayerIsAdmin()
    {
        (new StartGameEvent("bob", "token1")).apply(board);
        assertEquals(Phases.BUY_BUILDERS, board.getPhase());
    }
}

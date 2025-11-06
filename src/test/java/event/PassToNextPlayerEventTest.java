package event;

import org.domain.enums.Phases;
import org.domain.event.JoinBoardEvent;
import org.domain.event.PassToNextPlayerEvent;
import org.domain.event.StartGameEvent;
import org.domain.model.Board;
import org.domain.rule.ValidBuildersDataRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PassToNextPlayerEventTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        (new JoinBoardEvent("bob", "token1")).apply(board);
        (new JoinBoardEvent("alice", "token2")).apply(board);
        (new StartGameEvent("bob", "token1")).apply(board);
    }
    @Test
    public void shouldPassTurnToNextPlayer()
    {
        (new PassToNextPlayerEvent("bob", "token1", Phases.BUY_BUILDERS.ordinal())).apply(board);
        assertEquals("alice", board.getPlayers().getCurrent().getName());
    }
    @Test
    public void shouldAdvancePhaseWhenAllPlayersHavePlayed()
    {
        (new PassToNextPlayerEvent("bob", "token1", Phases.BUY_BUILDERS.ordinal())).apply(board);
        (new PassToNextPlayerEvent("alice", "token2", Phases.BUY_BUILDERS.ordinal())).apply(board);
        assertEquals(Phases.BUY_BUILDS, board.getPhase());
    }
    @Test
    public void shouldNotAdvancePhaseWhenNotAllPlayersHavePlayed()
    {
        (new PassToNextPlayerEvent("bob", "token1", Phases.BUY_BUILDERS.ordinal())).apply(board);
        assertEquals(Phases.BUY_BUILDERS, board.getPhase());
    }
    @Test
    public void shouldReturnToFirstPlayerAfterAllPlayersHavePlayed()
    {
        (new PassToNextPlayerEvent("bob", "token1", Phases.BUY_BUILDERS.ordinal())).apply(board);
        (new PassToNextPlayerEvent("alice", "token2", Phases.BUY_BUILDERS.ordinal())).apply(board);
        assertEquals("bob", board.getPlayers().getCurrent().getName());
    }

}

package event;

import org.domain.event.JoinBoardEvent;
import org.domain.model.Board;
import org.domain.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JoinBoardEventTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
    }

    @Test
    public void shouldAddPlayerToBoard()
    {
        new JoinBoardEvent("Player1").apply(board);
        assertEquals(1, board.getPlayers().size());
        assertTrue(board.getPlayers().contains(new Player("Player1")));
    }

    @Test
    public void shouldSetAdminWhenFirstPlayerJoins()
    {
        new JoinBoardEvent("Player1").apply(board);
        assertEquals("Player1", board.getAdminPlayerName());
    }

    @Test
    public void shouldNotChangeAdminWhenNotFirstPlayer()
    {
        new JoinBoardEvent("Player1").apply(board);
        new JoinBoardEvent("Player2").apply(board);
        assertEquals("Player1", board.getAdminPlayerName());
    }
}

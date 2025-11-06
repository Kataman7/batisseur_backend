package event;

import org.domain.event.JoinBoardEvent;
import org.domain.event.LeaveBoardEvent;
import org.domain.model.Board;
import org.domain.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LeaveBoardEventTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        new JoinBoardEvent("Player1", "token1").apply(board);
        new JoinBoardEvent("Player2", "token2").apply(board);
    }

    @Test
    public void shouldRemovePlayerFromBoard()
    {
        new LeaveBoardEvent("Player2", "token2").apply(board);
        assertEquals(1, board.getPlayers().size());
        assertFalse(board.getPlayers().contains(new Player("Player2")));
    }

    @Test
    public void shouldChangeAdminWhenAdminLeaves()
    {
        new LeaveBoardEvent("Player1", "token1").apply(board);
        assertEquals("Player2", board.getAdminPlayerName());
    }

    @Test
    public void shouldNotChangeAdminWhenNonAdminLeaves()
    {
        new LeaveBoardEvent("Player2", "token2").apply(board);
        assertEquals("Player1", board.getAdminPlayerName());
    }
}

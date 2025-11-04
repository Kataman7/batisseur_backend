package event;

import org.domain.event.AddMessageEvent;
import org.domain.event.JoinBoardEvent;
import org.domain.model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddMessageEventTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        new JoinBoardEvent("Player1").apply(board);
    }

    @Test
    public void shouldAddMessageToChat()
    {
        new AddMessageEvent("Player1", "Hello world").apply(board);
        assertEquals(1, board.getChat().getMessages().size());
        assertEquals("Player1", board.getChat().getMessages().get(0).getPlayerName());
        assertEquals("Hello world", board.getChat().getMessages().get(0).getContent());
    }
}

package rule;

import org.domain.event.JoinBoardEvent;
import org.domain.model.Board;
import org.domain.rule.IsBoardEmptyRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsBoardEmptyRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
    }

    @Test
    public void shouldReturnTrueWhenBoardHasZeroPlayers()
    {
        assertTrue(new IsBoardEmptyRule().isApplicable(board));
    }

    @Test
    public void shouldReturnTrueWhenBoardHasOnePlayer()
    {
        new JoinBoardEvent("Player1", "token1").apply(board);
        assertTrue(new IsBoardEmptyRule().isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenBoardHasTwoPlayers()
    {
        new JoinBoardEvent("Player1", "token1").apply(board);
        new JoinBoardEvent("Player2", "token2").apply(board);
        assertFalse(new IsBoardEmptyRule().isApplicable(board));
    }
}

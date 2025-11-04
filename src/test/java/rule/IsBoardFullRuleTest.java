package rule;

import org.domain.model.Board;
import org.domain.model.Player;
import org.domain.rule.IsBoardFullRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsBoardFullRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
    }

    @Test
    public void shouldReturnTrueWhenBoardHasEightOrMorePlayers()
    {
        for (int i = 1; i <= 8; i++) {
            board.getPlayers().add(new Player("Player" + i));
        }
        assertTrue(new IsBoardFullRule().isApplicable(board));

        // Add one more to ensure >=8
        board.getPlayers().add(new Player("Player9"));
        assertTrue(new IsBoardFullRule().isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenBoardHasLessThanEightPlayers()
    {
        for (int i = 1; i <= 7; i++) {
            board.getPlayers().add(new Player("Player" + i));
        }
        assertFalse(new IsBoardFullRule().isApplicable(board));

        // Test with 0 players
        Board emptyBoard = new Board();
        assertFalse(new IsBoardFullRule().isApplicable(emptyBoard));
    }
}

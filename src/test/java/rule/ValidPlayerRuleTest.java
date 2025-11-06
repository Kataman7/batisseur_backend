package rule;

import org.domain.event.JoinBoardEvent;
import org.domain.model.Board;
import org.domain.rule.ValidPlayerRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidPlayerRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        (new JoinBoardEvent("Player1", "token1")).apply(board);
    }
    @Test
    public void shouldReturnTrueWhenPlayerExists()
    {
        assertTrue((new ValidPlayerRule("Player1")).isApplicable(board));
    }
    @Test void shouldReturnFalseWhenPlayerDoesNotExist()
    {
        assertFalse((new ValidPlayerRule("NonExistentPlayer")).isApplicable(board));
    }
}

package rule;

import org.domain.event.JoinBoardEvent;
import org.domain.model.Board;
import org.domain.rule.IsPlayerAdminRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsPlayerAdminRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        new JoinBoardEvent("Player1").apply(board);
        new JoinBoardEvent("Player2").apply(board);
    }

    @Test
    public void shouldReturnTrueWhenPlayerIsAdmin()
    {
        assertTrue(new IsPlayerAdminRule("Player1").isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenPlayerIsNotAdmin()
    {
        assertFalse(new IsPlayerAdminRule("Player2").isApplicable(board));
    }
}

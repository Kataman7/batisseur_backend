package rule;

import org.domain.event.JoinBoardEvent;
import org.domain.model.Board;
import org.domain.rule.IsPlayerTurnRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsPlayerTurnRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        new JoinBoardEvent("Player1", "token1").apply(board);
        new JoinBoardEvent("Player2", "token2").apply(board);
        new StartGameEvent("Player1", "token1").apply(board);
    }

    @Test
    public void shouldReturnTrueWhenItIsPlayerTurn()
    {
        assertTrue(new IsPlayerTurnRule("Player1").isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenItIsNotPlayerTurn()
    {
        assertFalse(new IsPlayerTurnRule("Player2").isApplicable(board));
    }
}

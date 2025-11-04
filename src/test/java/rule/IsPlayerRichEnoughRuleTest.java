package rule;

import org.domain.event.JoinBoardEvent;
import org.domain.model.Board;
import org.domain.rule.IsPlayerRichEnoughRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsPlayerRichEnoughRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        new JoinBoardEvent("Player1").apply(board);
        board.getPlayers().getByName("Player1").setMoney(10);
    }

    @Test
    public void shouldReturnTrueWhenPlayerHasEnoughMoney()
    {
        assertTrue(new IsPlayerRichEnoughRule("Player1", 5).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenPlayerDoesNotHaveEnoughMoney()
    {
        assertFalse(new IsPlayerRichEnoughRule("Player1", 15).isApplicable(board));
    }
}

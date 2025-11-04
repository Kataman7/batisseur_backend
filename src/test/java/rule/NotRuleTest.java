package rule;

import org.domain.model.Board;
import org.domain.model.Player;
import org.domain.rule.IsBoardEmptyRule;
import org.domain.rule.NotRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
    }

    @Test
    public void shouldReturnFalseWhenRuleIsApplicable()
    {
        assertFalse(new NotRule(new IsBoardEmptyRule()).isApplicable(board));
    }

    @Test
    public void shouldReturnTrueWhenRuleIsNotApplicable()
    {
        board.getPlayers().add(new Player("P1"));
        board.getPlayers().add(new Player("P2"));
        assertTrue(new NotRule(new IsBoardEmptyRule()).isApplicable(board));
    }
}

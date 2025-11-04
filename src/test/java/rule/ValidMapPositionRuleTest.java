package rule;

import org.domain.model.Board;
import org.domain.rule.ValidMapPositionRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidMapPositionRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
    }

    @Test
    public void shouldReturnTrueWhenMapPositionIsValid()
    {
        assertTrue(new ValidMapPositionRule(0, 0, 1, 1).isApplicable(board));
        assertTrue(new ValidMapPositionRule(39, 39, 1, 1).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenMapPositionIsInvalid()
    {
        assertFalse(new ValidMapPositionRule(40, 0, 1, 1).isApplicable(board));
        assertFalse(new ValidMapPositionRule(0, 40, 1, 1).isApplicable(board));
        assertFalse(new ValidMapPositionRule(0, 0, 41, 1).isApplicable(board));
    }
}

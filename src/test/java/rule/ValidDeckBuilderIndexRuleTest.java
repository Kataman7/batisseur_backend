package rule;

import org.domain.model.Board;
import org.domain.rule.ValidDeckBuilderIndexRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidDeckBuilderIndexRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
    }

    @Test
    public void shouldReturnTrueWhenBuilderIndexIsValid()
    {
        assertTrue(new ValidDeckBuilderIndexRule(0).isApplicable(board));
        assertTrue(new ValidDeckBuilderIndexRule(3).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenBuilderIndexIsInvalid()
    {
        assertFalse(new ValidDeckBuilderIndexRule(-1).isApplicable(board));
        assertFalse(new ValidDeckBuilderIndexRule(4).isApplicable(board));
    }
}

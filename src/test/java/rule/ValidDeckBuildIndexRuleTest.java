package rule;

import org.domain.model.Board;
import org.domain.rule.ValidDeckBuildIndexRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidDeckBuildIndexRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
    }

    @Test
    public void shouldReturnTrueWhenBuildIndexIsValid()
    {
        assertTrue(new ValidDeckBuildIndexRule(0).isApplicable(board));
        assertTrue(new ValidDeckBuildIndexRule(3).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenBuildIndexIsInvalid()
    {
        assertFalse(new ValidDeckBuildIndexRule(-1).isApplicable(board));
        assertFalse(new ValidDeckBuildIndexRule(4).isApplicable(board));
    }
}

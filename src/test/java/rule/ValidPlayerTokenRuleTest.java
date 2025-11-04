package rule;

import org.domain.model.Board;
import org.domain.rule.ValidPlayerTokenRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidPlayerTokenRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
    }

    @Test
    public void shouldReturnFalseWhenTokenIsInvalid()
    {
        assertFalse(new ValidPlayerTokenRule("Player1", "invalidtoken").isApplicable(board));
        assertFalse(new ValidPlayerTokenRule("Player1", "").isApplicable(board));
        assertFalse(new ValidPlayerTokenRule("Player1", null).isApplicable(board));
    }
}

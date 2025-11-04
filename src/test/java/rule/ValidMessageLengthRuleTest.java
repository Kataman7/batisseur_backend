package rule;

import org.domain.model.Board;
import org.domain.rule.ValidMessageLengthRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidMessageLengthRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
    }

    @Test
    public void shouldReturnTrueWhenMessageLengthIsValid()
    {
        assertTrue(new ValidMessageLengthRule("hello").isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenMessageIsNull()
    {
        assertFalse(new ValidMessageLengthRule(null).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenMessageIsEmpty()
    {
        assertFalse(new ValidMessageLengthRule("").isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenMessageIsTooLong()
    {
        String longMessage = "a".repeat(101);
        assertFalse(new ValidMessageLengthRule(longMessage).isApplicable(board));
    }
}

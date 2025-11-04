package rule;

import org.domain.enums.Phases;
import org.domain.model.Board;
import org.domain.rule.ValidGamePhaseRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidGamePhaseRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
    }

    @Test
    public void shouldReturnTrueWhenGamePhaseIsValid()
    {
        assertTrue(new ValidGamePhaseRule(Phases.LOBBY).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenGamePhaseIsInvalid()
    {
        assertFalse(new ValidGamePhaseRule(Phases.BUY_BUILDERS).isApplicable(board));
    }
}

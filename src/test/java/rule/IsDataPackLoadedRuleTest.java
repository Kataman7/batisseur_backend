package rule;

import org.domain.model.Board;
import org.domain.model.Build;
import org.domain.model.Builder;
import org.domain.rule.IsDataPackLoadedRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsDataPackLoadedRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
    }

    @Test
    public void shouldReturnFalseWhenDataPackIsNotLoaded()
    {
        assertFalse(new IsDataPackLoadedRule().isApplicable(board));
    }

    @Test
    public void shouldReturnTrueWhenDataPackIsLoaded()
    {
        board.getBuilders().add(new Builder("ouvrier", 1, new int[]{1, 2, 3}));
        board.getBuilds().add(new Build("maison", 10, 20, new int[]{1, 2, 3}, 1, 1, true));
        assertTrue(new IsDataPackLoadedRule().isApplicable(board));
    }
}

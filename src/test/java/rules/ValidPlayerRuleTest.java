package rules;

import org.domain.events.game.JoinGameEvent;
import org.domain.model.Board;
import org.domain.rules.ValidPlayerRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidPlayerRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        (new JoinGameEvent("Player1")).apply(board);
    }
    @Test
    public void shouldReturnTrueWhenPlayerExists()
    {
        assertTrue((new ValidPlayerRule("Player1")).isApplicable(board));
    }
    @Test void shouldReturnFalseWhenPlayerDoesNotExist()
    {
        assertFalse((new ValidPlayerRule("NonExistentPlayer")).isApplicable(board));
    }
}

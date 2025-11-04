package rule;

import org.domain.event.JoinBoardEvent;
import org.domain.model.Board;
import org.domain.model.Builder;
import org.domain.model.Player;
import org.domain.rule.ValidPlayerBuilderIndexRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidPlayerBuilderIndexRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        new JoinBoardEvent("Player1").apply(board);
        Player player = board.getPlayers().getByName("Player1");
        player.getBuilders().add(new Builder("ouvrier1", 1, new int[]{1}));
        player.getBuilders().add(new Builder("ouvrier2", 1, new int[]{1}));
        player.getBuilders().add(new Builder("ouvrier3", 1, new int[]{1}));
    }

    @Test
    public void shouldReturnTrueWhenBuilderIndexIsValid()
    {
        assertTrue(new ValidPlayerBuilderIndexRule("Player1", 1).isApplicable(board));
        assertTrue(new ValidPlayerBuilderIndexRule("Player1", 2).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenBuilderIndexIsInvalid()
    {
        assertFalse(new ValidPlayerBuilderIndexRule("Player1", 0).isApplicable(board));
        assertFalse(new ValidPlayerBuilderIndexRule("Player1", 3).isApplicable(board));
    }
}

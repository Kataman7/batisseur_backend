package rule;

import org.domain.event.JoinBoardEvent;
import org.domain.model.Board;
import org.domain.model.Build;
import org.domain.model.Player;
import org.domain.rule.ValidPlayerBuildIndexRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidPlayerBuildIndexRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        new JoinBoardEvent("Player1").apply(board);
        Player player = board.getPlayers().getByName("Player1");
        player.getBuilds().add(new Build("maison", 10, 20, new int[]{1}, 1, 1, true));
        player.getBuilds().add(new Build("chateau", 30, 40, new int[]{1}, 2, 2, false));
        player.getBuilds().add(new Build("ferme", 50, 60, new int[]{1}, 3, 3, true));
    }

    @Test
    public void shouldReturnTrueWhenBuildIndexIsValid()
    {
        assertTrue(new ValidPlayerBuildIndexRule("Player1", 0).isApplicable(board));
        assertTrue(new ValidPlayerBuildIndexRule("Player1", 1).isApplicable(board));
        assertTrue(new ValidPlayerBuildIndexRule("Player1", 2).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenBuildIndexIsInvalid()
    {
        assertFalse(new ValidPlayerBuildIndexRule("Player1", -1).isApplicable(board));
        assertFalse(new ValidPlayerBuildIndexRule("Player1", 3).isApplicable(board));
    }
}

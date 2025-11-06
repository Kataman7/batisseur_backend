package rule;

import org.domain.event.JoinBoardEvent;
import org.domain.model.Board;
import org.domain.model.Build;
import org.domain.model.Player;
import org.domain.rule.IsBuildResourcesEmptyRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsBuildResourcesEmptyRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        new JoinBoardEvent("Player1", "token1").apply(board);
        Player player = board.getPlayers().getByName("Player1");
        player.getBuilds().add(new Build("maison", 10, 20, new int[]{}, 1, 1, true));
        player.getBuilds().add(new Build("chateau", 30, 40, new int[]{1, 2, 3}, 2, 2, false));
    }

    @Test
    public void shouldReturnTrueWhenBuildResourcesAreEmpty()
    {
        assertTrue(new IsBuildResourcesEmptyRule("Player1", 0).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenBuildResourcesAreNotEmpty()
    {
        assertFalse(new IsBuildResourcesEmptyRule("Player1", 1).isApplicable(board));
    }
}

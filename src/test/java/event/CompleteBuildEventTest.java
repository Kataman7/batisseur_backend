package event;

import org.domain.enums.Phases;
import org.domain.event.AssignBuilderToBuildEvent;
import org.domain.event.CompleteBuildEvent;
import org.domain.event.JoinBoardEvent;
import org.domain.model.Board;
import org.domain.model.Build;
import org.domain.model.Builder;
import org.domain.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CompleteBuildEventTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        new JoinBoardEvent("Player1", "token1").apply(board);
        Player player = board.getPlayers().getByName("Player1");
        player.getBuilds().add(new Build("maison", 10, 20, new int[]{1, 2, 3, 4, 5}, 1, 1, true));
        player.getBuilders().add(new Builder("ouvrier", 1, new int[]{1, 2, 3, 4, 5}));
        board.setPhase(Phases.BUILD);
        new AssignBuilderToBuildEvent("Player1", "token1", 0, 0).apply(board);
    }

    @Test
    public void shouldCompleteBuildAndRewardPlayer()
    {
        int initialMoney = board.getPlayers().getByName("Player1").getMoney();
        new CompleteBuildEvent("Player1", "token1", 0).apply(board);
        Player player = board.getPlayers().getByName("Player1");
        assertEquals(initialMoney + 10, player.getMoney());
        assertEquals(0, player.getBuilds().get(0).getReward());
        assertNull(player.getBuilders().get(0).getAssignedBuild());
    }
}

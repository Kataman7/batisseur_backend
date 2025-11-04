package event;

import org.domain.enums.Phases;
import org.domain.event.JoinBoardEvent;
import org.domain.event.PlaceBuildEvent;
import org.domain.model.Board;
import org.domain.model.Build;
import org.domain.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlaceBuildEventTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        new JoinBoardEvent("Player1").apply(board);
        Player player = board.getPlayers().getByName("Player1");
        player.getBuilds().add(new Build("maison", 10, 20, new int[]{}, 1, 1, true));
        board.setPhase(Phases.BUILD);
    }

    @Test
    public void shouldPlaceBuildOnMap()
    {
        new PlaceBuildEvent("Player1", 0, 0, 0, 1, 1).apply(board);
        assertNotNull(board.getGameMap().getCell(0, 0).build);
    }
}

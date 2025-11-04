package event;

import org.domain.enums.Phases;
import org.domain.event.BuyBuildEvent;
import org.domain.event.JoinBoardEvent;
import org.domain.event.StartGameEvent;
import org.domain.model.Board;
import org.domain.model.Build;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyBuildEventTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        new JoinBoardEvent("Player1").apply(board);
        board.getBuilds().add(new Build("maison", 10, 20, new int[]{1, 2, 3, 4, 5}, 1, 1, true));
        new StartGameEvent("Player1").apply(board);
        board.setPhase(Phases.BUY_BUILDS);
        board.getPlayers().getByName("Player1").setMoney(20);
    }

    @Test
    public void shouldBuyBuildAndDeductMoney()
    {
        int initialMoney = board.getPlayers().getByName("Player1").getMoney();
        int cost = board.getBuilds().get(0).getCost();
        new BuyBuildEvent("Player1", 0, cost).apply(board);
        assertEquals(1, board.getPlayers().getByName("Player1").getBuilds().size());
        assertEquals(initialMoney - cost, board.getPlayers().getByName("Player1").getMoney());
    }
}

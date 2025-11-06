package event;

import org.domain.event.InfectBuilderEvent;
import org.domain.event.JoinBoardEvent;
import org.domain.event.StartGameEvent;
import org.domain.model.Board;
import org.domain.model.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InfectBuilderEventTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        new JoinBoardEvent("Player1", "token1").apply(board);
        board.getBuilders().add(new Builder("ouvrier", 1, new int[]{2, 3, 4, 5}));
        new StartGameEvent("Player1", "token1").apply(board);
        board.getPlayers().getByName("Player1").setMoney(10);
    }

    @Test
    public void shouldInfectBuilderAndDeductMoney()
    {
        int initialMoney = board.getPlayers().getByName("Player1").getMoney();
        int cost = board.getBuilders().get(0).getCost();
        new InfectBuilderEvent("Player1", "token1", 0, cost).apply(board);
        assertTrue(board.getBuilders().get(0).isInfected());
        assertEquals(initialMoney - cost, board.getPlayers().getByName("Player1").getMoney());
    }
}

package event;

import org.domain.event.BuyBuilderEvent;
import org.domain.event.JoinBoardEvent;
import org.domain.event.StartGameEvent;
import org.domain.model.Board;
import org.domain.model.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyBuilderEventTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        new JoinBoardEvent("Player1").apply(board);
        board.getBuilders().add(new Builder("ouvrier", 1, new int[]{2, 3, 4, 5}));
        board.getBuilders().add(new Builder("apprenti", 0, new int[]{1, 2, 3, 4}));
        new StartGameEvent("Player1").apply(board);
        board.getPlayers().getByName("Player1").setMoney(10);
    }

    @Test
    public void shouldBuyBuilderAndDeductMoney()
    {
        int initialMoney = board.getPlayers().getByName("Player1").getMoney();
        int cost = board.getBuilders().get(0).getCost();
        new BuyBuilderEvent("Player1", 0, cost).apply(board);
        assertEquals(1, board.getPlayers().getByName("Player1").getBuilders().size());
        assertEquals(initialMoney - cost, board.getPlayers().getByName("Player1").getMoney());
    }
}

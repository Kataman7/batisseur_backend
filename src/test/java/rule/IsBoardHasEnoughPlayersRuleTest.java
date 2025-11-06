package rule;

import org.domain.event.JoinBoardEvent;
import org.domain.model.Board;
import org.domain.rule.IsBoardHasEnoughPlayersRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsBoardHasEnoughPlayersRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        new JoinBoardEvent("Player1", "token1").apply(board);
        new JoinBoardEvent("Player2", "token2").apply(board);
        new JoinBoardEvent("Player3", "token3").apply(board);
    }

    @Test
    public void shouldReturnTrueWhenBoardHasEnoughPlayers()
    {
        assertTrue(new IsBoardHasEnoughPlayersRule(2).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenBoardDoesNotHaveEnoughPlayers()
    {
        assertFalse(new IsBoardHasEnoughPlayersRule(4).isApplicable(board));
    }
}

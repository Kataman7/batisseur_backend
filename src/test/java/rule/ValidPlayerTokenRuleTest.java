package rule;

import org.domain.model.Board;
import org.domain.model.Player;
import org.domain.rule.ValidPlayerTokenRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidPlayerTokenRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        Player player = new Player("Player1");
        player.setSecretToken("token1");
        board.getPlayers().add(player);
    }

    @Test
    public void shouldReturnTrueWhenTokenIsValid()
    {
        assertTrue(new ValidPlayerTokenRule("Player1", "token1").isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenTokenIsInvalid()
    {
        assertFalse(new ValidPlayerTokenRule("Player1", "invalidtoken").isApplicable(board));
        assertFalse(new ValidPlayerTokenRule("Player1", "").isApplicable(board));
        assertFalse(new ValidPlayerTokenRule("Player1", null).isApplicable(board));
    }

    @Test
    public void shouldReturnTrueForAdminToken()
    {
        String adminToken = System.getenv("GAME_SECRET_ADMIN_TOKEN");
        assertTrue(new ValidPlayerTokenRule("Player1", adminToken).isApplicable(board));
    }
}

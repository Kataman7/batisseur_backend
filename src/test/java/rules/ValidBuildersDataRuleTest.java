package rules;

import org.domain.model.Board;
import org.domain.rules.ValidBuildersDataRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidBuildersDataRuleTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void shouldReturnTrueWhenBuildersDataAreValid() {
        String[][] buildersData = {
                {"ouvrier", "1", "2", "3", "4", "5"},
                {"apprenti", "0", "1", "2", "3", "4"}
        };
        assertTrue(new ValidBuildersDataRule(buildersData).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenBuildersNameHaveMajusculeLetters() {
        String[][] buildersData = {
                {"Ouvrier", "1", "2", "3", "4", "5"},
                {"apprenti", "0", "1", "2", "3", "4"}
        };
        assertFalse(new ValidBuildersDataRule(buildersData).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenBuildersDataHaveInvalidNumbers() {
        String[][] buildersData = {
                {"ouvrier", "un", "2", "3", "4", "5"},
                {"apprenti", "0", "1", "2", "3", "4"}
        };
        assertFalse(new ValidBuildersDataRule(buildersData).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenBuildersDataAreMissing() {
        String[][] buildersData = {
                {"ouvrier", "1", "2", "3", "4", "5"},
                {"apprenti", "0", "1", "2", "3"}
        };
        assertFalse(new ValidBuildersDataRule(buildersData).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenBuildersDataAreNull() {
        String[][] buildersData = null;
        assertFalse(new ValidBuildersDataRule(buildersData).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenBuildersDataAreEmpty() {
        String[][] buildersData = {};
        assertFalse(new ValidBuildersDataRule(buildersData).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenBuildersDataHaveNullValues() {
        String[][] buildersData = {
                {"ouvrier", "1", "2", "3", "4", "5"},
                {"apprenti", null, "1", "2", "3", "4"}
        };
        assertFalse(new ValidBuildersDataRule(buildersData).isApplicable(board));
    }

    @Test
    public void shouldReturnFalseWhenBuildersCostIsNegative() {
        String[][] buildersData = {
                {"ouvrier", "-1", "2", "3", "4", "5"},
                {"apprenti", "0", "1", "2", "3", "4"}
        };
        assertFalse(new ValidBuildersDataRule(buildersData).isApplicable(board));
    }
}

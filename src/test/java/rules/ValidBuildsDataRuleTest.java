package rules;

import org.domain.model.Board;
import org.domain.rule.ValidBuildsDataRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidBuildsDataRuleTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
    }
    @Test
    public void shouldReturnTrueWhenBuildsDataAreValid()
    {

        String[][] buildsData = {
                {"maison", "1", "1", "10", "20", "1", "2", "3", "4" },
                {"chateau", "2", "2", "30", "40", "1", "2", "3", "4" },
                {"ferme", "3", "3", "50", "60", "1", "2", "3", "4" },
        };

        assertTrue((new ValidBuildsDataRule(buildsData).isApplicable(board)));
    }
    @Test
    public void shouldReturnFalseWhenBuildsNameHaveMajusculeLetters()
    {

        String[][] buildsData = {
                {"Maison", "1", "1", "10", "20", "1", "2", "3", "4" },
                {"chateau", "2", "2", "30", "40", "1", "2", "3", "4" },
                {"ferme", "3", "3", "50", "60", "1", "2", "3", "4" },
        };

        assertFalse((new ValidBuildsDataRule(buildsData).isApplicable(board)));
    }
    @Test
    public void shouldReturnFalseWhenBuildsDataHaveInvalidNumbers()
    {
        String[][] buildsData = {
                {"maison", "one", "1", "10", "20", "1", "2", "3", "4" },
                {"chateau", "2", "2", "30", "40", "1", "2", "3", "4" },
                {"ferme", "3", "3", "50", "60", "1", "2", "3", "4" },
        };

        assertFalse((new ValidBuildsDataRule(buildsData).isApplicable(board)));
    }
    @Test
    public void shouldReturnFalseWhenBuildsDataAreMissing()
    {
        String[][] buildsData = {
                {"maison", "1", "1", "10", "20", "1", "2", "3", "4" },
                {"chateau", "2", "2", "30", "40", "1", "2", "3", "4" },
                {"ferme", "3", "3", "50", "1", "2", "3", "4"},
        };

        assertFalse((new ValidBuildsDataRule(buildsData).isApplicable(board)));
    }
    @Test
    public void shouldReturnFalseWhenBuildsDataAreNull()
    {
        String[][] buildsData = null;

        assertFalse((new ValidBuildsDataRule(buildsData).isApplicable(board)));
    }
    @Test
    public void shouldReturnFalseWhenBuildsDataAreEmpty()
    {
        String[][] buildsData = {};

        assertFalse((new ValidBuildsDataRule(buildsData).isApplicable(board)));
    }
    @Test
    public void shouldReturnFalseWhenBuildsDataHaveNullValues()
    {
        String[][] buildsData = {
                {"maison", "1", "1", "10", "20", "1", "2", "3", "4" },
                {"chateau", "1", "2", "30", "40", "1", "2", null, "4" },
                {"ferme", "3", "3", null, "60", "1", "2", "3", "4" },
        };

        assertFalse((new ValidBuildsDataRule(buildsData).isApplicable(board)));
    }
    @Test
    public void shouldReturnFalseWhenBuildsDataSizeXorYareZero()
    {
        String[][] buildsData = {
                {"maison", "1", "0", "10", "20", "1", "2", "3", "4" },
                {"chateau", "2", "2", "30", "40", "1", "2", "3", "4" },
                {"ferme", "3", "3", "50", "60", "1", "2", "3", "4" },
        };

        assertFalse((new ValidBuildsDataRule(buildsData).isApplicable(board)));
    }
}

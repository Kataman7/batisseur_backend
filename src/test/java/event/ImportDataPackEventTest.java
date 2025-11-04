package event;

import org.domain.event.ImportDataPackEvent;
import org.domain.event.JoinBoardEvent;
import org.domain.model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImportDataPackEventTest
{
    private Board board;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        new JoinBoardEvent("Player1").apply(board);
    }

    @Test
    public void shouldImportBuildsAndBuilders()
    {
        String[][] buildsData = {
                {"maison", "10", "20", "1", "2", "3", "4", "5", "1", "1", "true"},
                {"chateau", "30", "40", "2", "3", "4", "5", "6", "2", "2", "false"}
        };
        String[][] buildersData = {
                {"ouvrier", "1", "2", "3", "4", "5"},
                {"apprenti", "0", "1", "2", "3", "4"}
        };
        new ImportDataPackEvent("Player1", buildsData, buildersData).apply(board);
        assertEquals(2, board.getBuilds().size());
        assertEquals(2, board.getBuilders().size());
    }
}

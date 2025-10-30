package org.domain.events.game;

import org.domain.enums.Phases;
import org.domain.model.Board;
import org.domain.model.Build;
import org.domain.model.Ressources;
import org.domain.rules.*;

import javax.json.JsonObject;
import java.util.Collections;
import java.util.List;

public class ImportDataPackEvent extends PlayerEvent
{
    private final String[][] buildsData;
    private final String[][] buildersData;

    public ImportDataPackEvent(String playerName, String[][] buildsData, String[][] buildersData)
    {
        super(playerName);
        this.buildsData = buildsData;
        this.buildersData = buildersData;

        super.getRules().addAll(List.of(
                new ValidGamePhaseRule(Phases.LOBBY),
                new ValidPlayerRule(playerName),
                new PlayerAdminRule(getPlayerName()),
                new ValidBuildersDataRule(buildersData),
                new ValidBuildsDataRule(buildsData)
        ));
    }

    @Override
    public void apply(Board board)
    {
        for (String[] buildData : buildsData)
        {
            String name = buildData[0];
            int sizeX = Integer.parseInt(buildData[1]);
            int sizeY = Integer.parseInt(buildData[2]);
            int cost = Integer.parseInt(buildData[3]);
            int reward = Integer.parseInt(buildData[4]);
            int[] resources = {
                    Integer.parseInt(buildData[5]),
                    Integer.parseInt(buildData[6]),
                    Integer.parseInt(buildData[7]),
                    Integer.parseInt(buildData[8])
            };
            Build build = new Build(name, reward, cost, resources, sizeX, sizeY, true);
            board.getBuilds().add(build);
        }
        Collections.shuffle(board.getBuilds());
    }

    @Override
    public JsonObject toJson() {
        return null;
    }
}

package org.domain.event;

import org.domain.enums.Phases;
import org.domain.model.Board;
import org.domain.model.Build;
import org.domain.model.Builder;
import org.domain.rule.*;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.Collections;
import java.util.List;

public class ImportDataPackEvent extends PlayerEvent
{
    public static final String NAME = "ImportDataPackEvent";

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
                new IsPlayerAdminRule(getPlayerName()),
                new ValidBuildersDataRule(buildersData),
                new ValidBuildsDataRule(buildsData)
        ));
    }
    @Override
    public void apply(Board board)
    {
        for (String[] buildData : buildsData)
        {
            Build build = getBuildFromData(buildData);
            board.getBuilds().add(build);
        }
        Collections.shuffle(board.getBuilds());

        for (String[] builderData : buildersData)
        {
            Builder builder = getBuilderFromData(builderData);
            board.getBuilders().add(builder);
        }
        Collections.shuffle(board.getBuilders());
    }
    private Build getBuildFromData(String[] buildData)
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
        return new Build(name, reward, cost, resources, sizeX, sizeY, true);
    }
    private Builder getBuilderFromData(String[] builderData)
    {
        String name = builderData[0];
        int cost = Integer.parseInt(builderData[1]);
        int[] resources = {
                Integer.parseInt(builderData[2]),
                Integer.parseInt(builderData[3]),
                Integer.parseInt(builderData[4]),
                Integer.parseInt(builderData[5])
        };
        return new Builder(name, cost, resources);
    }
    @Override
    public JsonObject toJson()
    {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", NAME)
                .build();
    }
}

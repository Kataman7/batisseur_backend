package org.domain.events;

import org.domain.enums.Phases;
import org.domain.model.Board;
import org.domain.model.Build;
import org.domain.model.GameMap;
import org.domain.model.Player;
import org.domain.rules.*;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class PlaceBuildEvent extends PlayerEvent {
    public static final String NAME = "PlaceBuildEvent";

    private final int buildIndex;
    private final int x;
    private final int y;

    public PlaceBuildEvent(String playerName, int buildIndex, int x, int y, int sizeX, int sizeY)
    {
        super(playerName);
        this.buildIndex = buildIndex;
        this.x = x;
        this.y = y;

        super.getRules().addAll(List.of(
                new ValidGamePhaseRule(Phases.BUILD),
                new ValidPlayerRule(playerName),
                new ValidPlayerBuildIndexRule(playerName, buildIndex),
                new ValidMapPositionRule(x, y, sizeX, sizeY)
        ));
    }
    @Override
    public void apply(Board board)
    {
        Player player = board.getPlayers().getByName(getPlayerName());
        GameMap gameMap = board.getGameMap();
        Build build = player.getBuilds().get(buildIndex);

        for (int i = 0; i < build.getSizeX(); i++) {
            for (int j = 0; j < build.getSizeY(); j++) {
                int xi = x + i;
                int yj = y + j;
                boolean isOrigin = (i == 0 && j == 0);
                gameMap.setCell(xi, yj, new GameMap.BuildCell(build, isOrigin));
            }
        }
    }
    @Override
    public JsonObject toJson()
    {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", NAME)
                .add("player", getPlayerName())
                .add("buildIndex", buildIndex)
                .add("x", x)
                .add("y", y)
                .build();
    }
}

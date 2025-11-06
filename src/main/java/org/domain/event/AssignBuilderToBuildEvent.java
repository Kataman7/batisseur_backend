package org.domain.event;

import org.domain.enums.Phases;
import org.domain.model.Board;
import org.domain.model.Build;
import org.domain.model.Builder;
import org.domain.model.Player;
import org.domain.rule.*;
import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class AssignBuilderToBuildEvent extends PlayerEvent{

    public static final String NAME = "AssignBuilderToBuildEvent";

    private final int buildIndex;
    private final int builderIndex;

    public AssignBuilderToBuildEvent(String playerName, String playerToken, int buildIndex, int builderIndex) {
        super(playerName, playerToken);
        this.buildIndex = buildIndex;
        this.builderIndex = builderIndex;
        super.getRules().addAll(List.of(
                new ValidGamePhaseRule(Phases.BUILD),
                new NotRule(new IsBuildResourcesEmptyRule(playerName, buildIndex)),
                new ValidPlayerBuildIndexRule(getPlayerName(), buildIndex),
                new ValidPlayerBuilderIndexRule(getPlayerName(), builderIndex)));
    }
    @Override
    public void apply(Board board) {
        Player player = board.getPlayers().getByName(getPlayerName());
        Build build = player.getBuilds().get(buildIndex);
        Builder builder = player.getBuilders().get(builderIndex);

        build.getResources().remove(builder.getResources());
        if (builder.isInfected()) builder.setInfected(true);
        builder.setAssignedBuild(build);
    }
    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", NAME)
                .add("player", getPlayerName())
                .add("buildIndex", buildIndex)
                .add("builderIndex", builderIndex)
                .build();
    }
}

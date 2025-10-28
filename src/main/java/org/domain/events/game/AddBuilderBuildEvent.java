package org.domain.events.game;

import org.domain.enums.Phases;
import org.domain.model.Board;
import org.domain.model.Build;
import org.domain.model.Builder;
import org.domain.model.Player;
import org.domain.rules.*;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class AddBuilderBuildEvent extends PlayerEvent{

    private final int buildIndex;
    private final int builderIndex;

    public AddBuilderBuildEvent(String playerName, int buildIndex, int builderIndex) {
        super(playerName);
        this.buildIndex = buildIndex;
        this.builderIndex = builderIndex;
        super.getRules().addAll(List.of(
                new ValidGamePhaseRule(Phases.BUILD),
                new ValidPlayerRule(playerName),
                new NotRule(new BuildRessourcesIsEmptyRule(playerName, buildIndex)),
                new ValidPlayerBuildIndexRule(getPlayerName(), buildIndex),
                new ValidPlayerBuilderIndexRule(getPlayerName(), builderIndex)));
    }


    @Override
    public void apply(Board board) {
        Player player = board.getPlayers().getByName(getPlayerName());
        Build build = board.getBuilds().get(buildIndex);
        Builder builder = player.getBuilders().get(builderIndex);

        build.getRessources().remove(builder.getRessources());
        if (builder.isInfected()) builder.setInfected(true);
        builder.setAssignedBuild(build);
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", "addBuilderBuild")
                .add("player", getPlayerName())
                .add("buildIndex", buildIndex)
                .add("builderIndex", builderIndex)
                .build();
    }
}

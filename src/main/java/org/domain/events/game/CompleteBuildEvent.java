package org.domain.events.game;

import org.domain.enums.Phases;
import org.domain.model.Board;
import org.domain.model.Build;
import org.domain.model.Player;
import org.domain.rules.*;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class CompleteBuildEvent extends PlayerEvent
{
    private final int buildIndex;

    public CompleteBuildEvent(String playerName, int buildIndex) {
        super(playerName);
        this.buildIndex = buildIndex;

        super.getRules().addAll(List.of(
                new ValidPlayerRule(playerName),
                new ValidGamePhaseRule(Phases.BUILD),
                new ValidPlayerBuildIndexRule(playerName, buildIndex),
                new BuildRessourcesIsEmptyRule(playerName, buildIndex)
        ));
    }

    @Override
    public void apply(Board board) {
        Player player = board.getPlayers().getByName(super.getPlayerName());
        Build build = player.getBuilds().get(buildIndex);

        player.setMoney(player.getMoney() + build.getReward());
        build.setReward(0);
        player.getBuilders().forEach(builder -> {
            if (builder.getAssignedBuild() != null && builder.getAssignedBuild().equals(build)) {
                builder.setAssignedBuild(null);
            }
        });
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("content", "event")
                .add("event", "completeBuild")
                .add("player", getPlayerName())
                .add("buildIndex", buildIndex)
                .build();
    }
}

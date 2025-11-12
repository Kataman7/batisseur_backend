package org.domain.event;

import org.domain.model.Board;
import org.domain.bullet.Bullet;
import org.domain.model.Player;
import org.domain.rule.*;

import javax.json.JsonObject;
import java.util.Deque;
import java.util.List;

public class AddBulletEvent extends PlayerEvent
{
    private final int bulletIndex;

    public AddBulletEvent(String playerName, String playerToken, int bulletIndex)
    {
        super(playerName, playerToken);
        this.bulletIndex = bulletIndex;

        getRules().addAll(List.of(
                new NotRule(new IsGameOverRule()),
                new IsPlayerTurnRule(getPlayerName()),
                new NotRule(new IsBetPhaseRule()),
                new IsPlayerHasBulletUnloadedRule(getPlayerName())
        ));
    }

    @Override
    public void apply(Board board)
    {
        Player player = board.getPlayers().getCurrent();
        List<Bullet> unloadedBullets = player.getUnloadedBullets();
        List<Bullet> loadedBullets = player.getLoadedBullets();
        loadedBullets.add(unloadedBullets.remove(bulletIndex));
    }

    @Override
    public JsonObject toJson()
    {
        return null;
    }
}

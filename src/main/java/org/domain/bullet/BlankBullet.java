package org.domain.bullet;

import org.domain.event.WinBetEvent;
import org.domain.model.Board;
import org.domain.model.Player;

import java.util.List;

public class BlankBullet extends Bullet
{
    @Override
    public void apply(Board board)
    {
        Player bettor = board.getBettor();
        Player shooter = board.getShooter();

        shooter.getUnloadedBullets().add(this);
        shooter.getLoadedBullets().remove(this);

        new WinBetEvent(bettor.getName(), bettor.getSecretToken()).apply(board);
    }
}

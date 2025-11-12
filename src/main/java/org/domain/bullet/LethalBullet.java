package org.domain.bullet;

import org.domain.event.LooseBetEvent;
import org.domain.model.Board;
import org.domain.model.Player;

public class LethalBullet extends Bullet
{

    @Override
    public void apply(Board board)
    {
        Player bettor = board.getBettor();
        Player shooter = board.getShooter();

        shooter.getUnloadedBullets().add(this);
        shooter.getLoadedBullets().remove(this);

        new LooseBetEvent(bettor.getName(), bettor.getSecretToken()).apply(board);
    }
}

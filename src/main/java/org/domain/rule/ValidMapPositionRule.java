package org.domain.rule;

import org.domain.model.Board;
import org.domain.model.GameMap;

public class ValidMapPositionRule implements GameRule {
    private final int x;
    private final int y;
    private final int sizeX;
    private final int sizeY;

    public ValidMapPositionRule(int x, int y, int sizeX, int sizeY) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public boolean isApplicable(Board board) {

        GameMap gameMap = board.getGameMap();

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                int xi = x + i;
                int yj = y + j;
                if (xi >= gameMap.getSizeX() || yj >= gameMap.getSizeY()) return false;
                GameMap.BuildCell cell = gameMap.getCell(xi, yj);
                if (cell != null && cell.build != null && !cell.build.isReplaceable()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int getCode() {
        return 0;
    }
}

package org.domain.model;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class GameMap implements Model {
    private final int sizeX;
    private final int sizeY;
    private final BuildCell[][] cells;

    public static class BuildCell {
        public Build build;
        public boolean isOrigin;
        public BuildCell(Build build, boolean isOrigin) {
            this.build = build;
            this.isOrigin = isOrigin;
        }
    }

    public GameMap(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.cells = new BuildCell[sizeX][sizeY];
    }

    public BuildCell getCell(int x, int y) {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) return null;
        return cells[x][y];
    }

    public void setCell(int x, int y, BuildCell cell) {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) return;
        cells[x][y] = cell;
    }

    public int getSizeX() {
        return sizeX;
    }
    public int getSizeY() {
        return sizeY;
    }

    @Override
    public JsonObject toJson() {
        JsonArrayBuilder rows = Json.createArrayBuilder();
        for (int x = 0; x < sizeX; x++) {
            JsonArrayBuilder row = Json.createArrayBuilder();
            for (int y = 0; y < sizeY; y++) {
                BuildCell cell = cells[x][y];
                if (cell == null) {
                    row.addNull();
                } else {
                    JsonObject cellJson = Json.createObjectBuilder()
                            .add("build", cell.build.toJson())
                            .add("isOrigin", cell.isOrigin)
                            .build();
                    row.add(cellJson);
                }
            }
            rows.add(row);
        }
        return Json.createObjectBuilder()
                .add("sizeX", sizeX)
                .add("sizeY", sizeY)
                .add("cells", rows)
                .build();
    }
}

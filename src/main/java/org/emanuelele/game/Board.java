package org.emanuelele.game;

import org.emanuelele.tiles.*;

import java.util.List;

public class Board {
    private final List<Tile> tiles;

    public Board() {
        tiles = new ArrayList<>();
        initializeTiles();
        sortTiles();
    }

    private void initializeTiles() {
        try {
            tiles.addAll(Property.get());
            tiles.addAll(Station.get());
            tiles.addAll(Society.get());
            tiles.addAll(Corner.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package org.emanuelele.game;

import org.emanuelele.decks.ProbabilityCardDeck;
import org.emanuelele.decks.UnexpectedCardDeck;
import org.emanuelele.tiles.*;
import org.emanuelele.tiles.types.Corner;
import org.emanuelele.tiles.types.Property;
import org.emanuelele.tiles.types.Society;
import org.emanuelele.tiles.types.Station;
import org.emanuelele.tiles.types.Tax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private final List<Tile> tiles;
    private final ProbabilityCardDeck probabilityCards;
    private final UnexpectedCardDeck unexpectedCards;

    public Board() {
        tiles = new ArrayList<>();
        initializeTiles();
        sortTiles();
        this.probabilityCards = new ProbabilityCardDeck();
        this.unexpectedCards = new UnexpectedCardDeck();
    }

    private void initializeTiles() {
        try {
            tiles.addAll(Property.get());
            tiles.addAll(Station.get());
            tiles.addAll(Society.get());
            tiles.addAll(Corner.get());
            tiles.addAll(Tax.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sortTiles() {
        Collections.sort(tiles, (tile1, tile2) -> {
            if (tile1 instanceof Property && tile2 instanceof Property) {
                return ((Property) tile1).getPosition() - ((Property) tile2).getPosition();
            }
            if (tile1 instanceof Station && tile2 instanceof Station) {
                return ((Station) tile1).getPosition() - ((Station) tile2).getPosition();
            }
            if (tile1 instanceof Society && tile2 instanceof Society) {
                return ((Society) tile1).getPosition() - ((Society) tile2).getPosition();
            }
            return 0;
        });
    }
}

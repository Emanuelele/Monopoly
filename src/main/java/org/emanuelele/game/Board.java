package org.emanuelele.game;

import lombok.Getter;
import org.emanuelele.decks.ProbabilityCardDeck;
import org.emanuelele.decks.UnexpectedCardDeck;
import org.emanuelele.player.Player;
import org.emanuelele.tiles.*;
import org.emanuelele.tiles.types.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter
public class Board {
    private static Board instance;
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
            tiles.addAll(ProbabilityCardTile.get());
            tiles.addAll(UnexpectedCardTile.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sortTiles() {
        Collections.sort(tiles, Comparator.comparingInt(Tile::getPosition));
    }

    public void resolvePlayerAction(Player player) {
        this.tiles.get(player.getCurrentPosition()).onLand(player);
    }

    public static Board getInstance() {
        if(instance == null) {
            synchronized(Board.class) {
                if (instance == null)
                    instance = new Board();
            }
        }
        return instance;
    }
}

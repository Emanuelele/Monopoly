package org.emanuelele.tiles;

import lombok.Getter;
import org.emanuelele.player.Player;

@Getter
public abstract class Tile {
    private final String name;

    public Tile(String name) {
        this.name = name;
    }

    public abstract void onLand(Player player);
}

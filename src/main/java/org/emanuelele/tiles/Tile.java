package org.emanuelele.tiles;

import lombok.Getter;
import org.emanuelele.player.Player;

@Getter
public abstract class Tile {
    private final String name;
    private final int position;
    private final String image;

    public Tile(String name, int position, String image) {
        this.name = name;
        this.position = position;
        this.image = image;
    }

    public abstract void onLand(Player player);
}

package org.emanuelele.tiles.types;

import org.emanuelele.config.Config;
import org.emanuelele.game.Board;
import org.emanuelele.player.Player;
import org.emanuelele.tiles.Tile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.Integer.parseInt;

public class UnexpectedCardTile extends Tile {
    private UnexpectedCardTile(String key, Properties properties){
        super(properties.getProperty(key + ".name"), parseInt(properties.getProperty(key + ".position")), properties.getProperty(key + ".image"));
    }

    private static Properties loadPropertiesFile() throws IOException {
        Config config = new Config();
        Properties properties = new Properties();
        try (InputStream input = Property.class.getClassLoader().getResourceAsStream(config.getString("UNEXPECTED_CARDS_TILE_PATH"))) {
            if (input == null) {
                throw new IOException("Sorry, unable to find " + config.getString("UNEXPECTED_CARDS_TILE_PATH"));
            }
            properties.load(input);
        }
        return properties;
    }

    public static List<UnexpectedCardTile> get() throws IOException {
        Properties properties = loadPropertiesFile();
        List<UnexpectedCardTile> propertiesList = new ArrayList<>();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith("unexpected_") && key.endsWith(".name")) {
                String propertyKey = key.substring(0, key.lastIndexOf("."));
                propertiesList.add(new UnexpectedCardTile(propertyKey, properties));
            }
        }
        return propertiesList;
    }

    @Override
    public void onLand(Player player){
        Board.getInstance().getUnexpectedCards().drawCard().getAction().execute(player);
    }

    @Override
    public String toString() {
        return "Unexpected " + getName();
    }
}

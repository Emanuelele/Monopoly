package org.emanuelele.tiles.types;

import org.emanuelele.cards.actions.CardActionType;
import org.emanuelele.config.Config;
import org.emanuelele.player.Player;
import org.emanuelele.tiles.Tile;
import org.emanuelele.game.Board;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.Integer.parseInt;

public class ProbabilityCardTile extends Tile {
    private ProbabilityCardTile(String key, Properties properties){
        super(properties.getProperty(key + ".name"), parseInt(properties.getProperty(key + ".position")), properties.getProperty(key + ".image"));
    }

    private static Properties loadPropertiesFile() throws IOException {
        Config config = new Config();
        Properties properties = new Properties();
        try (InputStream input = Property.class.getClassLoader().getResourceAsStream(config.getString("PROBABILITY_CARDS_TILE_PATH"))) {
            if (input == null) {
                throw new IOException("Sorry, unable to find " + config.getString("PROBABILITY_CARDS_TILE_PATH"));
            }
            properties.load(input);
        }
        return properties;
    }

    public static List<ProbabilityCardTile> get() throws IOException {
        Properties properties = loadPropertiesFile();
        List<ProbabilityCardTile> propertiesList = new ArrayList<>();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith("probability_") && key.endsWith(".name")) {
                String propertyKey = key.substring(0, key.lastIndexOf("."));
                propertiesList.add(new ProbabilityCardTile(propertyKey, properties));
            }
        }
        return propertiesList;
    }

    @Override
    public void onLand(Player player){
        Board.getInstance().getProbabilityCards().drawCard().getAction().execute(player);
    }

    @Override
    public String toString() {
        return "Probability " + getName();
    }
}

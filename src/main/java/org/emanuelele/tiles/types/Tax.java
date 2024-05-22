package org.emanuelele.tiles.types;

import org.emanuelele.config.Config;
import org.emanuelele.player.Player;
import org.emanuelele.tiles.Tile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.Integer.parseInt;

public class Tax extends Tile {

    private final int rent;

    private Tax(String key, Properties properties){
        super(properties.getProperty(key + ".name"), parseInt(properties.getProperty(key + ".position")), properties.getProperty(key + ".image"));
        this.rent = parseInt(properties.getProperty(key + ".rent"));
    }

    private static Properties loadPropertiesFile() throws IOException {
        Config config = new Config();
        Properties properties = new Properties();
        try (InputStream input = Property.class.getClassLoader().getResourceAsStream(config.getString("TAXES_CARDS_PATH"))) {
            if (input == null) {
                throw new IOException("Sorry, unable to find " + config.getString("TAXES_CARDS_PATH"));
            }
            properties.load(input);
        }
        return properties;
    }

    public static List<Tax> get() throws IOException {
        Properties properties = loadPropertiesFile();
        List<Tax> propertiesList = new ArrayList<>();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith("tax_") && key.endsWith(".name")) {
                String propertyKey = key.substring(0, key.lastIndexOf("."));
                propertiesList.add(new Tax(propertyKey, properties));
            }
        }
        return propertiesList;
    }

    @Override
    public void onLand(Player player){

    }

    @Override
    public String toString() {
        return "Tax " + getName();
    }
}

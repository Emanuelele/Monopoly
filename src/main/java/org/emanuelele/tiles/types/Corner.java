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

public class Corner extends Tile {

    public Corner(String key, Properties properties) {
        super(properties.getProperty(key + ".name"), parseInt(properties.getProperty(key + ".position")), properties.getProperty(key + ".image"));
    }

    public void onLand(Player player) {
        switch (getName()) {
            case "Via":
                System.out.println(player.getName() + " is at " + getName() + ". Collects €200.");
                break;
            case "Prigione/Transito":
                System.out.println(player.getName() + " is at " + getName() + ".");
                break;
            case "Parcheggio gratuito":
                System.out.println(player.getName() + " is at " + getName() + ".");
                break;
            case "Vai in prigione":
                System.out.println(player.getName() + " goes to " + getName() + ".");
                break;
            default:
                System.out.println(player.getName() + " lands on an invalid corner.");
        }
    }

    private static Properties loadPropertiesFile() throws IOException {
        Config config = new Config();
        Properties properties = new Properties();
        try (InputStream input = Property.class.getClassLoader().getResourceAsStream(config.getString("CORNERS_CARDS_PATH"))) {
            if (input == null) {
                throw new IOException("Sorry, unable to find " + config.getString("CORNERS_CARDS_PATH"));
            }
            properties.load(input);
        }
        return properties;
    }

    public static List<Society> get() throws IOException {
        Properties properties = loadPropertiesFile();
        List<Society> propertiesList = new ArrayList<>();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith("corner_") && key.endsWith(".name")) {
                String propertyKey = key.substring(0, key.lastIndexOf("."));
                propertiesList.add(new Society(propertyKey, properties));
            }
        }
        return propertiesList;
    }

    @Override
    public String toString() {
        return "Corner " + getName();
    }
}

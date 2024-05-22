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

public class Station extends Tile {
    private final int cost;
    private final int baseRent;
    private boolean isOwned;
    private Player owner;

    public Station(String key, Properties properties) {
        super(properties.getProperty(key + ".name"), parseInt(properties.getProperty(key + ".position")), properties.getProperty(key + ".image"));
        this.cost = Integer.parseInt(properties.getProperty(key + ".cost"));
        this.baseRent = Integer.parseInt(properties.getProperty(key + ".rent"));
        this.isOwned = false;
        this.owner = null;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        this.isOwned = (owner != null);
    }


    public boolean purchase(Player player) {
        if (!isOwned && player.getMoney() >= cost) {
            player.removeMoney(cost);
            setOwner(player);
            return true;
        }
        return false;
    }

    public void payRent(Player player) {
        if (isOwned && owner != player) {
            int rentToPay = baseRent * owner.getNumberOfStationsOwned();
            player.removeMoney(rentToPay);
            owner.addMoney(rentToPay);
        }
    }

    public void reset() {
        this.owner = null;
        this.isOwned = false;
    }

    @Override
    public void onLand(Player player) {
        if (isOwned) {
            this.payRent(player);
        } else {
            this.purchase(player);
        }
    }

    private static Properties loadPropertiesFile() throws IOException {
        Config config = new Config();
        Properties properties = new Properties();
        try (InputStream input = Property.class.getClassLoader().getResourceAsStream(config.getString("STATION_CARDS_PATH"))) {
            if (input == null) {
                throw new IOException("Sorry, unable to find " + config.getString("STATION_CARDS_PATH"));
            }
            properties.load(input);
        }
        return properties;
    }

    public static List<Station> get() throws IOException {
        Properties properties = loadPropertiesFile();
        List<Station> propertiesList = new ArrayList<>();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith("station_") && key.endsWith(".name")) {
                String propertyKey = key.substring(0, key.lastIndexOf("."));
                propertiesList.add(new Station(propertyKey, properties));
            }
        }
        return propertiesList;
    }

    @Override
    public String toString() {
        return "Station " + getName();
    }
}

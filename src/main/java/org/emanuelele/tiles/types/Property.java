package org.emanuelele.tiles.types;

import lombok.Getter;
import org.emanuelele.config.Config;
import org.emanuelele.player.Player;
import org.emanuelele.tiles.Tile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.Integer.parseInt;

@Getter
public class Property extends Tile {
    private final int cost;
    private final int rent;
    private final int rentWith1House;
    private final int rentWith2Houses;
    private final int rentWith3Houses;
    private final int rentWith4Houses;
    private final int rentWithHotel;
    private final int houseCost;
    private final int hotelCost;
    private final String color;
    private boolean isOwned;
    private Player owner;
    private int houses;
    private boolean hasHotel;

    private Property(String key, Properties properties) {
        super(properties.getProperty(key + ".name"), parseInt(properties.getProperty(key + ".position")), properties.getProperty(key + ".image"));
        this.cost = parseInt(properties.getProperty(key + ".cost"));
        this.rent = parseInt(properties.getProperty(key + ".rent"));
        this.rentWith1House = parseInt(properties.getProperty(key + ".rent_with_1_house"));
        this.rentWith2Houses = parseInt(properties.getProperty(key + ".rent_with_2_houses"));
        this.rentWith3Houses = parseInt(properties.getProperty(key + ".rent_with_3_houses"));
        this.rentWith4Houses = parseInt(properties.getProperty(key + ".rent_with_4_houses"));
        this.rentWithHotel = parseInt(properties.getProperty(key + ".rent_with_hotel"));
        this.houseCost = parseInt(properties.getProperty(key + ".house_cost"));
        this.hotelCost = parseInt(properties.getProperty(key + ".hotel_cost"));
        this.color = properties.getProperty(key + ".color");
        this.isOwned = false;
        this.owner = null;
        this.houses = 0;
        this.hasHotel = false;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        this.isOwned = (owner != null);
    }

    public boolean purchase(Player player) {
        if (!isOwned && player.getMoney() >= cost) {
            player.removeMoney(cost);
            this.setOwner(player);
            return true;
        }
        return false;
    }

    public void payRent(Player player) {
        if (isOwned && owner != player) {
            int[] rentLevels = {rent, rentWith1House, rentWith2Houses, rentWith3Houses, rentWith4Houses};
            int rentToPay = hasHotel ? rentWithHotel : rentLevels[houses];
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
        if (isOwned)
            this.payRent(player);
        else
            this.purchase(player);

    }

    private static Properties loadPropertiesFile() throws IOException {
        Config config = new Config();
        Properties properties = new Properties();
        try (InputStream input = Property.class.getClassLoader().getResourceAsStream(config.getString("PROPERTY_CARDS_PATH"))) {
            if (input == null) {
                throw new IOException("Sorry, unable to find " + config.getString("PROPERTY_CARDS_PATH"));
            }
            properties.load(input);
        }
        return properties;
    }

    public static List<Property> get() throws IOException {
        Properties properties = loadPropertiesFile();
        List<Property> propertiesList = new ArrayList<>();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith("property_") && key.endsWith(".name")) {
                String propertyKey = key.substring(0, key.lastIndexOf("."));
                propertiesList.add(new Property(propertyKey, properties));
            }
        }
        return propertiesList;
    }

    @Override
    public String toString() {
        return "Property " + getName();
    }
}

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
public class Society extends Tile {
    private final int cost;
    private final int lowRent = 4;
    private final int highRent = 10;
    private boolean isOwned;
    private Player owner;

    public Society(String key, Properties properties) {
        super(properties.getProperty(key + ".name"), parseInt(properties.getProperty(key + ".position")), properties.getProperty(key + ".image"));
        this.cost = parseInt(properties.getProperty(key + ".cost"));
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

    private int getRent() {
        return owner.getNumberOfSocietiesOwned() == 1 ? lowRent : highRent;
    }

    public void payRent(Player player, int diceTotal) {
        if (isOwned && owner != player) {
            int rentToPay = this.getRent() * diceTotal;
            player.removeMoney(rentToPay);
            owner.addMoney(rentToPay);
        }
    }

    public void onLand(Player player) {
        if (isOwned) {
            payRent(player, player.getLastDiceTotal());
        } else {
            this.purchase(player);
        }
    }

    private static Properties loadPropertiesFile() throws IOException {
        Config config = new Config();
        Properties properties = new Properties();
        try (InputStream input = Society.class.getClassLoader().getResourceAsStream(config.getString("SOCIETIES_CARDS_PATH"))) {
            if (input == null) {
                throw new IOException("Sorry, unable to find " + config.getString("SOCIETIES_CARDS_PATH"));
            }
            properties.load(input);
        }
        return properties;
    }

    public static List<Society> get() throws IOException {
        Properties properties = loadPropertiesFile();
        List<Society> societyList = new ArrayList<>();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith("society_") && key.endsWith(".name")) {
                String societyKey = key.substring(0, key.lastIndexOf("."));
                Society society = new Society(societyKey, properties);
                System.out.println(society.getCost());
                societyList.add(society);
            }
        }
        return societyList;
    }

    @Override
    public String toString() {
        return "Property " + getName();
    }
}

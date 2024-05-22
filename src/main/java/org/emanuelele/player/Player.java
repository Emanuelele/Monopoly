package org.emanuelele.player;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.emanuelele.config.Config;
import org.emanuelele.tiles.types.Station;
import org.emanuelele.tiles.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter @Setter
public class Player {
    private String name;
    private int money;
    private int currentPosition;
    private boolean inJail;
    @Accessors(fluent = true)
    private boolean hasExitFromPrisonCard;
    private List<Tile> propertiesOwned;
    private int lastDiceTotal;

    public Player(String name) {
        Config config = new Config();
        this.name = name;
        this.money = config.getInt("INITIAL_MONEY");
        this.currentPosition = 0;
        this.inJail = false;
        this.hasExitFromPrisonCard = false;
        this.propertiesOwned = new ArrayList<Tile>();
        this.lastDiceTotal = 0;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }

    public void removeMoney(int amount) {
        this.money -= amount;
    }

    public int rollDice() {
        Random random = new Random();
        return this.lastDiceTotal = random.nextInt(1,6);
    }

    public void setCurrentPosition(int position, boolean canPassThroughGo) {
        if (canPassThroughGo && position < this.currentPosition)
            money += 200;
        this.currentPosition = position;
    }

    public void advanceOf(int spaces, boolean canPassThroughGo) {
        int oldPosition = this.currentPosition;
        this.currentPosition = (this.currentPosition + spaces) % 40;
        if (canPassThroughGo && oldPosition > this.currentPosition)
            money += 200;
    }

    public int getNumberOfStationsOwned() {
        int count = 0;
        for (Tile tile : propertiesOwned) {
            if (tile instanceof Station) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "Player [name=" + name + ", money=" + money + ", currentPosition=" + currentPosition
                + ", inJail=" + inJail + ", hasExitFromPrisonCard=" + hasExitFromPrisonCard + "]";
    }
}

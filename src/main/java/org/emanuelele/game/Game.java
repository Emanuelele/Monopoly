package org.emanuelele.game;

import org.emanuelele.player.Player;

import java.util.List;

public class Game {
    List<Player> players;
    private Board board;
    private int currentPlayer;

    public Game(List<Player> players) {
        this.players = players;
        board = new Board();
        currentPlayer = 0;
    }

    public void PlayRound(){
        Player player = players.get(currentPlayer);
        int roll = player.rollDice() + player.rollDice();
        player.setCurrentPosition(player.getCurrentPosition() + roll);
        //board.resolvePlayerAction(plauer);
        currentPlayer = (currentPlayer + 1) % players.size();
    }
}

package org.emanuelele;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.emanuelele.cards.ProbabilityCard;
import org.emanuelele.cards.actions.CardActionType;
import org.emanuelele.decks.ProbabilityCardDeck;
import org.emanuelele.decks.UnexpectedCardDeck;
import org.emanuelele.game.Board;
import org.emanuelele.game.Game;
import org.emanuelele.player.Player;
import org.emanuelele.tiles.Tile;

import java.util.Arrays;
import java.util.List;

public class Main extends Application {
    public void start(Stage primaryStage) {
        Button nuovaPartitaButton = new Button("Nuova Partita");
        Button caricaPartitaButton = new Button("Carica Partita");
        nuovaPartitaButton.setOnAction(e -> {
            ConfigScene configScene = new ConfigScene(primaryStage);
            primaryStage.setScene(configScene.getScene());
        });
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(nuovaPartitaButton, caricaPartitaButton);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestione Partite");
        primaryStage.show();
    }
    public static void main(String[] args) {
        // Creiamo alcuni giocatori
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");
        Player player3 = new Player("Charlie");

        // Mettiamo i giocatori in una lista
        List<Player> players = Arrays.asList(player1, player2, player3);

        // Inizializziamo il gioco con i giocatori
        Game game = new Game(players);

        // Eseguiamo alcuni round
        for (int i = 0; i < 150; i++) {
            System.out.println("Round " + (i + 1));
            game.PlayRound();
            // Stampa lo stato dei giocatori
            for (Player player : players) {
                System.out.println(player);
            }
            System.out.println();
        }
    }
}
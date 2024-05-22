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
import org.emanuelele.player.Player;

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
        //launch(args);
        ProbabilityCardDeck adeck = new ProbabilityCardDeck();
        //UnexpectedCardDeck udeck = new UnexpectedCardDeck();
        Player player = new Player("asd");
        for (int i = 0; i <18; i ++) {
            ProbabilityCard card = adeck.drawCard();
            System.out.println(card.getDescription());
            card.getAction().execute(player);
            //System.out.println(cardAction.get);
            System.out.println(player.toString());
        }
        /*player.setCurrentPosition(1);
        int roll;
        System.out.println(roll = -3);
        player.setCurrentPosition(player.getCurrentPosition() + roll);
        System.out.println(player.getCurrentPosition());*/



    }
}
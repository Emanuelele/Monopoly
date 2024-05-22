package org.emanuelele;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConfigScene {
    private Stage stage;
    private Scene scene;

    public ConfigScene(Stage stage) {
        this.stage = stage;
        initialize();
    }

    private void initialize() {
        Label nomePartitaLabel = new Label("Nome Partita:");
        TextField nomePartitaField = new TextField();

        Label numeroGiocatoriLabel = new Label("Numero di Giocatori (2-6):");
        ComboBox<Integer> numeroGiocatoriComboBox = new ComboBox<>();
        numeroGiocatoriComboBox.setItems(FXCollections.observableArrayList(2, 3, 4, 5, 6));

        CheckBox aggiungiBotCheckBox = new CheckBox("Aggiungi Bot");

        VBox giocatoriBox = new VBox(10);
        numeroGiocatoriComboBox.setOnAction(e -> {
            giocatoriBox.getChildren().clear();
            for (int i = 1; i <= numeroGiocatoriComboBox.getValue(); i++) {
                HBox playerBox = new HBox(10);
                playerBox.setAlignment(Pos.CENTER_LEFT);
                Label playerLabel = new Label("Giocatore " + i + " Nome:");
                TextField playerNameField = new TextField();
                playerBox.getChildren().addAll(playerLabel, playerNameField);
                giocatoriBox.getChildren().add(playerBox);
            }
        });

        Button startGameButton = new Button("Inizia Partita");
        startGameButton.setOnAction(e -> {
            // Codice per iniziare la partita con i dati inseriti
            String nomePartita = nomePartitaField.getText();
            int numeroGiocatori = numeroGiocatoriComboBox.getValue();
            boolean aggiungiBot = aggiungiBotCheckBox.isSelected();

            System.out.println("Nome Partita: " + nomePartita);
            System.out.println("Numero di Giocatori: " + numeroGiocatori);
            System.out.println("Aggiungi Bot: " + aggiungiBot);

            for (int i = 0; i < giocatoriBox.getChildren().size(); i++) {
                HBox playerBox = (HBox) giocatoriBox.getChildren().get(i);
                TextField playerNameField = (TextField) playerBox.getChildren().get(1);
                System.out.println("Nome Giocatore " + (i + 1) + ": " + playerNameField.getText());
            }
        });

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(nomePartitaLabel, nomePartitaField, numeroGiocatoriLabel, numeroGiocatoriComboBox, aggiungiBotCheckBox, giocatoriBox, startGameButton);

        scene = new Scene(root, 400, 400);
    }

    public Scene getScene() {
        return scene;
    }
}

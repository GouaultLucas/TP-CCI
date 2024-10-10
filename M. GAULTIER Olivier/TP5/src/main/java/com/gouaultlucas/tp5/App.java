package com.gouaultlucas.tp5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Charger le fichier FXML principal (MenuPrincipal.fxml)
            Parent root = FXMLLoader.load(getClass().getResource("/com/gouaultlucas/tp5/MenuPrincipal.fxml"));

            // Créer une scène avec la fenêtre principale
            Scene scene = new Scene(root);

            // Définir le titre de la fenêtre
            primaryStage.setTitle("Gestion de Comptes Bancaires");

            // Ajouter la scène à la fenêtre
            primaryStage.setScene(scene);

            // Afficher la fenêtre
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Lancer l'application JavaFX
        launch(args);
    }
}

package com.gouaultlucas.tp5.controleurs;

import com.gouaultlucas.tp5.modele.dao.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class MenuPrincipal {

    @FXML
    private MenuItem creerCompte;

    @FXML
    private MenuItem afficherCompte;

    @FXML
    private MenuItem listerComptes;

    @FXML
    private MenuItem creerLigneComptable;

    @FXML
    private VBox sousFenetre;

    @FXML
    private void handleMenuItem(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        System.out.println(item.getText());

        if(Objects.equals(item.getId(), creerCompte.getId())) afficherFenetre("/com/gouaultlucas/tp5/CreerCompte.fxml");
    }

    private void afficherFenetre(String cheminFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(cheminFXML));
            Parent contenu = loader.load();

            Object controller = loader.getController();

            if(controller instanceof CreerCompte) ((CreerCompte) controller).initialiserChamps();

            sousFenetre.getChildren().clear();
            sousFenetre.getChildren().add(contenu);
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier FXML : " + cheminFXML);
            e.printStackTrace();
        }
    }
}

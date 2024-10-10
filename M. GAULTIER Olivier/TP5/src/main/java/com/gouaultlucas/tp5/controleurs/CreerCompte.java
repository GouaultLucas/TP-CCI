package com.gouaultlucas.tp5.controleurs;

import com.gouaultlucas.tp5.modele.TypeCompte;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CreerCompte {
    public TextField ChampNumeroCompte;
    public ChoiceBox<String> ChampTypeCompte;
    public TextField ChampSolde;
    public TextField ChampTauxPlacement;
    public Button boutonCreerCompte;

    public void handleCreerCompte(ActionEvent actionEvent) {

    }

    public void initialiserChamps() {
        for(TypeCompte typeCompte : TypeCompte.values()) {
            ChampTypeCompte.getItems().add(typeCompte.toString());
        }
    }
}

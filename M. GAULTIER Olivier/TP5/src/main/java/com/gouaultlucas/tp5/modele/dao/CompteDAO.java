package com.gouaultlucas.tp5.modele.dao;

import com.gouaultlucas.tp5.modele.Compte;
import com.gouaultlucas.tp5.modele.CompteEpargne;
import com.gouaultlucas.tp5.modele.TypeCompte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDAO {

    // Méthode pour créer un compte dans la base de données
    public boolean creerCompte(Compte compte) {
        String query = "INSERT INTO Compte (numero, type_compte_id, solde, taux_placement) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, compte.getNumero());
            statement.setInt(2, compte.getType().ordinal() + 1); // Conversion du type en entier (ordinal)
            statement.setFloat(3, compte.getSolde());
            if (compte instanceof CompteEpargne) {
                statement.setFloat(4, ((CompteEpargne) compte).getTauxPlacement());
            } else {
                statement.setNull(4, Types.FLOAT); // NULL pour les comptes courants
            }
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour lire un compte par son numéro
    public Compte lireCompte(int numero) {
        String query = "SELECT * FROM Compte WHERE numero = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, numero);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int typeId = resultSet.getInt("type_compte_id");
                TypeCompte typeCompte = (typeId == 1) ? TypeCompte.COURANT : TypeCompte.EPARGNE;
                float solde = resultSet.getFloat("solde");
                Float tauxPlacement = resultSet.getObject("taux_placement") != null ? resultSet.getFloat("taux_placement") : null;

                if (typeCompte == TypeCompte.EPARGNE) {
                    return new CompteEpargne(numero, solde, tauxPlacement);
                } else {
                    return new Compte(typeCompte, numero, solde);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Aucun compte trouvé
    }

    // Méthode pour mettre à jour un compte
    public boolean mettreAJourCompte(Compte compte) {
        String query = "UPDATE Compte SET solde = ?, taux_placement = ? WHERE numero = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setFloat(1, compte.getSolde());
            if (compte instanceof CompteEpargne) {
                statement.setFloat(2, ((CompteEpargne) compte).getTauxPlacement());
            } else {
                statement.setNull(2, Types.FLOAT); // Aucun taux de placement pour les comptes courants
            }
            statement.setInt(3, compte.getNumero());
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour supprimer un compte
    public boolean supprimerCompte(int numero) {
        String query = "DELETE FROM Compte WHERE numero = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, numero);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour lire tous les comptes
    public List<Compte> lireTousLesComptes() {
        List<Compte> comptes = new ArrayList<>();
        String query = "SELECT * FROM Compte";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int numero = resultSet.getInt("numero");
                int typeId = resultSet.getInt("type_compte_id");
                TypeCompte typeCompte = (typeId == 1) ? TypeCompte.COURANT : TypeCompte.EPARGNE;
                float solde = resultSet.getFloat("solde");
                Float tauxPlacement = resultSet.getObject("taux_placement") != null ? resultSet.getFloat("taux_placement") : null;

                if (typeCompte == TypeCompte.EPARGNE) {
                    comptes.add(new CompteEpargne(numero, solde, tauxPlacement));
                } else {
                    comptes.add(new Compte(typeCompte, numero, solde));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comptes;
    }
}

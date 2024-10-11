package com.gouaultlucas.tp5.modele.dao;

import com.gouaultlucas.tp5.modele.Compte;
import com.gouaultlucas.tp5.modele.CompteEpargne;
import com.gouaultlucas.tp5.modele.TypeCompte;

import java.sql.*;
import java.util.ArrayList;

public class CompteDAO {

    // Méthode pour créer un compte dans la base de données
    public boolean creerCompte(Compte compte) {
        String query = "INSERT INTO Compte (numero, type_compte, solde, taux_placement) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, compte.getNumero());
            statement.setInt(2, compte.getType().ordinal());
            statement.setFloat(3, compte.getSolde());
            if (compte instanceof CompteEpargne) {
                statement.setFloat(4, ((CompteEpargne) compte).getTauxPlacement());
            } else {
                statement.setNull(4, Types.FLOAT);
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
        Compte compte = null;

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, numero);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                compte = toModele(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compte;
    }

    public boolean mettreAJourCompte(Compte compte) {
        String query = "UPDATE Compte SET solde = ?, taux_placement = ? WHERE numero = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setFloat(1, compte.getSolde());
            if (compte instanceof CompteEpargne) {
                statement.setFloat(2, ((CompteEpargne) compte).getTauxPlacement());
            } else {
                statement.setNull(2, Types.FLOAT);
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
    public ArrayList<Compte> lireTousLesComptes() {
        ArrayList<Compte> comptes = new ArrayList<>();
        String query = "SELECT * FROM Compte";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                comptes.add(toModele(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comptes;
    }

    private Compte toModele(ResultSet resultSet) throws SQLException {
        Compte compte = null;

        int numero = resultSet.getInt("numero");
        int type = resultSet.getInt("type_compte");
        TypeCompte typeCompte = TypeCompte.values()[type];
        float solde = resultSet.getFloat("solde");
        Float tauxPlacement = resultSet.getObject("taux_placement") != null ? resultSet.getFloat("taux_placement") : null;

        if (typeCompte == TypeCompte.EPARGNE) {
            return new CompteEpargne(numero, solde, tauxPlacement);
        } else {
            return new Compte(typeCompte, numero, solde);
        }
    }
}

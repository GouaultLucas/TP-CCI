package com.gouaultlucas.tp5.modele.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db_tp5_gouaultlucas";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion à la base de données réussie.");
            } catch (ClassNotFoundException e) {
                System.out.println("Le driver JDBC n'a pas été trouvé.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Erreur de connexion à la base de données.");
                e.printStackTrace();
                throw e;
            }
        }
        return connection;
    }

    // Méthode pour fermer la connexion
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion à la base de données fermée.");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion.");
                e.printStackTrace();
            }
        }
    }

    public static boolean testerConnexion() {
        try (Connection connection = getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("La connexion à la base de données est réussie !");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Échec de la connexion à la base de données : " + e.getMessage());
        }
        return false;
    }
}
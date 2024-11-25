package ma.fstt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_commandes";
    private static final String USER = "root";
    private static final String PASSWORD = "YES";

    public static Connection getConnection() {
        try {
            // Charger le pilote JDBC si nécessaire (pas obligatoire avec des versions modernes)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Retourner une connexion
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la connexion à la base de données", e);
        }
    }
}

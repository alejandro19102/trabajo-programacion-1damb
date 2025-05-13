package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Inicializador de la base de datos ranking.db para la aplicación.
 */
public class DBInitializer {
    // La URL crea el archivo ranking.db en el directorio de trabajo si no existe
    private static final String DB_URL = "jdbc:sqlite:ranking.db";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // Crear tabla de usuarios
            String createUsers = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    username TEXT PRIMARY KEY,
                    email    TEXT NOT NULL
                );
                """;
            stmt.execute(createUsers);

            // Crear tabla de ranking
            String createRanking = """
                CREATE TABLE IF NOT EXISTS ranking (
                    id     INTEGER PRIMARY KEY AUTOINCREMENT,
                    player TEXT    NOT NULL,
                    score  INTEGER NOT NULL
                );
                """;
            stmt.execute(createRanking);

            System.out.println("Base de datos inicializada correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos:");
            e.printStackTrace();
        }
    }
}

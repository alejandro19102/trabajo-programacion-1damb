package modelo;

import java.sql.*;

public class UserDAO {
    private static final String DB_URL = "jdbc:sqlite:ranking.db";
    static {
        try { Class.forName("org.sqlite.JDBC"); }
        catch (ClassNotFoundException e) { throw new RuntimeException(e); }
    }

    public UserDAO() {
        try (Connection c = DriverManager.getConnection(DB_URL);
             Statement s = c.createStatement()) {
            s.execute("""
                CREATE TABLE IF NOT EXISTS usuarios (
                    username TEXT PRIMARY KEY,
                    email    TEXT NOT NULL
                );
            """);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public boolean ensureUser(String u, String e) {
        try (Connection c = DriverManager.getConnection(DB_URL);
             PreparedStatement p = c.prepareStatement(
               "INSERT OR IGNORE INTO usuarios(username,email) VALUES(?,?)")) {
            p.setString(1, u); p.setString(2, e);
            p.executeUpdate();
            return true;
        } catch (SQLException ex) { ex.printStackTrace(); return false; }
    }
}

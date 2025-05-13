package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RankingDAO {
    private static final String DB_URL = "jdbc:sqlite:ranking.db";

    public RankingDAO() {
        try (Connection c = DriverManager.getConnection(DB_URL);
             Statement s = c.createStatement()) {
            s.execute("""
                CREATE TABLE IF NOT EXISTS ranking (
                    id     INTEGER PRIMARY KEY AUTOINCREMENT,
                    player TEXT    NOT NULL,
                    score  INTEGER NOT NULL
                );
            """);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void addScore(String player, int score) {
        try (Connection c = DriverManager.getConnection(DB_URL);
             PreparedStatement p = c.prepareStatement(
               "INSERT INTO ranking(player,score) VALUES(?,?)");
             Statement s = c.createStatement()) {
            p.setString(1, player); p.setInt(2, score);
            p.executeUpdate();
            s.execute("""
                DELETE FROM ranking
                WHERE id NOT IN (
                  SELECT id FROM ranking
                  ORDER BY score DESC
                  LIMIT 10
                );
            """);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<ScoreEntry> getTop10() {
        List<ScoreEntry> out = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(DB_URL);
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(
               "SELECT player,score FROM ranking ORDER BY score DESC LIMIT 10")) {
            while (rs.next()) {
                out.add(new ScoreEntry(rs.getString(1), rs.getInt(2)));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return out;
    }
}

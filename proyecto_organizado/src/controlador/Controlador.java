package controlador;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.RankingDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controlador {

    @FXML
    private TextArea mapaTextoArea;

    private char[][] maze;
    private int rows, cols;
    private int playerRow, playerCol;
    private int moves;

    @FXML
    public void initialize() {
        // Selecciona aleatoriamente uno de los 5 escenarios
        int escenario = new Random().nextInt(5) + 1;
        String path = String.format("/escenario/escenario%d.txt", escenario);
        System.out.println("Escenario elegido: " + path);

        loadMaze(path);
        // Posición inicial
        playerRow = 1;
        playerCol = 1;
        moves = 0;
        maze[playerRow][playerCol] = 'P';
        refreshMaze();

        // Listener de teclas y foco
        mapaTextoArea.setOnKeyPressed(this::handleKeyPress);
        Platform.runLater(() -> mapaTextoArea.requestFocus());
    }

    private void loadMaze(String resourcePath) {
        URL url = getClass().getResource(resourcePath);
        if (url == null) {
            throw new IllegalStateException("No se encontró recurso: " + resourcePath);
        }
        try (InputStream is = url.openStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            rows = lines.size();
            cols = lines.get(0).length();
            maze = new char[rows][cols];
            for (int i = 0; i < rows; i++) {
                maze[i] = lines.get(i).toCharArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error leyendo el laberinto", e);
        }
    }

    private void refreshMaze() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : maze) {
            sb.append(row).append('\n');
        }
        mapaTextoArea.setText(sb.toString());
    }

    @FXML
    private void handleKeyPress(KeyEvent event) {
        int nr = playerRow, nc = playerCol;
        KeyCode code = event.getCode();

        // Mover con flechas o WASD
        if (code == KeyCode.UP || code == KeyCode.W)      nr--;
        else if (code == KeyCode.DOWN || code == KeyCode.S) nr++;
        else if (code == KeyCode.LEFT || code == KeyCode.A) nc--;
        else if (code == KeyCode.RIGHT || code == KeyCode.D)nc++;
        else return;

        // Comprobar límites y muros
        if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) return;
        if (maze[nr][nc] == '#') return;

        // Actualizar posición
        maze[playerRow][playerCol] = ' ';
        playerRow = nr;
        playerCol = nc;
        maze[playerRow][playerCol] = 'P';
        moves++;
        refreshMaze();

        // Chequear si ha llegado a la meta (fila rows-2, col cols-2)
        if (playerRow == rows - 2 && playerCol == cols - 2) {
            int score = Math.max(0, 1000 - moves);
            String player = readPlayer();
            new RankingDAO().addScore(player, score);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¡Has ganado!");
            alert.setHeaderText(null);
            alert.setContentText("Movimientos: " + moves + "\nPuntuación: " + score);
            alert.showAndWait();

            regresarMenu();
        }
    }

    private String readPlayer() {
        try (BufferedReader reader = new BufferedReader(new FileReader("usuario.txt"))) {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "unknown";
        }
    }

    private void regresarMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
            Stage stage = (Stage) mapaTextoArea.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

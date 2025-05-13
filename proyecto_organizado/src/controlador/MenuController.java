package controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class MenuController {

    @FXML private Label mensajeLabel;

    @FXML
    private void iniciarJuego(ActionEvent event) {
        loadScene(event, "/view/Juego.fxml", "Juego");
    }

    @FXML
    private void mostrarRanking(ActionEvent event) {
        loadScene(event, "/view/Ranking.fxml", "Ranking");
    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            java.net.URL loc = getClass().getResource(fxmlPath);
            System.out.println("Cargando FXML desde: " + loc);
            if (loc == null) throw new IllegalStateException("No se encontró: " + fxmlPath);
            Parent root = FXMLLoader.load(loc);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
            if (mensajeLabel != null) {
                mensajeLabel.setText("Error cargando: " + e.getMessage());
            }
        }
    }
}

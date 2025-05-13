package controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import modelo.UserDAO;

import java.io.FileWriter;
import java.io.IOException;

public class LoginController {

    @FXML private TextField usuarioField;
    @FXML private TextField emailField;
    @FXML private Label    mensajeLabel;

    private final UserDAO userDao = new UserDAO();

    @FXML
    private void handleLogin(ActionEvent event) {
        String usuario = usuarioField.getText().trim();
        String email   = emailField.getText().trim();

        if (usuario.isEmpty() || email.isEmpty()) {
            mensajeLabel.setText("Debes rellenar ambos campos");
            return;
        }

        if (!userDao.ensureUser(usuario, email)) {
            mensajeLabel.setText("Error al acceder a la BBDD");
            return;
        }

        try (FileWriter writer = new FileWriter("usuario.txt")) {
            writer.write(usuario);
        } catch (IOException e) {
            e.printStackTrace();
            mensajeLabel.setText("Error guardando usuario");
            return;
        }

        loadScene(event, "/view/Menu.fxml", "Menú Principal");
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
            mensajeLabel.setText("Error cargando: " + e.getMessage());
        }
    }
}

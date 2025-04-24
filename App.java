import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Usar una ruta al archivo FXML
            File archivo = new File("out/view/Juego.fxml"); // Asegúrate de que el archivo se copie a esta ruta
            if (!archivo.exists()) {
                System.out.println("No se encontró el archivo FXML: " + archivo.getAbsolutePath());
                return;
            }

            // Cargar el archivo FXML
            FXMLLoader cargador = new FXMLLoader(fxmlFile.toURI().toURL());  // Usamos la URL desde el archivo
            Scene escena = new Scene(loader.load());
            escena.setTitle("Juego de Plataforma");
            escena.setScene(escena);
            escena.show();

        } catch (IOException e) {
            System.err.println("Error al cargar el archivo FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

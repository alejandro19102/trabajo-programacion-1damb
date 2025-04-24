import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Usar una ruta absoluta al archivo FXML
            File fxmlFile = new File("out/view/Juego.fxml"); // Asegúrate de que el archivo se copie a esta ruta
            if (!fxmlFile.exists()) {
                System.out.println("No se encontró el archivo FXML: " + fxmlFile.getAbsolutePath());
                return;
            }

            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(fxmlFile.toURI().toURL());  // Usamos la URL desde el archivo
            Scene scene = new Scene(loader.load());
            primaryStage.setTitle("Juego de Plataforma");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Error al cargar el archivo FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

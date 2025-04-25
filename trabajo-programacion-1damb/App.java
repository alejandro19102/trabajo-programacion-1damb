import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            File archivo = new File("src/view/Juego.fxml"); // Ruta relativa correcta
            if (!archivo.exists()) {
                System.out.println("No se encontró el archivo FXML: " + archivo.getAbsolutePath());
                return;
            }

            FXMLLoader loader = new FXMLLoader(archivo.toURI().toURL());
            Parent root = loader.load();
            Scene escena = new Scene(root);
            primaryStage.setTitle("Juego de Plataforma");
            primaryStage.setScene(escena);
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

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import modelo.Modelo;

public class Controlador {

    @FXML private TextArea mapaTextoArea;
    private Modelo modelo;

    @FXML
    public void initialize() {
        try {
            modelo = new Modelo("escenario/escenario1.txt");
            actualizarVista();
        } catch (Exception e) {
            mapaTextoArea.setText("Error al cargar el escenario.");
            System.err.println("Error al inicializar modelo: " + e.getMessage());
        }
    }

    private void actualizarVista() {
        StringBuilder sb = new StringBuilder();
        for (String linea : modelo.getMapaConJugador()) {
            sb.append(linea).append("\n");
        }
        mapaTextoArea.setText(sb.toString());
    }

    // Nuevo método para manejar teclas físicas
    public void manejarTecla(KeyCode code) {
        switch (code) {
            case W -> modelo.moverJugador("W");
            case A -> modelo.moverJugador("A");
            case S -> modelo.moverJugador("S");
            case D -> modelo.moverJugador("D");
            default -> {}
        }
        actualizarVista();
    }
}

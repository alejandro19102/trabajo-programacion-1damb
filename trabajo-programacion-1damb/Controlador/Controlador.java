import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import modelo.Modelo; // Asegúrate de que el paquete sea correcto

// Clase que controla los movimientos del jugador así como la carga de los archivos
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
        }
    }

    private void actualizarVista() {
        StringBuilder sb = new StringBuilder();
        for (String linea : modelo.getMapaConJugador()) {
            sb.append(linea).append("\n");
        }
        mapaTextoArea.setText(sb.toString());
    }

    @FXML private void moverArriba()    { modelo.moverJugador("W"); actualizarVista(); }
    @FXML private void moverAbajo()     { modelo.moverJugador("S"); actualizarVista(); }
    @FXML private void moverIzquierda() { modelo.moverJugador("A"); actualizarVista(); }
    @FXML private void moverDerecha()   { modelo.moverJugador("D"); actualizarVista(); }
}

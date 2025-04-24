
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.Escenario;
// clase que controla los movimientos del jugador asi como el cargar los archivos
public class Controlador {
    @FXML private TextArea mapaTextoArea;

    private Escenario escenario;

    @FXML
    public void incializar() {
        try {
            escenario = new Escenario("escenario/escenario1.txt");
            actualizarVista();
        } catch (Exception e) {
            mapaTextArea.setText("Error al cargar el escenario.");
        }
    }

    private void actualizarVista() {
        StringBuilder sb = new StringBuilder();
        for (String linea : escenario.getMapaConJugador()) {
            sb.append(linea).append("\n");
        }
        mapaTextArea.setText(sb.toString());
    }

    @FXML private void moverArriba() { escenario.moverJugador("W"); actualizarVista(); }
    @FXML private void moverAbajo() { escenario.moverJugador("S"); actualizarVista(); }
    @FXML private void moverIzquierda() { escenario.moverJugador("A"); actualizarVista(); }
    @FXML private void moverDerecha() { escenario.moverJugador("D"); actualizarVista(); }
}


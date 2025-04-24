
import java.io.*;
import java.util.ArrayList;
import java.util.List;
// Esta clase se encarga de cargar los archivos necesarios y la interaccion en pantalla con el jugador
public class Modelo {
    private List<String> mapa = new ArrayList<>();
    private int jugadorFila = 1;
    private int jugadorColumna = 1;

    public Escenario(String rutaArchivo) throws IOException {
        cargarDesdeArchivo(rutaArchivo);
    }

    private void cargarDesdeArchivo(String nombreArchivo) throws IOException {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) throw new FileNotFoundException();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                mapa.add(linea);
            }
        }
    }

    public List<String> getMapaConJugador() {
        List<String> copia = new ArrayList<>(mapa);
        copia.set(jugadorFila, reemplazarCaracter(copia.get(jugadorFila), jugadorColumna, '☠'));
        return copia;
    }

    public void moverJugador(String direccion) {
        switch (direccion.toUpperCase()) {
            case "W":
                if (jugadorFila > 0 && mapa.get(jugadorFila - 1).charAt(jugadorColumna) != '#') jugadorFila--;
                break;
            case "S":
                if (jugadorFila < mapa.size() - 1 && mapa.get(jugadorFila + 1).charAt(jugadorColumna) != '#') jugadorFila++;
                break;
            case "A":
                if (jugadorColumna > 0 && mapa.get(jugadorFila).charAt(jugadorColumna - 1) != '#') jugadorColumna--;
                break;
            case "D":
                if (jugadorColumna < mapa.get(jugadorFila).length() - 1 && mapa.get(jugadorFila).charAt(jugadorColumna + 1) != '#') jugadorColumna++;
                break;
        }
    }

    private String reemplazarCaracter(String linea, int indice, char nuevoCaracter) {
        StringBuilder nuevaLinea = new StringBuilder(linea);
        nuevaLinea.setCharAt(indice, nuevoCaracter);
        return nuevaLinea.toString();
    }
}


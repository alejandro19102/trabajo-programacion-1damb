package modelo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Esta clase se encarga de cargar los archivos necesarios y la interacción en pantalla con el jugador
public class Modelo {
    private final List<String> mapa = new ArrayList<>();
    private int jugadorFila = 1;
    private int jugadorColumna = 1;

    public Modelo(String rutaArchivo) throws IOException {
        cargarDesdeArchivo(rutaArchivo);
    }

    private void cargarDesdeArchivo(String nombreArchivo) throws IOException {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            throw new FileNotFoundException("No se encontró el archivo: " + archivo.getAbsolutePath());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                mapa.add(linea);
            }
        }
    }

    public List<String> getMapaConJugador() {
        List<String> copia = new ArrayList<>(mapa);
        if (jugadorFila >= 0 && jugadorFila < copia.size()) {
            copia.set(jugadorFila, reemplazarJugador(copia.get(jugadorFila), jugadorColumna, '☠'));
        }
        return copia;
    }

    public void moverJugador(String direccion) {
        direccion = direccion.toUpperCase();
        switch (direccion) {
            case "W":
                if (puedeMoverA(jugadorFila - 1, jugadorColumna)) jugadorFila--;
                break;
            case "S":
                if (puedeMoverA(jugadorFila + 1, jugadorColumna)) jugadorFila++;
                break;
            case "A":
                if (puedeMoverA(jugadorFila, jugadorColumna - 1)) jugadorColumna--;
                break;
            case "D":
                if (puedeMoverA(jugadorFila, jugadorColumna + 1)) jugadorColumna++;
                break;
        }
    }

    private boolean puedeMoverA(int fila, int columna) {
        if (fila < 0 || fila >= mapa.size()) return false;
        String linea = mapa.get(fila);
        if (columna < 0 || columna >= linea.length()) return false;
        return linea.charAt(columna) != '#';
    }

    private String reemplazarJugador(String linea, int indice, char nuevoCaracter) {
        StringBuilder nuevaLinea = new StringBuilder(linea);
        if (indice >= 0 && indice < nuevaLinea.length()) {
            nuevaLinea.setCharAt(indice, nuevoCaracter);
        }
        return nuevaLinea.toString();
    }
}

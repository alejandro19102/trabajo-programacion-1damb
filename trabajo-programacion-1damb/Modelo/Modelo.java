package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        switch (direccion.toUpperCase()) {
            case "W" -> { if (puedeMoverA(jugadorFila - 1, jugadorColumna)) jugadorFila--; }
            case "S" -> { if (puedeMoverA(jugadorFila + 1, jugadorColumna)) jugadorFila++; }
            case "A" -> { if (puedeMoverA(jugadorFila, jugadorColumna - 1)) jugadorColumna--; }
            case "D" -> { if (puedeMoverA(jugadorFila, jugadorColumna + 1)) jugadorColumna++; }
        }
    }

    private boolean puedeMoverA(int fila, int columna) {
        if (fila < 0 || fila >= mapa.size()) return false;
        String linea = mapa.get(fila);
        return columna >= 0 && columna < linea.length() && linea.charAt(columna) != '#';
    }

    private String reemplazarJugador(String linea, int indice, char nuevoCaracter) {
        StringBuilder nuevaLinea = new StringBuilder(linea);
        if (indice >= 0 && indice < nuevaLinea.length()) {
            nuevaLinea.setCharAt(indice, nuevoCaracter);
        }
        return nuevaLinea.toString();
    }
}


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    // Función main
    public static void main(String[] args) {
        String nombreArchivo = "configuracion.txt";
        File archivo = new File(nombreArchivo);

        if (archivo.exists()) {
            System.out.println("El archivo configuracion ya existe");
        } else {
            try {
                if (archivo.createNewFile()) {
                    System.out.println("Archivo '" + nombreArchivo + "' creado correctamente.");
                }
                // Crear los directorios requeridos
                crearDirectorio("jugador");
                crearDirectorio("escenario");
                crearDirectorio("juego");

                // Crear archivos de escenarios dentro del directorio "escenario"
                String[] escenarios = {"escenario1.txt", "escenario2.txt", "escenario3.txt", "escenario4.txt"};
                for (int i = 0; i < escenarios.length; i++) {
                    if (i == 0) {
                        crearEscenarioConObstaculos("escenario/" + escenarios[i]);
                    } else {
                        crearArchivo("escenario/" + escenarios[i]);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }

        // Cargar y mostrar el escenario
        cargarEscenario("escenario/escenario1.txt");
    }

    // Función para crear directorios introduciendo el nombre
    private static void crearDirectorio(String nombreDirectorio) {
        File directorio = new File(nombreDirectorio);
        if (!directorio.exists()) {
            if (directorio.mkdir()) {
                System.out.println("Directorio '" + nombreDirectorio + "' creado correctamente.");
            } else {
                System.out.println("Error al crear el directorio '" + nombreDirectorio + "'.");
            }
        } else {
            System.out.println("El directorio '" + nombreDirectorio + "' ya existe.");
        }
    }

    // Función para crear archivos de escenario vacíos
    private static void crearArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write("Escenario: " + nombreArchivo.replace(".txt", ""));
            writer.newLine();
            writer.write("Este es un escenario vacío.");
            writer.newLine();
            System.out.println("Archivo creado: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo " + nombreArchivo);
            e.printStackTrace();
        }
    }

    // Función para crear un escenario con obstáculos 
    private static void crearEscenarioConObstaculos(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            int filas = 10;
            int columnas = 20;
            writer.write(filas + " " + columnas);
            writer.newLine();
            
            String[] mapa = { //escenario que se carga
                "####################",
                "#       ##        #",
                "#  ####  ##  #### #",
                "#       ##        #",
                "##### ###### #####",
                "#       ##        #",
                "#  ####  ##  #### #",
                "#       ##        #",
                "####################"
            };
            
            for (String linea : mapa) {
                writer.write(linea);
                writer.newLine();
            }
            writer.write(filas + " " + columnas);
            writer.newLine();
            System.out.println("Archivo con obstáculos creado: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo con obstáculos " + nombreArchivo);
            e.printStackTrace();
        }
    }

    // Función para cargar y mostrar un escenario
    private static void cargarEscenario(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            System.out.println("El archivo " + nombreArchivo + " no existe.");
            return;
        }

        List<String> escenario = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                escenario.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo " + nombreArchivo);
            e.printStackTrace();
        }

        // Mostrar el escenario cargado
        System.out.println("Escenario cargado desde " + nombreArchivo + ":");
        for (String linea : escenario) {
            System.out.println(linea);
        }
    }
}

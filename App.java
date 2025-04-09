import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Programa Main para Juego de plataformas.
 * Incluye la creación de directorios, archivos de escenarios y almacenamiento de datos del usuario.
 *
 * @author Alejandro
 * @author Guillermo
 * 
 */
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

        // Solicitar y guardar datos del usuario
        guardarDatosUsuario();

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

            String[] mapa = {
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

    // Funcion para itroducir un escenario y que inserte el muñeco del usuario
    private static void insertarJugador(List <String> escenario){
        if (esccenario.size() > 1){
            String  linea = escenario.get(1);
            if(linea.length() >1){
                String nuevaLinea = linea.substring(0,1)+"☠️"+ linea.substring(2);
                escenario.set(1, nuevaLinea);
            }
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

    // Función para pedir al usuario el nombre de usuario y email y guardar los datos en un fichero .txt
    private static void guardarDatosUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce tu nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Introduce tu email: ");
        String email = scanner.nextLine();

        String rutaArchivo = "jugador/datos_usuario.txt";
        File archivo = new File(rutaArchivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write("Nombre de usuario: " + nombreUsuario);
            writer.newLine();
            writer.write("Email: " + email);
            writer.newLine();
            System.out.println("Datos guardados en " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos del usuario en " + rutaArchivo);
            e.printStackTrace();
        }
    }
}

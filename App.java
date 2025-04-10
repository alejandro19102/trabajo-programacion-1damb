import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Programa Main para Juego de plataformas.
 * Incluye la creaciC3n de directorios, archivos de escenarios y almacenamiento de datos del usuario.
 *
 * @author Alejandro
 * @author Guillermo
 *
 */
public class Main {
	// FunciC3n main
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

	// FunciC3n para crear directorios introduciendo el nombre
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

	// FunciC3n para crear archivos de escenario vacC-os
	private static void crearArchivo(String nombreArchivo) {
		File archivo = new File(nombreArchivo);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
			writer.write("Escenario: " + nombreArchivo.replace(".txt", ""));
			writer.newLine();
			writer.write("Este es un escenario vacC-o.");
			writer.newLine();
			System.out.println("Archivo creado: " + nombreArchivo);
		} catch (IOException e) {
			System.err.println("Error al crear el archivo " + nombreArchivo);
			e.printStackTrace();
		}
	}

	// FunciC3n para crear un escenario con obstC!culos
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
			System.out.println("Archivo con obstC!culos creado: " + nombreArchivo);
		} catch (IOException e) {
			System.err.println("Error al crear el archivo con obstC!culos " + nombreArchivo);
			e.printStackTrace();
		}
	}

	/* Funcion para itroducir un escenario y que inserte el muC1eco del usuario
	private static void insertarJugador(List <String> escenario) {
		if (escenario.size() > 1) {
			String  linea = escenario.get(1);
			if(linea.length() >1) {
				String nuevaLinea = linea.substring(0,1)+"b o8"+ linea.substring(2);
				escenario.set(1, nuevaLinea);
			}
		}
	}*/

	// FunciC3n para cargar y mostrar un escenario
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

		// Insertar jugador inicial en posiciC3n (1,1)
		int jugadorFila = 1;
		int jugadorColumna = 1;

		Scanner scanner = new Scanner(System.in);
		String comando;

		do {
			// Actualizar mapa con la posiciC3n del jugador
			List<String> mapaConJugador = new ArrayList<>(escenario);
			mapaConJugador.set(jugadorFila, reemplazarCaracter(mapaConJugador.get(jugadorFila), jugadorColumna, '☠'));


			// Mostrar mapa
			System.out.println("Escenario cargado desde " + nombreArchivo + ":");
			for (String linea : mapaConJugador) {
				System.out.println(linea);
			}

			System.out.print("Mover (W/A/S/D), Q para salir: ");
			comando = scanner.nextLine().toUpperCase();

			// Restaurar fondo antes de mover
			escenario.set(jugadorFila, reemplazarCaracter(escenario.get(jugadorFila), jugadorColumna, ' '));

			switch (comando) {
			case "W":
				if (jugadorFila > 0 && escenario.get(jugadorFila - 1).charAt(jugadorColumna) != '#') {
					jugadorFila--;
				}
				break;
			case "S":
				if (jugadorFila < escenario.size() - 1 && escenario.get(jugadorFila + 1).charAt(jugadorColumna) != '#') {
					jugadorFila++;
				}
				break;
			case "A":
				if (jugadorColumna > 0 && escenario.get(jugadorFila).charAt(jugadorColumna - 1) != '#') {
					jugadorColumna--;
				}
				break;
			case "D":
				if (jugadorColumna < escenario.get(jugadorFila).length() - 1 && escenario.get(jugadorFila).charAt(jugadorColumna + 1) != '#') {
					jugadorColumna++;
				}
				break;
			case "Q":
				System.out.println("Saliendo del juego...");
				break;
			default:
				System.out.println("Comando no vC!lido.");
			}

		} while (!comando.equals("Q"));
	}

	// FunciC3n para pedir al usuario el nombre de usuario y email y guardar los datos en un fichero .txt
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
	private static String reemplazarCaracter(String linea, int indice, char nuevoCaracter) {
		StringBuilder nuevaLinea = new StringBuilder(linea);
		nuevaLinea.setCharAt(indice, nuevoCaracter);
		return nuevaLinea.toString();
	}
}

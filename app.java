import java.io.File;

public class App{
    //funcion main
    public static void main(String[]args){
        String nombreArchivo = "configuracion.txt";
        File archivo = new File(nombreArchivo);
        if (archivo.exists()){
            System.out.println("El archivo configuracion ya exixte");
            return;}else{
            try {
                if (archivo.createNewFile()) {
                    System.out.println("Archivo '" + nombreArchivo + "' creado correctamente.");
                } // Crear los directorios requeridos
                crearDirectorio("jugador");
                crearDirectorio("escenario");
                crearDirectorio("juego");
            } catch (Exception e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }
    //Funcion para crear directorios introduciendo el nombre
    private static void crearDirectorio(String nombreDirectorio) {
        //Declaracion de Directorio
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
}

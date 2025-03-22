import java.io.File;

public class app{
  //funcion main
  public static void main(String[]args){
    // Declaraciond de variables
  String nombreArchivo = "configuracion.txt";
    File archivo = new File(configFilePath);
    if (configFile.exist()){
      System.out.println("El archivo configuracion ya exixte");
      return;}else{
      try {
        if (archivo.createNewFile()) {
        System.out.println("Archivo '" + nombreArchivo + "' creado correctamente.");
    } // Crear los directorios requeridos
                crearDirectorio("jugador");
                crearDirectorio("escenario");
                crearDirectorio("juego");
            } catch (IOException e) {
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

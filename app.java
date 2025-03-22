import java.io.File;

public class app{
  public static void main(String[]args){
  String configFilePath = "configuracion.txt";
    File configFile = new File(configFilePath);
    if (configFile.exist()){
      System.out.println("El archivo configuracion ya exixte");
      return;}
   System.out.println("El archivo no existe");
  }
}

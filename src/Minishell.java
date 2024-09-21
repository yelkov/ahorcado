import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Minishell {
    public static void main(String[] args) {
        String directorioActual = System.getProperty("user.dir");

        Path directorio = Path.of(directorioActual);
        Scanner sc = new Scanner(System.in);
        Boolean salida = false;

        while(!salida){
            System.out.println(directorio.toAbsolutePath() + ">");
            String input = sc.nextLine();
            String comando = input.split(" ",2)[0];

            switch(comando){
                case "exit":
                    salida = true;
                    break;
                case "ls":
                    ListarDirectorio.listarDirectorio(directorio);
                    break;
                case "ls -R":
                    ListarDirectorioRecursivo.listarDirectorio(directorio,0);
                    break;
                case "pwd":
                    System.out.println(directorio.toAbsolutePath().toString() + "\n");
                    break;
                case "cd":
                    directorio = cambiarDirectorio(input, directorio);
                    break;
                case "cat":
                    leerArchivo(input,directorio);
                    break;
                default:
                        System.out.println('"'+input.toString()+'"' + " no se reconoce como un comando.");

            }
        }
    }

    private static Path cambiarDirectorio(String input, Path directioActual) {
        String nuevaRuta = input.replace("cd ","");
        Path nuevoDirectorio = directioActual.resolve(nuevaRuta).normalize();

        if(Files.isDirectory(nuevoDirectorio)){
            return nuevoDirectorio;
        }
        else{
            System.out.println("El sistema no puede encontrar la ruta especificada.");
            return directioActual;
        }
    }

    private static void leerArchivo(String input, Path directorioActual){
        String fichero = input.replace("cat ","");
        Path rutaArchivo = directorioActual.resolve(fichero);

        if (Files.isRegularFile(rutaArchivo)){
            try{
                List<String> lineas = Files.readAllLines(rutaArchivo);
                for (String linea : lineas){
                    System.out.println(linea);
                }
            }
            catch (IOException e){
                System.out.println("Error al abrir el archivo.");
            }
        }else{
            System.out.println("El archivo especificado no se encuentra o no puede ser leído.");
        }
    }

}

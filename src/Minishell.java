import java.nio.file.Path;
import java.util.Scanner;

public class Minishell {
    public static void main(String[] args) {
        String directorioActual = System.getProperty("user.dir");

        Path directorio = Path.of(directorioActual);
        Scanner sc = new Scanner(System.in);
        Boolean salida = false;
        System.out.println(">" + directorio.getFileName());

        while(!salida){
            String input = sc.nextLine();

            switch(input){
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
                    System.out.println(directorio.toAbsolutePath().toString());
                    break;
                default:
                    if (input.startsWith("cd")){
                        directorio = cambiarDirectorio(input);
                    }else{
                        System.out.println("El comando introducido es desconocido");
                    }
            }
        }
    }

    private static Path cambiarDirectorio(String input) {
        Path directorio;
        String nuevaRuta = input.replace("cd ","");
        directorio = Path.of(nuevaRuta);
        System.out.println(">" + directorio.toAbsolutePath().toString());
        return directorio;
    }

}

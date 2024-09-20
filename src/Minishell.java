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

            if (input.equals("exit")){
                salida = true;
            }
            if (input.equals("ls")){
                ListarDirectorio.listarDirectorio(directorio);
            }
            if (input.equals("ls -r")){
                ListarDirectorioRecursivo.listarDirectorio(directorio,0);
            }
            if (input.startsWith("cd")){
                directorio = cambiarDirectorio(input);
            }
            if (input.equals("pwd")){
                System.out.println(directorio.toAbsolutePath().toString());
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

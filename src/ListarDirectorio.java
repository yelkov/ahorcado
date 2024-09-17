import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class ListarDirectorio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        Path dir = Path.of(nombre);
        if (Files.isDirectory(dir)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir);) {
                for (Path fichero: stream) {
                    StringBuilder sb = extraerPermisos(fichero);
                    sb.append(" " + fichero.toFile().getName());
                    System.out.println(sb.toString());
                }
            } catch (IOException | DirectoryIteratorException ex) {
                System.err.println(ex);
            }
        } else {
            System.err.println(dir.toString()+" no es un directorio");
        }
    }

    private static StringBuilder extraerPermisos(Path fichero){
        StringBuilder sb = new StringBuilder();
        File file = fichero.toFile();
        if (file.isDirectory()) {sb.append("d");}
            else{sb.append("-");}
        if (file.canRead()) {sb.append("r");}
            else{sb.append("-");}
        if (file.canWrite()) {sb.append("w");}
            else{sb.append("-");}
        if (file.canExecute()) {sb.append("x");}
            else{sb.append("-");}

            return sb;
    }
}
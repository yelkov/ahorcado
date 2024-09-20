import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class ListarDirectorio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce ruta del directorio: ");
        String nombre = sc.nextLine();

        Path dir = Path.of(nombre);
        if (Files.isDirectory(dir)) {
            listarDirectorio(dir);
        } else {
            System.err.println(dir.toString()+" no es un directorio");
        }
    }

    public static void listarDirectorio(Path dir) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir);) {
            for (Path fichero: stream) {
                StringBuilder sb = extraerPermisos(fichero);
                sb.append(" " + fichero.toFile().getName());
                if (fichero.toFile().isDirectory()) {
                    sb.append("/");
                }
                System.out.println(sb.toString());
            }
        } catch (IOException | DirectoryIteratorException ex) {
            System.err.println(ex);
        }
    }

    private static StringBuilder extraerPermisos(Path fichero){
        StringBuilder sb = new StringBuilder();
        File file = fichero.toFile();

        sb.append(file.isDirectory()? "d" : "-");
        sb.append(file.canRead()? "r" : "-");
        sb.append(file.canWrite()? "w" : "-");
        sb.append(file.canExecute()? "x" : "-");

        return sb;
    }
}
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class ListarDirectorioRecursivo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce ruta del directorio: ");
        String nombre = sc.nextLine();

        Path dir = Path.of(nombre);
        if (Files.isDirectory(dir)) {
            listarDirectorio(dir,0);
        } else {
            System.err.println(dir.toString()+" no es un directorio");
        }
    }

    public static void listarDirectorio(Path dir, int nivel) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir);) {
            for (Path fichero: stream) {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < nivel; i++){
                    sb.append("\t");
                }
                sb.append(extraerPermisos(fichero));
                sb.append(" " + fichero.toFile().getName());
                if (fichero.toFile().isDirectory()) {
                    sb.append("/");
                }
                System.out.println(sb.toString());

                Deque<Path> stack = new ArrayDeque<Path>();
                if(fichero.toFile().isDirectory()){
                    stack.push(fichero);
                }

                while (!stack.isEmpty()) {
                    Path p = stack.pop();
                    listarDirectorio(p, nivel+1);
                }
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
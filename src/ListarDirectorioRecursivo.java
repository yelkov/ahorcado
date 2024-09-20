import java.io.File;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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
            List<Path> archivos = new ArrayList<>();
            List<Path> subdirectorios = new ArrayList<>();


            for (Path fichero: stream) {
                if (fichero.toFile().isDirectory()) {
                    subdirectorios.add(fichero);
                } else {
                    archivos.add(fichero);
                }
            }
                for ( Path archivo : archivos){
                    printarFichero(archivo, nivel,false);
                }

                for (Path subdirectorio : subdirectorios) {
                    printarFichero(subdirectorio,nivel,true);
                    listarDirectorio(subdirectorio, nivel+1);
                }
            } catch (IOException | DirectoryIteratorException ex) {
            System.err.println(ex);
        }

    }

    private static void printarFichero(Path archivo, int nivel, Boolean esDirectorio) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nivel; i++){
            sb.append("\t");
        }
        sb.append(extraerPermisos(archivo));
        sb.append(" "+ archivo.toFile().getName());
        if(esDirectorio){
            sb.append("/");
        }
        System.out.println(sb.toString());
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
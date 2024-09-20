import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Ordenaciones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa ruta del archivo: ");
        String archivo = sc.nextLine();
        System.out.println("Ingresa el tipo de ordenación\n (A - ascendente case sensitive)\n (a - ascendente case insensitive)\n (D - descendente case sensitive)\n (d - descendente case insensitive)\n: ");
        String tipoOrden = sc.nextLine();

        try{
            List<String> lineas = Files.readAllLines(Paths.get(archivo));
            List<String> listaOrdenada = ordenarLineas(lineas, tipoOrden);
            String nombreArchivo = asignarNombre(archivo, tipoOrden);

            BufferedWriter archivoOrdenado = Files.newBufferedWriter(Paths.get(nombreArchivo));

            for (String linea : listaOrdenada) {
                archivoOrdenado.write(linea);
                archivoOrdenado.newLine();
            }
            archivoOrdenado.close();

            System.out.println("Escritura finalizada con éxito.");

        }catch (IOException e) {
                System.err.println("No se ha realizado la escritura " + e.getMessage());
           }
        catch (IllegalArgumentException e){
            System.err.println("El tipo de ordenación no es válido. ");
        }

    }

    public static List<String> ordenarLineas(List<String> lineas, String tipoOrdenacion) throws IllegalArgumentException {
        switch (tipoOrdenacion) {
            case "A":
                return lineas.stream().sorted().toList();
            case "a":
                return lineas.stream().sorted(String.CASE_INSENSITIVE_ORDER).toList();
            case "D":
                return lineas.stream().sorted(Comparator.reverseOrder()).toList();
            case "d":
                return lineas.stream().sorted(String.CASE_INSENSITIVE_ORDER.reversed()).toList();
            default:
                throw new IllegalArgumentException("Tipo de ordenación no válido.");
            }
        }
        public static String asignarNombre(String nombreArchivo, String tipoOrdenacion) throws IllegalArgumentException {
            int indiceExtension = nombreArchivo.lastIndexOf(".");
            nombreArchivo = nombreArchivo.substring(0,indiceExtension);

            switch (tipoOrdenacion) {
                case "A":
                    return nombreArchivo + "_asc_case.txt";
                case "a":
                    return nombreArchivo + "_asc_non_case.txt";
                case "D":
                    return nombreArchivo + "_desc_case.txt";
                case "d":
                    return nombreArchivo + "_desc_non_case.txt";
                default:
                    throw new IllegalArgumentException("Tipo de ordenación no válido.");
            }
        }
}

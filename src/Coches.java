import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Coches {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta del archivo: ");
        String archivo = sc.nextLine();

        try{
            List<String> lineas = Files.readAllLines(Paths.get(archivo));
            Map<String,List<String>> tablaMarcas = new HashMap<>();

            for ( String linea : lineas){
                String[] partes = linea.split(" ",2);
                String marca = partes[0];
                String modelo = partes[1];

                tablaMarcas.putIfAbsent(marca,new ArrayList<>());
                if (!tablaMarcas.get(marca).contains(modelo)){
                    tablaMarcas.get(marca).add(modelo);
                }
            }

            BufferedWriter archivoMarcas = Files.newBufferedWriter(Paths.get("res/marcas.txt"));
            String textoMarcas = formatearTabla(tablaMarcas).toString();
            archivoMarcas.write(textoMarcas);
            archivoMarcas.close();
            System.out.println("La escritura se realizó con éxito.");

        }catch (IOException e){
            System.out.println("No se ha realizado la escritura.");
        }

    }
    public static StringBuilder formatearTabla(Map<String,List<String>> tablaMarcas){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,List<String>> entry : tablaMarcas.entrySet()){
            sb.append(entry.getKey()).append(": ");
            sb.append(String.join(", ",entry.getValue())).append("\n");
        }
        return sb;
    }
}

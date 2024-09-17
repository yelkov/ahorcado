import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CaracterMasRepetido {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       System.out.println("Introduce ruta del archivo: ");
       String archivo = sc.nextLine();

           try (BufferedReader lector = new BufferedReader(new FileReader(archivo));) {
               String linea;
               Map<Character,Integer> ocurrenciasCaracteres = new HashMap<>();
               while ((linea = lector.readLine()) != null){
                    for (Character c: linea.toCharArray()){
                        ocurrenciasCaracteres.putIfAbsent(c,1);
                        ocurrenciasCaracteres.computeIfPresent(c,(k,v)-> v+1);
                    }
               }

               Character caracterMasRepetido = '\0';
               Integer maxOcurrencias = 0;
               for (Map.Entry<Character,Integer> entry : ocurrenciasCaracteres.entrySet()){
                   if (entry.getValue() > maxOcurrencias){
                       caracterMasRepetido = entry.getKey();
                       maxOcurrencias = entry.getValue();
                   }
               }
               System.out.println("El caracter más repetido es: " + caracterMasRepetido + " y se repite " + maxOcurrencias + " veces.");

           } catch (IOException e) {
                System.err.println(e.getMessage());
           }
   }
}

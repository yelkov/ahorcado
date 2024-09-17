import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ContarCaracter {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       System.out.println("Introduce ruta del archivo: ");
       String archivo = sc.nextLine();
       System.out.println("Introduce caracter a buscar: ");
       Character caracter = sc.next().charAt(0);

           try (BufferedReader lector = new BufferedReader(new FileReader(archivo));) {
               String linea;
               Integer contador = 0;
               while ((linea=lector.readLine()) != null)
               {
                   for (Character c : linea.toCharArray()){
                       if (c.equals(caracter)){
                           contador++;
                       }
                   }
               }
               System.out.println("El archivo contiene el caracter " + caracter + " " + contador + " veces.");


           } catch (IOException e) {
                System.err.println(e.getMessage());
           }
   }
}

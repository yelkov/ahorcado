import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CaracterMasRepetido {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       System.out.println("Introduce ruta del archivo: ");
       String archivo = sc.nextLine();

           try (BufferedReader lector = new BufferedReader(new FileReader(archivo));) {
               String linea;


           } catch (IOException e) {
                System.err.println(e.getMessage());
           }
   }
}

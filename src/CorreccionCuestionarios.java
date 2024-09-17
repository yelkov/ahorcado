import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CorreccionCuestionarios {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce ruta del archivo: ");
        String archivo = sc.nextLine();

        try{
            List<String> lineas = Files.readAllLines(Paths.get(archivo));
            String respuestasValidas = lineas.get(0);
            Map<String,Double> resultados = new HashMap<>();

            for (int i = 1; i < lineas.size(); i++){
                String[] partes = lineas.get(i).split(" ",2);
                String codigoAlumno = partes[0];
                String respuestasAlumno = partes[1];
                resultados.put(codigoAlumno,0.0);


                for (int j = 0; j < respuestasAlumno.length(); j++){
                    if (respuestasAlumno.charAt(j) == respuestasValidas.charAt(j)){
                        resultados.compute(codigoAlumno,(k,v)-> v + 0.5);
                    }else if(respuestasAlumno.charAt(j) == ' '){
                        continue;
                    }else{
                        resultados.compute(codigoAlumno,(k,v)-> v - 0.15);
                    }
                }

                }
            System.out.println(resultados);
            } catch (IOException e) {
        System.err.println(e.getMessage());
    }
    }
}

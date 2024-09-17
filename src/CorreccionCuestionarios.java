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
            System.out.println(printarResultados(resultados).toString());

            } catch (IOException e) {
        System.err.println(e.getMessage());
    }
    }

    public static StringBuilder printarResultados(Map<String,Double> resultados){
        StringBuilder sb = new StringBuilder();
        sb.append("Alumno:").append("\t\t").append("Nota:").append("\t\t").append("Calificación:\n");

        for (Map.Entry<String,Double> entry : resultados.entrySet()){
            sb.append(entry.getKey()).append("\t\t");
            sb.append(String.format("%.2f",entry.getValue())).append("\t\t");
            sb.append(obtenerCalificacion(entry.getValue())).append("\n");
        }
        return sb;
    }

    public static String obtenerCalificacion(Double nota){
        String calificación;

        calificación = nota > 8.5? "Excelente" :
                        nota > 7.0 && nota < 8.49? "Notable" :
                        nota > 6 && nota < 6.99? "Bien" :
                        nota > 5 && nota < 5.99? "Aprobado" :
                        "Suspenso";

        return calificación;
        }
}

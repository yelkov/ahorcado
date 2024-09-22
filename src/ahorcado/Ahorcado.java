package ahorcado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Ahorcado {
    public static void main(String[] args) {
        Game game = null;
        System.out.println("¡Bienvenido al juego del Ahorcado!");
        System.out.println("¿Quieres cargar un juego existente? (s/N)");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine().toLowerCase();

        if(answer.equals("s")){
            System.out.println("Introduce la ruta completa del juego a cargar");
            String path = sc.nextLine();
            game = Game.loadGame(path);
            System.out.println("Juego cargado:");
        }else{
            try {
                List<String> possibleWords = Files.readAllLines(Paths.get("res/ahorcado/palabras.txt"));
                String secretWord = possibleWords.get(new Random().nextInt(0,possibleWords.size()));
                game = new Game(secretWord,11);
            }
            catch (IOException e){
                System.out.println("Se produjo un error al generar la palabra secreta.");
            }
        }

        Drawing drawing = new Drawing(game);

        while(!game.hasWon() && !game.hasLost()){
            drawing.setDrawing();
            System.out.println(drawing.getDrawing().toString());
            System.out.println(game.getState());
            String attempt = sc.nextLine().toLowerCase();

            if(attempt.equals("exit")) {
                System.out.println("¿Deseas guardar la partida? (s/N)");
                if(sc.nextLine().toLowerCase().equals("s")){
                    System.out.println("Introduce el nombre completo del archivo a guardar: ");
                    game.saveGame(sc.nextLine().toLowerCase());
                    break;
                }else{
                    System.out.println("El juego no se ha guardado.");
                    break;
                }
            }else if (attempt.length()>1){
                game.guessWord(attempt);
            }else if (attempt.length() == 1){
                game.guessLetter(attempt.toCharArray()[0]);
            }else{
                System.out.println("Recuerda introducir una letra o una palabra. Escribe exit para salir.");
            }
        }

        if(game.hasWon()){
            System.out.println("¡ENHORABUENA, HAS GANADO!");
            System.out.println("La palabra era: " + game.getSecretWord());
            System.out.println("Lo has conseguido en " + game.getAttempts() + " intentos.");
        }else if(game.hasLost()){
            drawing.setDrawing();
            System.out.println(drawing.getDrawing().toString());
            System.out.println("Lo siento, ¡HAS PERDIDO!. La palabra era: " + game.getSecretWord());
            System.out.println("Has superado el número máximo de errores.");;
        }else{
            System.out.println("¡Hasta la próxima!");
        }
    }
}

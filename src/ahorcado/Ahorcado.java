package ahorcado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Ahorcado {
    public static void main(String[] args) {
        Boolean ahorcadoIsExit = false;
        Game game = null;
        while (!ahorcadoIsExit) {
            System.out.println("\n ¡Bienvenido al juego del Ahorcado! Escriba exit para salir.");
            System.out.println("¿Quieres cargar un juego existente? (s/N)");
            Scanner sc = new Scanner(System.in);
            String answer = sc.nextLine().toLowerCase();

            if (answer.equals("s")) {
                game = loadSavedGame(sc, game);
            } else {
                game = createNewGame(sc, game);
            }

            Drawing drawing = new Drawing(game);

            while (!game.hasWon() && !game.hasLost()) {
                drawing.setDrawing();
                System.out.println(drawing.getDrawing().toString());
                System.out.println(game.getState());
                String attempt = sc.nextLine().toLowerCase();

                if (attempt.equals("exit")) {
                    System.out.println("¿Deseas guardar la partida? (s/N)");
                    if (sc.nextLine().toLowerCase().equals("s")) {
                        System.out.println("Introduce el nombre completo del archivo a guardar: ");
                        try {
                            game.saveGame(sc.nextLine().toLowerCase());
                            break;
                        } catch (IOException e) {
                            System.out.println("Se produjo un error al guardar la partida.");
                        }
                    } else {
                        System.out.println("El juego no se ha guardado.");
                        break;
                    }
                    ahorcadoIsExit = setExit(sc);
                } else if (attempt.length() > 1) {
                    game.guessWord(attempt);
                } else if (attempt.length() == 1) {
                    game.guessLetter(attempt.toCharArray()[0]);
                } else {
                    System.out.println("Recuerda introducir una letra o una palabra. Escribe exit para salir.");
                }
            }

            if (game.hasWon()) {
                System.out.println("¡ENHORABUENA, HAS GANADO!");
                System.out.println("La palabra era: " + game.getSecretWord());
                System.out.println("Lo has conseguido en " + game.getAttempts() + " intentos.");
                ahorcadoIsExit = setExit(sc);
            } else if (game.hasLost()) {
                drawing.setDrawing();
                System.out.println(drawing.getDrawing().toString());
                System.out.println("Lo siento, ¡HAS PERDIDO!. La palabra era: " + game.getSecretWord());
                System.out.println("Has superado el número máximo de errores.");
                ahorcadoIsExit = setExit(sc);
            }
        }
        System.out.println("¡Hasta la próxima!.");
    }

    private static Game createNewGame(Scanner sc, Game game) {
        try {
            List<String> possibleWords = Files.readAllLines(Paths.get("res/ahorcado/palabras.txt"));
            String secretWord = possibleWords.get(new Random().nextInt(0, possibleWords.size()));
            System.out.println("Elige el nivel de dificultad (EASY | medium | hard): ");
            String difficulty = sc.nextLine().toLowerCase();
            game = new Game(secretWord, difficulty);
            System.out.println("Introduce una letra o intenta adivinar la palabra. ¡Suerte!: ");
        } catch (IOException e) {
            System.out.println("Se produjo un error al generar la palabra secreta.");
        }
        return game;
    }

    private static Game loadSavedGame(Scanner sc, Game game) {
        System.out.println("Introduce la ruta completa del juego a cargar");
        String path = sc.nextLine();
        try {
            game = Game.loadGame(path);
        } catch (IOException e) {
            System.out.println("Se produjo un error al cargar el juego.");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.out.println("No se encuentra la ruta especificada.");
            System.exit(1);
        }
        System.out.println("Juego cargado: ");
        return game;
    }

    private static Boolean setExit(Scanner sc) {
        System.out.println("\n ¿Deseas jugar otra partida? (s/N)");
        if (sc.nextLine().toLowerCase().equals("s")) {
            return false;
        }else{
            return true;
        }
    }
}
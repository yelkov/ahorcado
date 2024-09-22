package ahorcado;

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
        }else{
            game = new Game("colonoscopia",25);
        }


        while(!game.hasWon() && !game.hasLost()){
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
            System.out.println("Lo siento, ¡HAS PERDIDO!. La palabra era: " + game.getSecretWord());
            System.out.println("Has superado el número máximo de errores.");;
        }else{
            System.out.println("¡Hasta la proxima!");
        }
    }
}

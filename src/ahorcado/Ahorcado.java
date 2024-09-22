package ahorcado;

import java.util.Scanner;

public class Ahorcado {
    public static void main(String[] args) {
        Game game = new Game("colonoscopia",25);
        Scanner sc = new Scanner(System.in);

        while(!game.hasWon() && !game.hasLost()){
            System.out.println(game.getState());
            String attempt = sc.nextLine().toLowerCase();

            if (attempt.length()>1){
                game.guessWord(attempt);
            }else if (attempt.length() == 1){
                game.guessLetter(attempt.toCharArray()[0]);
            }else{
                System.out.println("Recuerda introducir una letra o una palabra");
            }
        }

        if(game.hasWon()){
            System.out.println("¡ENHORABUENA, HAS GANADO!");
            System.out.println("La palabra era: " + game.getSecretWord());
            System.out.println("Lo has conseguido en " + game.getAttempts() + " intentos.");
        }else{
            System.out.println("Lo siento, ¡HAS PERDIDO!. La palabra era: " + game.getSecretWord());
            System.out.println("Has superado el número máximo de errores.");;
        }
    }
}

package ahorcado;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {
    private static final long serialversionUID = 1L;

    private final String secretWord;
    private StringBuilder state;
    private Integer errors;
    private final Integer maxErrors = 11;
    private Integer attempts;
    private List<Character> guessedCharacters = new ArrayList<Character>();

    public Game(String secretWord, String difficulty) {
        this.secretWord = secretWord.toLowerCase();
        this.state = new StringBuilder("-".repeat(secretWord.length()));
        this.attempts = 0;
        setErrors(difficulty);
    }

    public String getSecretWord(){
        return secretWord;
    }

    public StringBuilder getState(){
        return this.state;
    }

    public Integer getErrors(){
        return this.errors;
    }

    public Integer getMaxErrors(){
        return this.maxErrors;
    }

    public Integer getAttempts(){
        return this.attempts;
    }

    public void setErrors(String difficulty){
        switch(difficulty.toLowerCase()){
            case "medium":
                this.errors = 5;
                break;
            case "hard":
                this.errors = 10;
                break;
            default:
                this.errors = 0;
                break;
        }
    }

    public void guessLetter(Character letter){
        if (secretWord.contains(letter.toString()) && !guessedCharacters.contains(letter)){
            guessedCharacters.add(letter);
            for (int i = 0; i < secretWord.length(); i++){
                if (secretWord.charAt(i) == letter){
                    state.setCharAt(i,letter);
                }
            }
            attempts++;
        }else{
            errors++;
            attempts++;
        }
    }

    public void guessWord(String word){
        if (secretWord.equals(word)){
            state.setLength(0);
            state.append(word);
            attempts++;
        }else{
            errors++;
            attempts++;
        }
    }

    public boolean hasWon(){
        return secretWord.equals(state.toString());
    }

    public boolean hasLost(){
        return errors >= maxErrors;
    }

    public void saveGame(String fileName){
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName+ ".bin"))){
            writer.writeObject(this);
            System.out.println("El juego se guardó con éxito.");
        }catch (IOException e){
            System.out.println("Se produjo un error en el guardado.");
            System.err.println(e);
        }
    }

    public static Game loadGame(String fileName){
        Game game = null;
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName))){
                Object o = reader.readObject();
                if (o instanceof Game){
                    game = (Game) o;
                }
        }catch (EOFException e){
            System.out.println("Fin de archivo alcanzado inesperadamente.");
        }catch (IOException e){
            System.out.println("Se produjo un error en la lectura del archivo.");
            System.err.println(e);
        }catch (ClassNotFoundException e){
            System.err.println(e);
        }
        return game;
    }
}



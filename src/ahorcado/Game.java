package ahorcado;

import java.io.Serializable;

public class Game implements Serializable {
    private static final long serialversionUID = 1L;

    private String secretWord;
    private StringBuilder state;
    private Integer errors;
    private Integer maxErrors;
    private Integer attempts;

    public Game(String secretWord, Integer maxErrors){
        this.secretWord = secretWord.toLowerCase();
        this.state = new StringBuilder("-".repeat(secretWord.length()));
        this.errors = 0;
        this.maxErrors = maxErrors;
        this.attempts = 0;
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

    public void guessLetter(Character letter){
        if (secretWord.contains(letter.toString())){
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
}



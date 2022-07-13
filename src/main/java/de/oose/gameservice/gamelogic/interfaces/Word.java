package de.oose.gameservice.gamelogic.interfaces;

public interface Word {

    /**
     * returns the word as a string with only the letters that are guessed visible
     * @return the word
     */
    String getWord();

    /**
     * sets the word to the given string
     * @param word the word
     * @throws Exception if the word is already set
     */
    void setWord(String word) throws Exception;

    /**
     * guesses a letter and returns if the letter is correct
     * @param letter the letter which will be guessed
     * @return true if the letter is correct, false if not
     */
    boolean guessLetter(char letter);

    /**
     * checks if the word is guessed
     * @return true if the word is guessed, false if not
     */
    boolean isWordGuessed();

    /**
     * resets the word
     */
    void resetWord();
}

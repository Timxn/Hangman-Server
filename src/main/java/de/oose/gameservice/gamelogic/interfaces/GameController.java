package de.oose.gameservice.gamelogic.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface GameController {
    /**
     * Adds a user to an existing game.
     * @param username
     * @param gameIdentifier the identifier of the game
     */
    public void joinGame(String gameIdentifier, String username) throws Exception;

    /**
     * removes a user from an existing game.
     * @param username
     * @param gameIdentifier the identifier of the game
     */
    public void leaveGame(String gameIdentifier, String username) throws Exception;

    /**
     * creates a new game.
     * @param username
     * @return 1000-9999 as identifier for the game
     */
    public String createGame(String username) throws Exception;

    /**
     * starts a new game.
     * @param gameIdentifier
     * @return true if game start was successfully
     */
    public boolean startGame(String gameIdentifier) throws Exception;

    /**
     * returns if a player is god
     * @param gameIdentifier
     * @param username
     * @return true if god...
     */
    public boolean isGod(String gameIdentifier, String username) throws Exception;

    /**
     * gives god the ability to set the word for the next round.
     * @param word the new word
     */
    void setWord(String gameIdentifier, String word, String username) throws Exception;

    /**
     * make a guess
     * @param letter
     */
    public void guessLetter(String gameIdentifier, char letter);

    /**
     * Get the players of the game in which the player is
     * @return ArrayList<Username>
     */
    public ArrayList<String> getPlayers(String gameIdentifier) throws Exception;

    /**
     * Get the word but with all unknown characters replaced by NULL and split by spaces so the client of the user has the minimum of the information
     * @return String (the word ex.: W NULL R D) (WORD) or null if no word exists yet (god has not yet set one)
     */
    public String getWord(String gameIdentifier) throws Exception;

    /**
     * Get all characters that have been tried in an ArrayList<Character>
     * @return ArrayList<Character>
     */
    public ArrayList<Character> getCharactersThatAlreadyHaveBeenTried(String gameIdentifier);


    /**
     * How many mistakes the guessers made
     *
     * @param gameIdentifier
     * @return int 0-9
     */
    int getMistakesMade(String gameIdentifier);

    /**
     * gets if the word has been guessed.
     * @return
     */
    public boolean getWordGuessed(String gameIdentifier);

    /**
     * get if game is started
     * @param gameIdentifier
     * @return
     */
    public boolean getStarted(String gameIdentifier) throws Exception;

    /**
     * get if god entered a valid word
     * @param gameIdentifier
     * @return
     */
    public boolean getWorded(String gameIdentifier) throws Exception;

    /**
     * Return the Username of the User whose turn it is to guess a character
     * @return String (Username ex.: Test)
     */
    public String whoseTurnIsIt();
}

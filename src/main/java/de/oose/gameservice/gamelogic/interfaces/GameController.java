package de.oose.gameservice.gamelogic.interfaces;

import java.util.ArrayList;

public interface GameController {

    /**
     * Adds a user to an existing game.
     * @param username user which will be added
     * @param gameIdentifier the identifier of the game
     */
    void joinGame(String gameIdentifier, String username) throws Exception;

    /**
     * removes a user from an existing game.
     * @param username user which will be removed
     * @param gameIdentifier the identifier of the game
     */
    void leaveGame(String gameIdentifier, String username) throws Exception;

    /**
     * creates a new game.
     * @param username user which will be added to the game
     * @return 1000-9999 as identifier for the game
     */
    String createGame(String username);

    /**
     * starts a new game.
     * @param gameIdentifier the identifier of the game
     */
    void startGame(String gameIdentifier) throws Exception;

    /**
     * returns if a player is god
     * @param gameIdentifier the identifier of the game
     * @param username the username of the player
     * @return true if god, false if not
     */
    boolean isGod(String gameIdentifier, String username) throws Exception;

    /**
     * gives god the ability to set the word for the next round.
     * @param word the new word
     */
    void setWord(String gameIdentifier, String word, String username) throws Exception;

    /**
     * make a guess
     * @param letter the letter which will be guessed
     */
    void guessLetter(String gameIdentifier, char letter, String username) throws Exception;

    /**
     * Get the players of the game in which the player is
     * @return ArrayList<Username>
     */
    ArrayList<String> getPlayers(String gameIdentifier) throws Exception;

    /**
     * Get the word but with all unknown characters replaced by NULL and split by spaces so the client of the user has the minimum of the information
     * @return String (the word ex.: W NULL R D) (WORD) or null if no word exists yet (god has not yet set one)
     */
    String getWord(String gameIdentifier) throws Exception;

    /**
     * Get all characters that have been tried in an ArrayList<Character> and are not correct
     * @return ArrayList<Character>
     */
    String getCharactersThatAlreadyHaveBeenTried(String gameIdentifier) throws Exception;


    /**
     * How many mistakes the guessers made
     *
     * @param gameIdentifier the identifier of the game
     * @return int 0-9
     */
    int getMistakesMade(String gameIdentifier) throws Exception;

    /**
     * gets if the word has been guessed.
     * @return true if word has been guessed, false if not
     */
    boolean getWordGuessed(String gameIdentifier) throws Exception;

    /**
     * get if game is started
     * @param gameIdentifier the identifier of the game
     * @return true if game is started, false if not
     */
    boolean getStarted(String gameIdentifier) throws Exception;

    /**
     * get if god entered a valid word
     * @param gameIdentifier the identifier of the game
     * @return true if god entered a valid word, false if not
     */
    boolean getWorded(String gameIdentifier) throws Exception;

    /**
     * Return the Username of the User whose turn it is to guess a character
     * @return String (Username ex.: Test)
     */
    String whoseTurnIsIt(String gameIdentifier) throws Exception;

    String getWinnerOfGame(String gameIdentifier) throws Exception;
}

package de.oose.gameservice.gamelogic.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface GameController {
    /**
     * Adds a user to an existing game.
     * @param username
     * @param gameIdentifier the identifier of the game
     */
    public void joinGame(String gameIdentifier, String username);

    /**
     * removes a user from an existing game.
     * @param username
     * @param gameIdentifier the identifier of the game
     */
    public void leaveGame(String gameIdentifier, String username);

    /**
     * creates a new game.
     * @param username
     * @return 1000-9999 as identifier for the game
     */
    public String createGame(String username);

    /**
     * starts a new game.
     * @return 0 = normal Player, 1 = god
     */
    public int startGame(String gameIdentifier);

    /**
     * gives god the ability to set the word for the next round.
     * @param word the new word
     */
    public void setWord(String gameIdentifier, String word);

    /**
     * make a guess
     * @param letter
     */
    public void guessLetter(String gameIdentifier, char letter);

    /**
     * Get the global scoreboard, provided with the integer mapped to the Username in a Hashmap
     * @return Hashmap<Username, Points>
     */
    public HashMap<String, Integer> getScoreboard();

    /**
     * Get the players of the game in which the player is
     * @return ArrayList<Username>
     */
    public ArrayList<String> getPlayers(String gameIdentifier);

    /**
     * Get the word but with all unknown characters replaced by NULL and split by spaces so the client of the user has the minimum of the information
     * @return String (the word ex.: W NULL R D) (WORD) or null if no word exists yet (god has not yet set one)
     */
    public ArrayList<Character> getWord(String gameIdentifier);

    /**
     * Get all characters that have been tried in an ArrayList<Character>
     * @return ArrayList<Character>
     */
    public ArrayList<Character> getCharactersThatAlreadyHaveBeenTried(String gameIdentifier);

    /**
     * How many tries have the Guesser left
     * @return int
     */
    public int howManyTriesAreLeft(String gameIdentifier);

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
    public boolean getStarted(String gameIdentifier);

    /**
     * Return the Username of the User whose turn it is to guess a character
     * @return String (Username ex.: Test)
     */
    public String whoseTurnIsIt();
}

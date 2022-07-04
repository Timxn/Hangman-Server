package de.oose.gameservice.gamelogic;

import de.oose.gameservice.gamelogic.interfaces.GameController;

import java.util.ArrayList;
import java.util.HashMap;

public class GameControllerImpl implements GameController {
    /**
     * Adds a user to an existing game.
     *
     * @param gameIdentifier the identifier of the game
     * @param username
     */
    @Override
    public void joinGame(String gameIdentifier, String username) {

    }

    /**
     * removes a user from an existing game.
     *
     * @param gameIdentifier the identifier of the game
     * @param username
     */
    @Override
    public void leaveGame(String gameIdentifier, String username) {

    }

    /**
     * creates a new game.
     *
     * @param username
     * @return 1000-9999 as identifier for the game
     */
    @Override
    public String createGame(String username) {
        return "null";
    }

    /**
     * starts a new game.
     *
     * @param gameIdentifier
     * @return 0 = normal Player, 1 = god
     */
    @Override
    public int startGame(String gameIdentifier) {
        return 0;
    }

    /**
     * returns if a player is god
     * @param gameIdentifier
     * @param username
     * @return true if god...
     */
    public boolean isGod(String gameIdentifier, String username) {return true;}

    /**
     * gives god the ability to set the word for the next round.
     *
     * @param gameIdentifier
     * @param word           the new word
     */
    @Override
    public void setWord(String gameIdentifier, String word) {

    }

    /**
     * make a guess
     *
     * @param gameIdentifier
     * @param letter
     */
    @Override
    public void guessLetter(String gameIdentifier, char letter) {

    }

    /**
     * Get the global scoreboard, provided with the integer mapped to the Username in a Hashmap
     *
     * @return Hashmap<Username, Points>
     */
    @Override
    public HashMap<String, Integer> getScoreboard() {
        return new HashMap<>();
    }

    /**
     * Get the players of the game in which the player is
     *
     * @param gameIdentifier
     * @return ArrayList<Username>
     */
    @Override
    public ArrayList<String> getPlayers(String gameIdentifier) {
        return new ArrayList<>();
    }

    /**
     * Get the word but with all unknown characters replaced by NULL and split by spaces so the client of the user has the minimum of the information
     *
     * @param gameIdentifier
     * @return String (the word ex.: W NULL R D) (WORD) or null if no word exists yet (god has not yet set one)
     */
    @Override
    public ArrayList<Character> getWord(String gameIdentifier) {
        return new ArrayList<>();
    }

    /**
     * Get all characters that have been tried in an ArrayList<Character>
     *
     * @param gameIdentifier
     * @return ArrayList<Character>
     */
    @Override
    public ArrayList<Character> getCharactersThatAlreadyHaveBeenTried(String gameIdentifier) {
        return new ArrayList<>();
    }

    /**
     * How many mistakes the guessers made
     *
     * @param gameIdentifier
     * @return int 0-9
     */
    @Override
    public int getMistakesMade(String gameIdentifier) {
        return 0;
    }

    /**
     * gets if the word has been guessed.
     *
     * @param gameIdentifier
     * @return
     */
    @Override
    public boolean getWordGuessed(String gameIdentifier) {
        return false;
    }

    /**
     * get if game is started
     *
     * @param gameIdentifier
     * @return
     */
    @Override
    public boolean getStarted(String gameIdentifier) {
        return false;
    }

    /**
     * Return the Username of the User whose turn it is to guess a character
     *
     * @return String (Username ex.: Test)
     */
    @Override
    public String whoseTurnIsIt() {
        return "null";
    }
}

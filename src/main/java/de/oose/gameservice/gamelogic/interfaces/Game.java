package de.oose.gameservice.gamelogic.interfaces;

import de.oose.gameservice.gamelogic.PlayerImpl;
import de.oose.gameservice.gamelogic.WordImpl;

import java.util.ArrayList;

public interface Game {

    /**
     * starts the game
     * @throws Exception if the game is already started
     */
    void startingGame() throws Exception;

    /**
     * adds a player to the game
     * @param username the username of the player
     * @throws Exception if the game is not started or the player is already in the game
     */
    void addPlayer(String username) throws Exception;

    /**
     * removes a player from the game
     * @param username username of the player
     * @throws Exception if player is not in the game
     */
    void removePlayer(String username) throws Exception;

    /**
     * returns the player with the given username
     * @param username username of the player
     * @return the player
     * @throws Exception if player is not in the game
     */
    PlayerImpl getPlayerByUsername(String username) throws Exception;

    /**
     * sets the word for the game
     * @param word the word
     * @param username the username of the player who tries to set the word
     * @throws Exception if the game is not started or the player is not god
     */
    void setWord(String word, String username) throws Exception;

    /**
     * guesses a letter
     * @param letter the letter which will be guessed
     * @param username the username of the player who tries to guess the letter
     * @throws Exception if the game is not started, or it's not the players turn
     */
    void guessLetter(char letter, String username) throws Exception;

    /**
     * checks if the word is guessed
     * @return true if the word is guessed, false if not
     */
    boolean isWordGuessed();

    /**
     * returns a list of all players in the game
     * @return ArrayList<Player> with all players
     */
    ArrayList<String> getPlayers();

    /**
     * returns which letters were already guessed
     * @return ArrayList<Character> with all guessed letters
     */
    String getGuessedWrongLetters();

    /**
     * returns the username of the player who won the game
     * @return String with the username
     */
    String getWinner();

    /**
     * returns the game id of the game
     * @return int with the game id
     */
    String getGameID();

    /**
     * returns if the game is started or not
     * @return true if the game is started, false if not
     */
    boolean isStarted();

    /**
     * returns the word object of the game
     * @return WordImpl with the word object
     */
    WordImpl getWordObject();

    /**
     * returns the player who is currently at turn
     * @return String with the username of the player who is at turn
     */
    String getCurrentTurn();

    /**
     * returns the number of mistakes made
     * @return int with the number of mistakes made
     */
    int getMistakesMade();
}

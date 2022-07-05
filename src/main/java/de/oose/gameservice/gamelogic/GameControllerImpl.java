package de.oose.gameservice.gamelogic;

import de.oose.gameservice.gamelogic.interfaces.GameController;
import de.oose.gameservice.gamelogic.utils.RandomStringImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class GameControllerImpl implements GameController {
    ArrayList<GameImpl> allGames;

    public GameControllerImpl(){
        allGames = new ArrayList<GameImpl>();
    }
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
     * creates a new game and adds the creator to the player list
     * @param username of the player
     * @return 0000-ZZZZ as identifier for the game
     */
    @Override
    public String createGame(String username) throws Exception {
           if (username.isBlank()) throw new Exception("Username is empty");
           GameImpl game = new GameImpl(username);
           allGames.add(game);
           return game.getGameID();
    }

    /**
     * starts a new game.
     * @param gameIdentifier
     * @return true if game start was successfully
     */
    @Override
    public boolean startGame(String gameIdentifier) {
        try {
            int index = getIndexByID(gameIdentifier);
            GameImpl game = allGames.get(index);
            game.startingGame();
            allGames.set(index, game);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * returns if a player is god
     *
     * @param gameIdentifier
     * @param username
     * @return true if god...
     */
    @Override
    public boolean isGod(String gameIdentifier, String username) {
        return false;
    }

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
        return null;
    }

    /**
     * Get the players of the game in which the player is
     * @param gameIdentifier
     * @return ArrayList<Username>
     */
    @Override
    public ArrayList<String> getPlayers(String gameIdentifier) {
        return null;
    }

    /**
     * Get the word but with all unknown characters replaced by NULL and split by spaces so the client of the user has the minimum of the information
     *
     * @param gameIdentifier
     * @return String (the word ex.: W NULL R D) (WORD) or null if no word exists yet (god has not yet set one)
     */
    @Override
    public ArrayList<Character> getWord(String gameIdentifier) {
        return null;
    }

    /**
     * Get all characters that have been tried in an ArrayList<Character>
     *
     * @param gameIdentifier
     * @return ArrayList<Character>
     */
    @Override
    public ArrayList<Character> getCharactersThatAlreadyHaveBeenTried(String gameIdentifier) {
        return null;
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
     * get if god entered a valid word
     *
     * @param gameIdentifier
     * @return
     */
    @Override
    public boolean getWorded(String gameIdentifier) {
        return false;
    }

    /**
     * Return the Username of the User whose turn it is to guess a character
     *
     * @return String (Username ex.: Test)
     */
    @Override
    public String whoseTurnIsIt() {
        return null;
    }

    private int getIndexByID(String gameIdentifier) {
        int index = 0;
        for (GameImpl game: allGames) {
            if(game.getGameID().equals(gameIdentifier)){
                return index;
            }
            index++;
        }
        throw new IllegalArgumentException("There is no game with this ID");
    }
}
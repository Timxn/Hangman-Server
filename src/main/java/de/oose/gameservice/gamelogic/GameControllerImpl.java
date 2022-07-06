package de.oose.gameservice.gamelogic;

import de.oose.gameservice.gamelogic.interfaces.GameController;
import de.oose.gameservice.gamelogic.utils.IllegalString;

import java.util.ArrayList;

public class GameControllerImpl implements GameController {
    ArrayList<GameImpl> allGames;

    public GameControllerImpl(){
        allGames = new ArrayList<>();
    }
    /**
     * Adds a user to an existing game.
     *
     * @param gameIdentifier the identifier of the game
     * @param username
     */
    @Override
    public void joinGame(String gameIdentifier, String username) throws Exception {
        int index = getIndexByID(gameIdentifier);
        GameImpl game = allGames.get(index);
        if (game.isStarted()) throw new Exception("Game is already in progress and cant be joined!");
        game.addPlayer(username);
    }

    /**
     * removes a user from an existing game.
     *
     * @param gameIdentifier the identifier of the game
     * @param username
     */
    @Override
    public void leaveGame(String gameIdentifier, String username) throws Exception {
        int index = getIndexByID(gameIdentifier);
        GameImpl game = allGames.get(index);
        game.removePlayer(username);
    }
    /**
     * creates a new game and adds the creator to the player list
     * @param username of the player
     * @return 0000-ZZZZ as identifier for the game
     */
    @Override
    public String createGame(String username) throws Exception {
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
    public boolean startGame(String gameIdentifier) throws Exception {
        try {
            int index = getIndexByID(gameIdentifier);
            GameImpl game = allGames.get(index);
            if (game.isStarted()) throw new Exception("Game cant be started again!");
            game.startingGame();
            allGames.set(index, game);
            return true;
        } catch (Exception e) {
            if (e.getMessage().equals("Game cant be started again!")) throw e;
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
    public boolean isGod(String gameIdentifier, String username) throws Exception {
        return allGames.get(getIndexByID(gameIdentifier)).getPlayerByUsername(username).isGod();
    }

    /**
     * gives god the ability to set the word for the next round.
     *
     * @param gameIdentifier
     * @param word           the new word
     */
    @Override
    public void setWord(String gameIdentifier, String word, String username) throws Exception {
        if (!isGod(gameIdentifier, username)) throw new Exception("This player is not allowed to do that!");
        if (!IllegalString.isAlpha(word)) throw new Exception("Illegal word");
        if (word.length() < 2) throw new Exception("Word to short");
        int index = getIndexByID(gameIdentifier);
        GameImpl game = allGames.get(index);
        game.setWord(word);
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
     * Get the players of the game in which the player is
     * @param gameIdentifier
     * @return ArrayList<Username>
     */
    @Override
    public ArrayList<String> getPlayers(String gameIdentifier) throws Exception {
        ArrayList<PlayerImpl> players = new ArrayList<>(allGames.get(getIndexByID(gameIdentifier)).getPlayers());
        ArrayList<String> usernames = new ArrayList<>();
        for (PlayerImpl player: players) {
            usernames.add(player.getUsername());
        }
        return usernames;
    }

    /**
     * Get the word but with all unknown characters replaced by _ and split by spaces so the client of the user has the minimum of the information
     *
     * @param gameIdentifier
     * @return String (the word ex.: W _ R D) (WORD) or null if no word exists yet (god has not yet set one)
     */
    @Override
    public String getWord(String gameIdentifier) throws Exception {
        return allGames.get(getIndexByID(gameIdentifier)).getWordObject().getWord();
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
    public boolean getStarted(String gameIdentifier) throws Exception {
        return allGames.get(getIndexByID(gameIdentifier)).isStarted();
    }

    /**
     * get if god entered a valid word
     *
     * @param gameIdentifier
     * @return
     */
    @Override
    public boolean getWorded(String gameIdentifier) throws Exception {
        return allGames.get(getIndexByID(gameIdentifier)).getWordObject().getWord() == null;
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

    private int getIndexByID(String gameIdentifier) throws Exception {
        int index = 0;
        for (GameImpl game: allGames) {
            if(game.getGameID().equals(gameIdentifier)){
                return index;
            }
            index++;
        }
        throw new Exception("There is no game with this ID");
    }
}
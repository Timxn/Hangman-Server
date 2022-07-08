package de.oose.gameservice.gamelogic;

import de.oose.gameservice.gamelogic.utils.IllegalString;
import de.oose.gameservice.gamelogic.utils.RandomString;

import java.util.ArrayList;

public class GameImpl {
    private String gameID;
    private boolean isStarted = false;
    private int mistakesMade = 0;
    private final ArrayList<PlayerImpl> players;
    private final ArrayList<Character> alreadyGuessedLetters;
    private final WordImpl word;
    private final TurnHandlerImpl turnHandler;
    public GameImpl(String firstPlayer){
        players = new ArrayList<>();
        alreadyGuessedLetters = new ArrayList<>();
        word = new WordImpl();
        turnHandler = new TurnHandlerImpl();
        players.add(new PlayerImpl(firstPlayer));
        this.gameID = createGameID();
    }

    public void startingGame() throws Exception {
        if (players.size()<2) throw new Exception("To less players");
        if (isStarted) throw new Exception("Game cant be started again!");
        word.resetWord();
        isStarted = true;
        mistakesMade = 0;
        alreadyGuessedLetters.clear();
        turnHandler.setOrder(players.size() - 1, godMaker());
    }

    public void addPlayer(String username) throws Exception {
        if (isStarted) throw new Exception("Game is already in progress and cant be joined!");
        for (PlayerImpl player : players) {
            if (player.getUsername().equals(username)) throw new Exception("User already in game");
        }
        players.add(new PlayerImpl(username));
    }

    public void removePlayer(String username) throws Exception {
        if (!players.contains(new PlayerImpl(username))) throw new Exception("User not in game");
        players.remove(new PlayerImpl(username));
    }
    public PlayerImpl getPlayerByUsername(String username) throws Exception {
        for (PlayerImpl player: players) {
            if(player.getUsername().equals(username)){
                return player;
            }
        }
        throw new Exception("There is no player with this username");
    }

    public void setWord(String word, String username) throws Exception {
        if (!getPlayerByUsername(username).isGod()) throw new Exception("This player is not allowed to do that!");
        if (IllegalString.isNotAlpha(word)) throw new Exception("Illegal word");
        if (word.length() < 2) throw new Exception("Word to short");
        this.word.setWord(word);
    }

    public void guessLetter(char letter, String username) throws Exception {
        if (!getCurrentTurn().equals(username)) throw new Exception("Not this players turn");
        if (IllegalString.isNotAlpha(String.valueOf(letter))) throw new Exception("Illegal char");
        alreadyGuessedLetters.add(letter);
        if (!word.guessLetter(letter)) {
            mistakesMade++;
            turnHandler.nextTurn();
            if (mistakesMade == 9) //if mistake count is 9 the game stops
                isStarted = false;
        }
    }

    public boolean isWordGuessed() {
        if (word.isWordGuessed())
            isStarted = false;
        return word.isWordGuessed();
    }

    public String getGameID() {
        return gameID;
    }

    public ArrayList<String> getPlayers() {
        ArrayList<String> usernames = new ArrayList<>();
        for (PlayerImpl player: players) {
            usernames.add(player.getUsername());
        }
        return usernames;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public WordImpl getWordObject() {
        return word;
    }

    public ArrayList<Character> getAlreadyGuessedLetters() {
        return alreadyGuessedLetters;
    }

    public String getCurrentTurn() {
        return players.get(turnHandler.getCurrentTurn()).getUsername();
    }

    public int getMistakesMade() {
        return mistakesMade;
    }

    private String createGameID(){
        gameID = RandomString.getRandomString(4);
        return gameID;
    }

    private int godMaker() {
        int index = 0;
        for (PlayerImpl player: players) {
            if (player.isGod()) {
                player.setGod(false);
                players.set(index, player); //could be removed?
            }
        }
        index = (int)(players.size() * Math.random());
        PlayerImpl player = players.get(index);
        player.setGod(true);
        players.set(index, player); //could be removed?
        return index;
    }
}
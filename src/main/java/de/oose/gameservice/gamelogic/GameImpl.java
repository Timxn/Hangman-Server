package de.oose.gameservice.gamelogic;

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
        isStarted = true;
        mistakesMade = 0;
        alreadyGuessedLetters.clear();
        int index = (int)(players.size() * Math.random());
        PlayerImpl player = players.get(index);
        player.setGod(true);
        players.set(index, player);
        turnHandler.setOrder(players.size() - 1, index);
    }

    public void addPlayer(String username) throws Exception {
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

    public void setWord(String word) throws Exception {
        this.word.setWord(word);
    }

    public void guessLetter(char letter) {
        alreadyGuessedLetters.add(letter);
        if (!word.guessLetter(letter)) {
            mistakesMade++;
            turnHandler.nextTurn();
        }
    }

    public boolean isWordGuessed() {
        return word.isWordGuessed();
    }

    public String getGameID() {
        return gameID;
    }

    public ArrayList<PlayerImpl> getPlayers() {
        return players;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
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

    /*private int indexOfGod() throws Exception {
        int index = 0;
        for (PlayerImpl player: players) {
            if (player.isGod())
                return index;
            index++;
        }
        throw new Exception("There is no God");
    }*/
}
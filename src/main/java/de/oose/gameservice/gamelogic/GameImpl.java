package de.oose.gameservice.gamelogic;

import de.oose.gameservice.gamelogic.utils.RandomString;

import java.util.ArrayList;

public class GameImpl {
    private String gameID;
    private boolean isStarted = false;
    private final ArrayList<PlayerImpl> players;
    private WordImpl word;
    private TurnHandlerImpl turnHandler;

    public GameImpl(String firstPlayer){
        players = new ArrayList<>();
        this.gameID = createGameID();
        players.add(new PlayerImpl(firstPlayer));
        word = new WordImpl();
        turnHandler = new TurnHandlerImpl();
    }

    public void startingGame() throws Exception {
        if (players.size()<2) throw new Exception("To less players");
        isStarted = true;
        int index = (int)(players.size() * Math.random());
        PlayerImpl player = players.get(index);
        player.setGod(true);
        players.set(index, player);
        setStarted(true);
        turnHandler.setOrder(players.size() - 1, indexOfGod());
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

    public void setWord(String word) throws Exception {
        this.word.setWord(word);
    }

    public String getCurrentTurn() {
        return players.get(turnHandler.getCurrentTurn()).getUsername();
    }

    private String createGameID(){
        gameID = RandomString.getRandomString(4);
        return gameID;
    }

    private int indexOfGod() throws Exception {
        int index = 0;
        for (PlayerImpl player: players) {
            if (player.isGod())
                return index;
            index++;
        }
        throw new Exception("There is no God");
    }
}
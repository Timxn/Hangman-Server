package de.oose.gameservice.gamelogic;

import de.oose.gameservice.gamelogic.utils.RandomStringImpl;

import java.util.ArrayList;

public class GameImpl {
    private String gameID;
    private ArrayList<PlayerImpl> players;
    private boolean isStarted = false;

    public GameImpl(String firstPlayer) {
        this.gameID = createGameID();
        players.add(new PlayerImpl(firstPlayer));
    }

    public void startingGame() throws Exception {
        if (players.size()<2) throw new Exception("To less players");
        isStarted = true;
        int index = (int)(players.size() * Math.random());
        PlayerImpl player = players.get(index);
        player.setGod(true);
        players.set(index, player);
    }

    public void addPlayer(String username) throws Exception {
        if (username.isBlank()) throw new Exception("Username is empty");
        if (players.contains(new PlayerImpl(username))) throw new Exception("User already in game");
        players.add(new PlayerImpl(username));
    }

    public void removePlayer(String username) throws Exception {
        if (username.isBlank()) throw new Exception("Username is empty");
        if (!players.contains(new PlayerImpl(username))) throw new Exception("User not in game");
        players.remove(new PlayerImpl(username));
    }

    public String getGameID() {
        return gameID;
    }

    private String createGameID(){
        gameID = RandomStringImpl.getRandomString(4);
        return gameID;
    }
}
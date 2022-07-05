package de.oose.gameservice.gamelogic;

import de.oose.gameservice.gamelogic.utils.RandomStringImpl;

import java.util.ArrayList;

public class GameImpl {
    private String gameID;
    private ArrayList<PlayerImpl> players;
    private boolean isStarted = false;

    public GameImpl(String firstPlayer) {
        this.gameID = createGameID();
        players.add(new PlayerImpl(firstPlayer, 0));
    }

    public String getGameID() {
        return gameID;
    }

    public void startingGame() throws Exception {
        if (players.size()<2) throw new Exception("To less players");
        isStarted = true;
        int index = (int)(players.size() * Math.random());
        PlayerImpl player = players.get(index);
        player.setGod(true);
        players.set(index, player);
    }

    private String createGameID(){
        gameID = RandomStringImpl.getRandomString(4);
        return gameID;
    }
}
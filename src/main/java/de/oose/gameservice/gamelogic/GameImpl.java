package de.oose.gameservice.gamelogic;

import de.oose.gameservice.gamelogic.utils.RandomStringImpl;

import java.util.ArrayList;

public class GameImpl {
    String gameID;
    ArrayList<PlayerImpl> players;

    public GameImpl(String firstPlayer) {
        this.gameID = createGameID();
        players.add(new PlayerImpl(firstPlayer, 0));
    }

    public String getGameID() {
        return gameID;
    }

    private String createGameID(){
        gameID = RandomStringImpl.getRandomString(4);
        return gameID;
    }
}

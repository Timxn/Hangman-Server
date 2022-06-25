package de.oose.gameservice.gamelogic;

import java.io.Serializable;
import java.util.HashMap;

public class Leaderboard implements Serializable {
    private HashMap<String, Integer> score;
    public Leaderboard() {
        score = new HashMap<>();
    }

    public int getPoints(String username) {
        return score.get(username);
    }

    public void addPoints(String username) {
        score.put(username, score.getOrDefault(username,0));
    }
}

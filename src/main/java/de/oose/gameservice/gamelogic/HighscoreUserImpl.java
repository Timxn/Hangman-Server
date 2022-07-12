package de.oose.gameservice.gamelogic;

import java.io.Serializable;

public class HighscoreUserImpl implements Serializable {
    private final String username;
    private Integer points;

    public HighscoreUserImpl(String username) {
        this.username = username;
        this.points = 1;
    }

    public String getUsername() {
        return username;
    }


    public Integer getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}

package de.oose.gameservice.gamelogic;

public class HighscoreUserImpl {
    private String username;
    private Integer points;

    public HighscoreUserImpl(String username) {
        this.username = username;
        this.points = 1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}

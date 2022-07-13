package de.oose.gameservice.gamelogic;

public class HighscoreUserImpl implements de.oose.gameservice.gamelogic.interfaces.HighscoreUser {
    private final String username;
    private Integer points;

    public HighscoreUserImpl(String username) {
        this.username = username;
        this.points = 1;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public Integer getPoints() {
        return points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

}

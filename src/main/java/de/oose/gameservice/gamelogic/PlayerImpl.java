package de.oose.gameservice.gamelogic;

public class PlayerImpl {
    private final String username;
    private int highscore;
    private boolean isGod;

    public PlayerImpl(String username, int highscore) {
        this.username = username;
        this.highscore = highscore;
    }

    public String getUsername() {
        return username;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public boolean isGod() {
        return isGod;
    }

    public void setGod(boolean god) {
        this.isGod = god;
    }
}

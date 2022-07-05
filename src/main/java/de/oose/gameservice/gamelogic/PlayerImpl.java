package de.oose.gameservice.gamelogic;

public class PlayerImpl {
    private final String username;
    private boolean isGod;

    public PlayerImpl(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public boolean isGod() {
        return isGod;
    }

    public void setGod(boolean god) {
        this.isGod = god;
    }
}

package de.oose.gameservice.gamelogic;

public class PlayerImpl implements de.oose.gameservice.gamelogic.interfaces.Player {
    private final String username;
    private boolean isGod;

    public PlayerImpl(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isGod() {
        return isGod;
    }

    @Override
    public void setGod(boolean god) {
        this.isGod = god;
    }
}

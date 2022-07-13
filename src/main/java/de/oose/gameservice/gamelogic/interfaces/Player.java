package de.oose.gameservice.gamelogic.interfaces;

public interface Player {

    /**
     * returns the username of the player
     * @return the username of the player
     */
    String getUsername();

    /**
     * returns if the player is god
     * @return true if the player is god, false otherwise
     */
    boolean isGod();

    /**
     * sets the player to god
     * @param god true if the player should be god, false otherwise
     */
    void setGod(boolean god);
}

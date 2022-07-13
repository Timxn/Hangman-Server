package de.oose.gameservice.gamelogic.interfaces;

import java.io.Serializable;

public interface HighscoreUser extends Serializable {

    /**
     * returns the username of the user
     * @return the username of the user
     */
    String getUsername();

    /**
     * returns the points of the user
     * @return the points of the user
     */
    Integer getPoints();

    /**
     * sets the points of the user
     * @param points the points of the user
     */
    void setPoints(int points);
}

package de.oose.gameservice.gamelogic.interfaces;

import java.util.ArrayList;

public interface HighscoreSystem {

    /**
     * increments the points of the user by one with the given username
     * @param username the username of the user
     */
    void incrementUser(String username);

    /**
     * returns the list of all users with their points
     * @return the list of all users with their points
     */
    ArrayList<String> getSort();

    /**
     * writes the data to a tmp file
     */
    void writeData();

    /**
     * reads the data from a tmp file
     */
    void loadData();
}

package de.oose.gameservice.gamelogic.interfaces;

public interface TurnHandler {

    /**
     * sets the order in which indexes are used to guess the word
     * @param count the number of indexes to use
     * @param skipper the index to skip (if any, otherwise count + 1)
     */
    void setOrder(int count, int skipper);

    /**
     * returns the index of the player who should play next
     * @return the index of the player who should play next
     */
    int getCurrentTurn();

    /**
     * increments the index by one or if reached end jumps back to start
     */
    void nextTurn();
}

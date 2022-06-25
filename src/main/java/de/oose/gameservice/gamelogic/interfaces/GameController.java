package de.oose.gameservice.gamelogic.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface GameController {
    /**
     * Get the global scoreboard, provided with the integer mapped to the Username in a Hashmap
     * @return Hashmap<Username, Points>
     */
    public HashMap<String, Integer> getScoreboard();

    /**
     * Get the word but with all unknown characters replaced by NULL and split by spaces so the client of the user has the minimum of the informations
     * @return String (the word ex.: W NULL R D) (WORD)
     */
    public String getWordWithUnknownCharactersBeingNULL();

    /**
     * Get all characters that have been tried in an ArrayList<Character>
     * @return ArrayList<Character>
     */
    public ArrayList<Character> getCharactersThatAlreadyHaveBeenTried();

    /**
     * How many tries have the Guesser left
     * @return int
     */
    public int howManyTriesAreLeft();

    /**
     * Return the Username of the User whos turn it is to guess a character
     * @return String (Username ex.: Test)
     */
    public String whoseTurnIsIt();
}

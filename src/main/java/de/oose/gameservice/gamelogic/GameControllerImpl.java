package de.oose.gameservice.gamelogic;

import de.oose.gameservice.gamelogic.interfaces.GameController;

import java.util.ArrayList;
import java.util.HashMap;

public class GameControllerImpl implements GameController {
    /**
     * Get the global scoreboard, provided with the integer mapped to the Username in a Hashmap
     *
     * @return Hashmap<Username, Points>
     */
    @Override
    public HashMap<String, Integer> getScoreboard() {
        return null;
    }

    /**
     * Get the word but with all unknown characters replaced by NULL and split by spaces so the client of the user has the minimum of the informations
     *
     * @return String (the word ex.: W NULL R D) (WORD)
     */
    @Override
    public String getWordWithUnknownCharactersBeingNULL() {
        return null;
    }

    /**
     * Get all characters that have been tried in an ArrayList<Character>
     *
     * @return ArrayList<Character>
     */
    @Override
    public ArrayList<Character> getCharactersThatAlreadyHaveBeenTried() {
        return null;
    }

    /**
     * How many tries have the Guesser left
     *
     * @return int
     */
    @Override
    public int howManyTriesAreLeft() {
        return 0;
    }

    /**
     * Return the Username of the User whos turn it is to guess a character
     *
     * @return String (Username ex.: Test)
     */
    @Override
    public String whoseTurnIsIt() {
        return null;
    }
}

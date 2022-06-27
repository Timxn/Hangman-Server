package de.oose.gameservice.gamelogic.interfaces;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class HouseKeepingMessage implements Serializable {
    private String whoseTurnIsIt = null;
    private int howManyTriesAreLeft = 0;
    private String word = null;
    private ArrayList<Character> alreadyTriedList = null;
    private HashMap<String, Integer> scoreboard = null;

    public HouseKeepingMessage() {
    }

    public String getWhoseTurnIsIt() {
        return whoseTurnIsIt;
    }

    public void setWhoseTurnIsIt(String whoseTurnIsIt) {
        this.whoseTurnIsIt = whoseTurnIsIt;
    }

    public int getHowManyTriesAreLeft() {
        return howManyTriesAreLeft;
    }

    public void setHowManyTriesAreLeft(int howManyTriesAreLeft) {
        this.howManyTriesAreLeft = howManyTriesAreLeft;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<Character> getAlreadyTriedList() {
        return alreadyTriedList;
    }

    public void setAlreadyTriedList(ArrayList<Character> alreadyTriedList) {
        this.alreadyTriedList = alreadyTriedList;
    }

    public HashMap<String, Integer> getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(HashMap<String, Integer> scoreboard) {
        this.scoreboard = scoreboard;
    }
}

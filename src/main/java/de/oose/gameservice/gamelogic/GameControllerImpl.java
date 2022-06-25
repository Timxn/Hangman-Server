package de.oose.gameservice.gamelogic;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GameControllerImpl {
    private Leaderboard leaderboard;    // make static (golbal)
    private ArrayList<String> users = new ArrayList<>();
    private ArrayList<ObjectOutputStream> allUserOutPutStreams = new ArrayList<>();
    private String wordGiver;
    private ArrayList<String> unknownWord;
    private ArrayList<String> knownWord;
    private ArrayList<String> tried;
    private final int MAX_TRIES = 9;
    private int triesLeft = MAX_TRIES;
    private boolean active = false;
    public GameControllerImpl() {
        this.leaderboard = new Leaderboard();
    }
    public void makeGuess(String guess) {
        guess.toLowerCase();
        guess.substring(0,1);
        testCharacterThenSetIt(guess);
        whoWon();
    }

    public ArrayList<String> getUnknownWord() {
        return unknownWord;
    }
    public void setWord(String word, String username) {
        word.toLowerCase();
        this.unknownWord = new ArrayList<>();
        for (Character character:word.toCharArray()) {
            this.unknownWord.add(String.valueOf(character));
        }

        this.knownWord = new ArrayList<>(this.unknownWord.size());
        for (int i = 0; i < knownWord.size(); i++) {
            knownWord.add(null);
        }

        this.wordGiver = username;
        this.active = true;

        tried.clear();
        triesLeft = MAX_TRIES;
    }

    public void addUser(String newUsername, ObjectOutputStream objectOutputStream) {
        this.users.add(newUsername);
        this.allUserOutPutStreams.add(objectOutputStream);
    }

    private void testCharacterThenSetIt(String character) {
        character.toLowerCase();
        character = character.substring(0,1);
        if (tried.contains(character)) return;
        else if (unknownWord.contains(character)) {
            knownWord.add(unknownWord.indexOf(character),character);
            unknownWord.add(unknownWord.indexOf(character),null);
            tried.add(character);
            return;
        }
        triesLeft--;
        return;
    }

    public ArrayList<ObjectOutputStream> getAllOutPutStreams() {
        return allUserOutPutStreams;
    }

    private void whoWon() {
        if (triesLeft == 0) {
            leaderboard.addPoints(wordGiver);
            this.active = false;
        } else if (triesLeft > 0 && !(knownWord.contains(null))) {
            for (String user:users) {
                if (!(user.equals(wordGiver))) leaderboard.addPoints(user);
            }
            this.active = false;
        }
    }
}

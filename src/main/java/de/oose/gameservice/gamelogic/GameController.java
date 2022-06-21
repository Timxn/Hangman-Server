package de.oose.gameservice.gamelogic;

import java.util.ArrayList;
import java.util.UUID;

public class GameController {
    private UUID uuid = UUID.randomUUID();

    private String word;
    private ArrayList<String> tried;
    private final int MAX_TRIES = 9;
    private int triesLeft = MAX_TRIES;
    public GameController(String word) {
        this.word = word;
        this.word.toLowerCase();
    }

    public int tryCharacter(String character) {
        character.toLowerCase();
        if (!tried.contains(character)) {
            for (Character chara : word.toCharArray()) {
                if (chara.equals(character)) {
                    tried.add(character);
                    return triesLeft;
                }
            }
        }
        triesLeft++;
        return triesLeft;
    }

    public ArrayList<Character> getWord() {
        ArrayList<Character> tmp = new ArrayList<>();
        for (Character chara : word.toCharArray()) {
            if (tried.contains(chara)) {
                tmp.add(chara);
            }
            tmp.add(null);
        }
        return tmp;
    }
    public void setWord(String word) {
        this.word = word;
        tried.clear();
        triesLeft = MAX_TRIES;
    }

    public UUID getUuid() {
        return uuid;
    }
}

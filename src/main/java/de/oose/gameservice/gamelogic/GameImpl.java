package de.oose.gameservice.gamelogic;

import de.oose.gameservice.gamelogic.utils.IllegalString;
import de.oose.gameservice.gamelogic.utils.RandomString;

import java.util.ArrayList;

public class GameImpl implements de.oose.gameservice.gamelogic.interfaces.Game {
    private String gameID;
    private boolean isStarted = false;
    private int mistakesMade = 0;
    private String winner = null;
    private final ArrayList<PlayerImpl> players;
    private final ArrayList<Character> guessedWrongLetters;
    private final WordImpl word;
    private final TurnHandlerImpl turnHandler;
    
    public GameImpl(String firstPlayer){
        players = new ArrayList<>();
        guessedWrongLetters = new ArrayList<>();
        word = new WordImpl();
        turnHandler = new TurnHandlerImpl();
        players.add(new PlayerImpl(firstPlayer));
        this.gameID = createGameID();
    }

    @Override
    public void startingGame() throws Exception {
        if (players.size()<2) throw new Exception("Not enough players!");
        if (isStarted) throw new Exception("Game can not be started again!");
        word.resetWord();
        isStarted = true;
        mistakesMade = 0;
        guessedWrongLetters.clear();
        turnHandler.setOrder(players.size() - 1, godMaker());
    }

    @Override
    public void addPlayer(String username) throws Exception {
        if (isStarted) throw new Exception("Game is already in progress and can't be joined!");
        if (players.size()>=8) throw new Exception("Game is full!");
        for (PlayerImpl player : players) {
            if (player.getUsername().equals(username)) throw new Exception("User is already in game!");
        }
        players.add(new PlayerImpl(username));
    }

    @Override
    public void removePlayer(String username) throws Exception {
        players.remove(getIndexOfPlayer(username));
        isStarted = false;
        if (username.equals(winner))
            winner = null;
    }

    @Override
    public PlayerImpl getPlayerByUsername(String username) throws Exception {
        for (PlayerImpl player: players) {
            if(player.getUsername().equals(username)){
                return player;
            }
        }
        throw new Exception("There is no player with this username!");
    }

    @Override
    public void setWord(String word, String username) throws Exception {
        if (!getPlayerByUsername(username).isGod()) throw new Exception("This player is not allowed to do that!");
        if (IllegalString.isNotAlpha(word)) throw new Exception("Illegal word!");
        if (word.length() < 2) throw new Exception("Word too short!");
        if (word.length() > 20) throw new Exception("Word too long!");
        this.word.setWord(word);
    }

    @Override
    public void guessLetter(char letter, String username) throws Exception {
        if (!isStarted()) throw new Exception("Game is not started!");
        if (!getCurrentTurn().equals(username)) throw new Exception("Not your turn!");
        if (IllegalString.isNotAlpha(String.valueOf(letter))) throw new Exception("Illegal char! (Nearly as illegal as you!)");
        if (!word.guessLetter(letter)) {
            guessedWrongLetters.add(letter);
            mistakesMade++;
            turnHandler.nextTurn();
            if (mistakesMade == 9) { //if mistake count is 9 the game stops
                winner = players.get(getIndexOfGod()).getUsername();
                isStarted = false;
            }
        } else if (word.isWordGuessed()){
            winner = username;
            isStarted = false;
        }
    }

    @Override
    public boolean isWordGuessed() {
        return word.isWordGuessed();
    }

    @Override
    public ArrayList<String> getPlayers() {
        ArrayList<String> usernames = new ArrayList<>();
        for (PlayerImpl player: players) {
            usernames.add(player.getUsername());
        }
        return usernames;
    }

    @Override
    public String getGuessedWrongLetters() {
        String returny = guessedWrongLetters.toString();
        return returny.substring(1, returny.length() - 1);
    }

    @Override
    public String getWinner() {
        return winner;
    }

    @Override
    public String getGameID() {
        return gameID;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

    @Override
    public WordImpl getWordObject() {
        return word;
    }

    @Override
    public String getCurrentTurn() {
        return players.get(turnHandler.getCurrentTurn()).getUsername();
    }

    @Override
    public int getMistakesMade() {
        return mistakesMade;
    }

    private String createGameID(){
        gameID = RandomString.getRandomString(4);
        return gameID;
    }

    private int godMaker() throws Exception {
        int index = 0;
        for (PlayerImpl player: players) {
            player.setGod(false);
            players.set(index, player); //could be removed?
            index++;
        }
        if (winner != null) {
            index = getIndexOfPlayer(winner);
            PlayerImpl player = players.get(index);
            player.setGod(true);
            players.set(index, player); //could be removed?
        } else {
            index = (int) (players.size() * Math.random());
            PlayerImpl player = players.get(index);
            player.setGod(true);
            players.set(index, player); //could be removed?
        }
        winner = null;
        return index;
    }

    private int getIndexOfPlayer(String username) throws Exception {
        int index = 0;
        for (PlayerImpl player: players) {
            if (player.getUsername().equals(username)) {
                return index;
            }
            index++;
        }
        throw new Exception("There is no player with this username!");
    }

    private int getIndexOfGod() throws Exception {
        int index = 0;
        for (PlayerImpl player: players) {
            if (player.isGod()) {
                return index;
            }
            index++;
        }
        throw new Exception("There is no god(thanks Stephen)");
    }
}

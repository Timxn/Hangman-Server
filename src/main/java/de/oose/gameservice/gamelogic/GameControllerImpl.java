package de.oose.gameservice.gamelogic;

import de.oose.gameservice.gamelogic.interfaces.GameController;

import java.util.ArrayList;

public class GameControllerImpl implements GameController {
    ArrayList<GameImpl> allGames;

    public GameControllerImpl(){
        allGames = new ArrayList<>();
    }

    @Override
    public void joinGame(String gameIdentifier, String username) throws Exception {
        if (gameIdentifier.isBlank()) throw new Exception("Please enter a game ID");
        int index = getIndexByID(gameIdentifier);
        GameImpl game = allGames.get(index);
        game.addPlayer(username);
    }

    @Override
    public void leaveGame(String gameIdentifier, String username) throws Exception {
        int index = getIndexByID(gameIdentifier);
        GameImpl game = allGames.get(index);
        game.removePlayer(username);
    }

    @Override
    public String createGame(String username) {
        GameImpl game = new GameImpl(username);
        allGames.add(game);
        return game.getGameID();
    }

    @Override
    public boolean startGame(String gameIdentifier) throws Exception {
        //TODO make this cleaner
        try {
            int index = getIndexByID(gameIdentifier);
            GameImpl game = allGames.get(index);
            game.startingGame();
            return true;
        } catch (Exception e) {
            if (e.getMessage().equals("Game cant be started again!")) throw e;
            return false;
        }
    }

    @Override
    public void setWord(String gameIdentifier, String word, String username) throws Exception {
        int index = getIndexByID(gameIdentifier);
        GameImpl game = allGames.get(index);
        game.setWord(word, username);
    }

    @Override
    public void guessLetter(String gameIdentifier, char letter, String username) throws Exception {
        int index = getIndexByID(gameIdentifier);
        GameImpl game = allGames.get(index);
        game.guessLetter(letter, username);
    }

    @Override
    public boolean isGod(String gameIdentifier, String username) throws Exception {
        return allGames.get(getIndexByID(gameIdentifier)).getPlayerByUsername(username).isGod();
    }

    @Override
    public ArrayList<String> getPlayers(String gameIdentifier) throws Exception {
        return allGames.get(getIndexByID(gameIdentifier)).getPlayers();
    }

    @Override
    public String getWord(String gameIdentifier) throws Exception {
        return allGames.get(getIndexByID(gameIdentifier)).getWordObject().getWord();
    }

    @Override
    public String getCharactersThatAlreadyHaveBeenTried(String gameIdentifier) throws Exception {
        return allGames.get(getIndexByID(gameIdentifier)).getGuessedWrongLetters();
    }

    @Override
    public int getMistakesMade(String gameIdentifier) throws Exception {
        return allGames.get(getIndexByID(gameIdentifier)).getMistakesMade();
    }

    @Override
    public boolean getWordGuessed(String gameIdentifier) throws Exception {
        return allGames.get(getIndexByID(gameIdentifier)).isWordGuessed();
    }

    @Override
    public boolean getStarted(String gameIdentifier) throws Exception {
        return allGames.get(getIndexByID(gameIdentifier)).isStarted();
    }

    @Override
    public boolean getWorded(String gameIdentifier) throws Exception {
        return !allGames.get(getIndexByID(gameIdentifier)).getWordObject().getWord().equals("");
    }

    @Override
    public String whoseTurnIsIt(String gameIdentifier) throws Exception {
        return allGames.get(getIndexByID(gameIdentifier)).getCurrentTurn();
    }

    private int getIndexByID(String gameIdentifier) throws Exception {
        int index = 0;
        for (GameImpl game: allGames) {
            if(game.getGameID().equals(gameIdentifier)){
                return index;
            }
            index++;
        }
        throw new Exception("There is no game with this ID");
    }
}
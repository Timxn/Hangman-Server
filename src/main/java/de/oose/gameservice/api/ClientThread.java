package de.oose.gameservice.api;

import de.oose.gameservice.Main;
import de.oose.gameservice.gamelogic.utils.IllegalString;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;


public class ClientThread implements Runnable {
    private String username;
    private String gameIdentifier;
    Logger log = Logger.getLogger(String.valueOf(ClientThread.class));
    DataInputStream objectInputStream;
    DataOutputStream objectOutputStream;

    private final Socket socket;
    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            objectInputStream = new DataInputStream(socket.getInputStream());
            objectOutputStream = new DataOutputStream(socket.getOutputStream());

            label:
            while (true) {
                JSONObject message = new JSONObject(objectInputStream.readUTF());
//                log.info("DEBUG");
                switch (message.getString("command")) {
                    // login page
                    case "createRoom":
                    {
                        this.username = message.getString("username").toUpperCase();
                        JSONObject response = new JSONObject();
                        if (username.isBlank()) {
                            response.put("status", "Username is empty");
                            log.severe("Username is empty");
                            objectOutputStream.writeUTF(response.toString());
                            break;
                        }
                        if (!IllegalString.isAlpha(username)) {
                            response.put("status", "Invalid username!");
                            log.severe("Invalid username!");
                            objectOutputStream.writeUTF(response.toString());
                            break;
                        }
                        try {
                            this.gameIdentifier = Main.gameController.createGame(username);
                        } catch (Exception e) {
                            log.severe(e.getMessage());
                            response.put("status", e.getMessage());
                            objectOutputStream.writeUTF(response.toString());
                            break;
                        }
                        response.put("status", "successful")
                                .put("gameID", gameIdentifier);
                        log.info("Created Room " + gameIdentifier);
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }
                    case "joinRoom":
                    {
                        this.username = message.getString("username").toUpperCase();
                        this.gameIdentifier = message.getString("gameID").toUpperCase();
                        JSONObject response = new JSONObject();
                        if (username.isBlank()) {
                            response.put("status", "Username is empty");
                            log.severe("Username is empty");
                            objectOutputStream.writeUTF(response.toString());
                            break;
                        }
                        if (!IllegalString.isAlpha(username)) {
                            response.put("status", "Invalid username!");
                            log.severe("Invalid username!");
                            objectOutputStream.writeUTF(response.toString());
                            break;
                        }
                        if (!IllegalString.isAlphaNumeric(gameIdentifier)) {
                            response.put("status", "Illegal game identifier");
                            log.severe("Illegal game identifier");
                            objectOutputStream.writeUTF(response.toString());
                            break;
                        }
                        try {
                            Main.gameController.joinGame(gameIdentifier, username);
                        } catch (Exception e) {
                            response.put("status", e.getMessage());
                            log.severe(e.getMessage());
                            objectOutputStream.writeUTF(response.toString());
                            break;
                        }
                        response.put("status", "successful");
                        log.info("Joined Room " + gameIdentifier);
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }
                    // waiting page
                    case "startGame": {
                        JSONObject response = new JSONObject();
                        try {
                            Main.gameController.startGame(gameIdentifier);
                            log.info(gameIdentifier + " got started");
                        } catch (Exception e) {
                            response.put("status", e.getMessage());
                            log.severe(e.getMessage());
                            objectOutputStream.writeUTF(response.toString());
                            break;
                        }
                        response.put("status", "successful");
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }
                    case "updateWaiting": {
                        JSONObject response = new JSONObject();
                        boolean tmp;
                        ArrayList<String> tmp2;
                        try {
                            tmp = Main.gameController.getStarted(gameIdentifier);
                            tmp2 = Main.gameController.getPlayers(gameIdentifier);
                        } catch (Exception e) {
                            response.put("status", e.getMessage());
                            log.severe(e.getMessage());
                            objectOutputStream.writeUTF(response.toString());
                            break;
                        }
                        response.put("status", "successful")
                                .put("gameID", gameIdentifier)
                                .put("isStarted", tmp)
                                .put("userList", tmp2);
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }
                    // game page
                    case "isGod": {
                        JSONObject response = new JSONObject();
                        boolean tmp;
                        try {
                            tmp = Main.gameController.isGod(gameIdentifier, username);
                            if (tmp) log.info(username + " is god");
                            log.info(username + " is not god");
                        } catch (Exception e) {
                            response.put("status", e.getMessage());
                            log.severe(e.getMessage());
                            objectOutputStream.writeUTF(response.toString());
                            break;
                        }
                        response.put("status", "successful")
                                .put("isGod", tmp);
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }

                    case "setWord": {
                        JSONObject response = new JSONObject();
                        try {
                            Main.gameController.setWord(gameIdentifier, message.getString("word").toUpperCase(), username);
                            log.info(message.getString("word") + " is new word");
                        } catch (Exception e) {
                            response.put("status", e.getMessage());
                            log.severe(e.getMessage());
                            objectOutputStream.writeUTF(response.toString());
                            break;
                        }
                        response.put("status", "successful");
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }

                    case "isStarted": {
                        JSONObject response = new JSONObject();
                        boolean isStarted;
                        try {
                            isStarted = Main.gameController.getStarted(gameIdentifier);
                            if (isStarted) log.info(gameIdentifier + " is started");
                            log.info(gameIdentifier + " is not started");
                        } catch (Exception e) {
                            response.put("status", e.getMessage());
                            log.severe(e.getMessage());
                            objectOutputStream.writeUTF(response.toString());
                            break;
                        }
                        response.put("status", "successful")
                                .put("isStarted", isStarted);
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }

                    case "hasWord": {
                        JSONObject response = new JSONObject();
                        boolean hasWord;
                        try {
                            hasWord = Main.gameController.getWorded(gameIdentifier);
                            if (hasWord) log.info(gameIdentifier + " has Word");
                            log.info(gameIdentifier + " does not have a word");
                        } catch (Exception e) {
                            response.put("status", e.getMessage());
                            log.severe(e.getMessage());
                            objectOutputStream.writeUTF(response.toString());
                            break;
                        }
                        response.put("status", "successful")
                                .put("hasWord", hasWord);
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }

                    case "guess":
                        Main.gameController.guessLetter(gameIdentifier, message.getString("character").toUpperCase().charAt(0));
                        objectOutputStream.writeUTF(new JSONObject("status", "successful").toString());
                        break;
                    case "updateGame": {
                        JSONObject response = new JSONObject();
                        response.put("whoseTurnIsIt", Main.gameController.whoseTurnIsIt())
                                .put("mistakesMade", Main.gameController.getMistakesMade(gameIdentifier))
                                .put("characterList", Main.gameController.getCharactersThatAlreadyHaveBeenTried(this.gameIdentifier))
                                .put("word", Main.gameController.getWord(this.gameIdentifier));
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }
                    // win page
                    case "quitGame": {
                        Main.gameController.leaveGame(this.gameIdentifier, this.username);
                        JSONObject response = new JSONObject();
                        response.put("status", "successful");
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }
                    case "restartGame": {
                        JSONObject response = new JSONObject();
                        try {
                            Main.gameController.startGame(gameIdentifier);
                            log.info(gameIdentifier + " got restarted");
                        } catch (Exception e) {
                            response.put("status", e.getMessage());
                            log.severe(e.getMessage());
                            objectOutputStream.writeUTF(response.toString());
                            break;
                        }
                        response.put("status", "successful");
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }

                    case "close": {
                        JSONObject response = new JSONObject();
                        try {
                            Main.gameController.leaveGame(this.gameIdentifier, this.username);
                            log.info(gameIdentifier + " stopped playing");
                        } catch (Exception e) {
                            response.put("status", e.getMessage());
                            log.severe(e.getMessage());
                            objectOutputStream.writeUTF(response.toString());
                            break;
                        }
                        response.put("status", "successful");
                        objectOutputStream.writeUTF(response.toString());
                        this.objectInputStream.close();
                        this.objectOutputStream.close();
                        socket.close();
                        break label;
                    }
                }
            }
        } catch (IOException ex) {
            log.severe("Server exception: " + ex.getMessage() + ", probably unsafe closure of a connection");
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
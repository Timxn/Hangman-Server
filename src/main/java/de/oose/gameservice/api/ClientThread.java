package de.oose.gameservice.api;

import de.oose.gameservice.Main;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import de.oose.gameservice.gamelogic.GameControllerImpl;
import org.json.*;


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
                    case "createRoom":  // payload is "username"
                    {
                        this.username = message.getString("username");
                        this.gameIdentifier = Main.gameController.createGame(username);
                        log.info("Created Room with ID: " + gameIdentifier);
                        JSONObject response = new JSONObject();
                        response.put("command", "response")
                                .put("status", "successful")
                                .put("gameID", gameIdentifier);
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }
                    case "joinRoom":    // payload is "gameID;username"
                    {
                        this.gameIdentifier = message.getString("gameID");
                        this.username = message.getString("username");
                        Main.gameController.joinGame(gameIdentifier, username);
                        log.info("Joined Room " + gameIdentifier);
                        JSONObject response = new JSONObject()
                                .put("command", "response")
                                .put("status", "successful")
                                .put("gameID", gameIdentifier);
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }
                    // waiting page
                    case "startGame": {
                        Main.gameController.startGame(gameIdentifier);
                        log.info("I FUCKING STARTED IT");
                        JSONObject response = new JSONObject()
                                .put("command", "response")
                                .put("status", "successful");
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }
                    case "updateWaiting": {
                        JSONObject response = new JSONObject();
                        response.put("gameID", gameIdentifier)
                                .put("isStarted", Main.gameController.getStarted(gameIdentifier))
                                .put("userList", Main.gameController.getPlayers(gameIdentifier));
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }
                    // game page
                    case "isGod": {
                        JSONObject response = new JSONObject();
                        response.put("command", "response")
                                .put("isGod", Main.gameController.isGod(gameIdentifier, username));
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }

                    case "setWord": {
                        Main.gameController.setWord(gameIdentifier, message.getString("word"));
                        JSONObject response = new JSONObject();
                        response.put("command", "response")
                                .put("status", "successful");
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }

                    case "isStarted": {
                        JSONObject response = new JSONObject();
                        response.put("command", "response")
                                .put("isStarted", Main.gameController.getStarted(gameIdentifier));
                        objectOutputStream.writeUTF(response.toString());
                        break;
                    }

                    case "guess":
                        Main.gameController.guessLetter(gameIdentifier, message.getString("character").charAt(0));
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
                    case "restartGame":
                        Main.gameController.startGame(this.gameIdentifier);
                        JSONObject response = new JSONObject();
                        response.put("status", "successful");
                        objectOutputStream.writeUTF(response.toString());
                        break;

                    case "close":
                        Main.gameController.leaveGame(this.gameIdentifier, this.username);
                        this.objectInputStream.close();
                        this.objectOutputStream.close();
                        socket.close();
                        break label;
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
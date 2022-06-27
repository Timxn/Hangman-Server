package de.oose.gameservice;

import de.oose.gameservice.api.Message;
import de.oose.gameservice.gamelogic.GameControllerImpl;
import de.oose.gameservice.gamelogic.interfaces.HouseKeepingMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;


public class ServerHandler implements Runnable {
    private GameControllerImpl game = null;
    private String username;
    Logger log = Logger.getLogger(String.valueOf(ServerHandler.class));
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    private final Socket socket;
    public ServerHandler(Socket socket) {
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
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            label:
            while (true) {
                Message message = (Message) objectInputStream.readObject();
                log.info("DEBUG");
                log.info("Message received with command " + message.getCommand() + " and Payload " + message.getPayload());
                switch (message.getCommand()) {
                    case "createRoom":  // payload is "username"
                        game = new GameControllerImpl();
                        game.addUser(message.getPayload(), objectOutputStream);
                        int id = Main.addGameInstance(game);
                        this.username = message.getPayload();
                        log.info("Created Room with ID: " + id);
                        objectOutputStream.writeObject(new Message("response", String.valueOf(id)));
                        break;
                    case "joinRoom":    // payload is "gameid username"
                        game = Main.getGameInstance(Integer.getInteger(message.getPayload().split(" ")[0]));
                        game.addUser(message.getPayload().split(" ")[1], objectOutputStream);
                        this.username = message.getPayload();
                        log.info("Joined Room " + message.getPayload());
                        objectOutputStream.writeObject(new Message("response", "successful"));
                        break;
                    case "makeGuess":
                        game.makeGuess(message.getPayload());
                        log.info("I FUCKING GUESSED IT");
                        break;
                    case "close":
                        socket.close();
                        break label;
                }
            }
        } catch (IOException ex) {
            log.severe("Server exception: " + ex.getMessage() + ", probably unsafe closure of a connection");
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            log.severe("Serialization exception from Message prob: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendUpdate () {
        try {
            HouseKeepingMessage message = new HouseKeepingMessage();
            message.setScoreboard(game.getScoreboard());
            message.setAlreadyTriedList(game.getCharactersThatAlreadyHaveBeenTried());
            message.setWord(game.getWordWithUnknownCharactersBeingNULL());
            message.setHowManyTriesAreLeft(game.howManyTriesAreLeft());
            message.setWhoseTurnIsIt(game.whoseTurnIsIt());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            log.severe(e.getMessage());
        }
    }
}
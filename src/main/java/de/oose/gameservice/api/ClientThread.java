package de.oose.gameservice.api;

import de.oose.gameservice.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;


public class ClientThread implements Runnable {
    private String username;
    private String gameIdentifier;
    Logger log = Logger.getLogger(String.valueOf(ClientThread.class));
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

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
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            label:
            while (true) {
                de.oose.gameservice.api.Message message = (de.oose.gameservice.api.Message) objectInputStream.readObject();
                log.info("DEBUG");
                log.info("Message received with command " + message.getCommand() + " and Payload " + message.getPayload());
                switch (message.getCommand()) {

                    // login page
                    case "createRoom":  // payload is "username"
                        this.gameIdentifier = Main.gameController.createGame(message.getPayload());
                        this.username = message.getPayload();
                        log.info("Created Room with ID: " + gameIdentifier);
                        objectOutputStream.writeObject(new de.oose.gameservice.api.Message("response", "successful;" + gameIdentifier));
                        break;
                    case "joinRoom":    // payload is "gameID;username"
                        this.gameIdentifier = message.getPayload().split(";")[0];
                        this.username = message.getPayload().split(";")[1];
                        Main.gameController.joinGame(this.gameIdentifier, this.username);
                        log.info("Joined Room " + this.gameIdentifier);
                        objectOutputStream.writeObject(new de.oose.gameservice.api.Message("response", "successful;" + gameIdentifier));
                        break;

                    // waiting page
                    case "startGame":
                        Main.gameController.startGame(this.gameIdentifier);
                        log.info("I FUCKING STARTED IT");
                        break;
                    case "updateWaiting":
                        objectOutputStream.writeObject(new de.oose.gameservice.api.Message("response", this.gameIdentifier +"; " + Main.gameController.getPlayers(this.gameIdentifier).toString() + "; " + Main.gameController.getStarted(this.gameIdentifier)));
                        break;

                    // game page
                    case "guess":
                        Main.gameController.guessLetter(this.gameIdentifier, message.getPayload().charAt(0));
                        objectOutputStream.writeObject(new de.oose.gameservice.api.Message("response", "successful;" + gameIdentifier));
                        break;
                    case "updateGame":
                        objectOutputStream.writeObject(new de.oose.gameservice.api.Message("response", Main.gameController.whoseTurnIsIt() + "; " + Main.gameController.howManyTriesAreLeft(this.gameIdentifier) + "; " + Main.gameController.getCharactersThatAlreadyHaveBeenTried(this.gameIdentifier) + "; " + Main.gameController.getWord(this.gameIdentifier)));
                        break;

                    // win page
                    case "quitGame":
                        Main.gameController.leaveGame(this.gameIdentifier, this.username);
                        objectOutputStream.writeObject(new de.oose.gameservice.api.Message("response", "successful;" + gameIdentifier));
                        break;
                    case "restartGame":
                        Main.gameController.startGame(this.gameIdentifier);
                        objectOutputStream.writeObject(new de.oose.gameservice.api.Message("response", "successful;" + gameIdentifier));
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
        } catch (ClassNotFoundException e) {
            log.severe("Serialization exception from Message prob: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
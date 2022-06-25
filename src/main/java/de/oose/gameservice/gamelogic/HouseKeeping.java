package de.oose.gameservice.gamelogic;

import de.oose.gameservice.api.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class HouseKeeping implements Runnable{
    GameControllerImpl game;

    public HouseKeeping(GameControllerImpl game) {
        this.game = game;
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
        while (true) {
            try {
                wait(6000);
                sendMessageToAllUsers(game.getClientInformations());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void sendMessageToAllUsers(Message message) {
        for (ObjectOutputStream stream: game.getAllOutPutStreams()) {
            try {
                stream.writeObject(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

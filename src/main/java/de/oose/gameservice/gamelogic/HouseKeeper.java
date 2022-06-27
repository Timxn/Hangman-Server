package de.oose.gameservice.gamelogic;

import de.oose.gameservice.gamelogic.interfaces.HouseKeepingMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HouseKeeper implements Runnable{
    private GameControllerImpl game;
    private ArrayList<ObjectOutputStream> objectOutputStreams = new ArrayList<>();
    public HouseKeeper(GameControllerImpl game) {
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

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void addObjectOutputStreams(ObjectOutputStream objectOutputStream) {
        objectOutputStreams.add(objectOutputStream);
    }
}

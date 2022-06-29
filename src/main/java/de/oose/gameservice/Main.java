package de.oose.gameservice;

import de.oose.gameservice.api.ServerThread;
import de.oose.gameservice.gamelogic.GameControllerImpl;

import java.io.IOException;


public class Main {
    public static GameControllerImpl gameController;
    public static void main(String[] args) throws IOException {
        gameController = new GameControllerImpl();
        new Thread(new ServerThread()).start();
    }
}

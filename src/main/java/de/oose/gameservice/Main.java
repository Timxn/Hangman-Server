package de.oose.gameservice;

import de.oose.gameservice.gamelogic.GameControllerImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.*;


public class Main {
    private static HashMap<Integer, GameControllerImpl> allGameInstances = new HashMap<>();
    public static void main(String[] args) throws IOException {
        Logger log = Logger.getLogger(String.valueOf(Main.class));
        log.info("Gameservice Server WarmUp has begun...");

        Handler filehandler = new FileHandler("src/main/resources/myapp.log");
        log.addHandler(filehandler);

        // load a config file for server configurations
        Properties configuration = new Properties();
        configuration.load(new FileInputStream(new File("src/main/resources/conf.properties")));
        log.info("Config loaded...");

        int port = Integer.parseInt((String) configuration.get("port"));
        log.info("Socket started with port " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket.toString());
            }

        } catch (IOException ex) {
            log.severe("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static GameControllerImpl getGameInstance(int id) {
        return allGameInstances.get(id);
    }
    public static int addGameInstance(GameControllerImpl gameInstance) {
        int id = (int)(Math.random()*9999);
        while (allGameInstances.containsKey(id)) id = (int)(Math.random()*9999);
        allGameInstances.put(id, gameInstance);
        return id;
    }
}

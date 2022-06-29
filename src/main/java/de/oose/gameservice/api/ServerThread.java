package de.oose.gameservice.api;

import de.oose.gameservice.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class ServerThread implements Runnable {

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
        Logger log = Logger.getLogger(String.valueOf(Main.class));
        log.info("Gameservice Server WarmUp has begun...");

        Handler filehandler = null;

        try {
            filehandler = new FileHandler("src/main/resources/myapp.log");
        } catch (IOException e) {
            log.severe(e.getMessage());
        }

        log.addHandler(filehandler);

        // load a config file for server configurations
        Properties configuration = new Properties();

        try {
            configuration.load(new FileInputStream(new File("src/main/resources/conf.properties")));
        } catch (IOException e) {
            log.severe(e.getMessage());
        }

        log.info("Config loaded...");
        int port = Integer.parseInt((String) configuration.get("port"));

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientThread(socket)).start();
                log.info("New client connected: " + socket.toString());
            }

        } catch (IOException e) {
            log.severe("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

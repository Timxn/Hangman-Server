package de.oose.gameservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Gameservice Server WarmUp has begun...");

        // load a config file for server configurations
        Properties configuration = new Properties();
        configuration.load(new FileInputStream(new File("src/main/resources/conf.properties")));
        System.out.println(configuration.get("key"));

        int port = Integer.parseInt("8001");

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket.toString());

                new Thread(new ServerHandler(socket)).start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("LOL");
    }
}

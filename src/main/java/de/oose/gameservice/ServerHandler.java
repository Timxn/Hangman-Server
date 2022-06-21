package de.oose.gameservice;

import com.google.gson.Gson;
import de.oose.gameservice.gamelogic.GameController;
import de.oose.gameservice.gamelogic.Message;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;


public class ServerHandler implements Runnable {
    private GameController instance = null;
    Logger log = Logger.getLogger(String.valueOf(ServerHandler.class));

    private Socket socket;
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
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String text;

            label:
            while (true) {
                text = reader.readLine();
                Message tmp = new Message("test", "test2 oder so");
                Gson gson = new Gson();
                String tmp2 = gson.toJson(tmp);
                Message tmp3 = gson.fromJson(tmp2, Message.class);
                Message message = gson.fromJson(text, Message.class);
                switch (message.getKey()) {
                    case "##guess":
                        log.info("i tried to guess i guess");
                        break;
                    case "##createRoom": {
                        instance = new GameController("");
                        int id = Main.addGameInstance(instance);
                        log.info("Created Room with ID: " + id);
                        writer.println(id);
                        break;
                    }
                    case "##joinRoom": {
                        int id = Integer.parseInt(message.getValue());
                        instance = Main.getGameInstance(id);
                        log.info("Joined Room " + id);
                        writer.println("joined");
                        break;
                    }
                    case "##leave":
                        socket.close();
                        break label;
                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
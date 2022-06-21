package de.oose.gameservice;

import com.google.gson.Gson;
import de.oose.gameservice.gamelogic.GameController;
import de.oose.gameservice.gamelogic.Message;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

            while (true) {
                text = reader.readLine();
                if (text.contains("##createRoom")) {
                    int id = Main.addGameInstance(instance);
                    log.info("Created Room with ID: " + id);
                    writer.println(id);
                } else if (text.contains("##joinRoom")) {
                    Pattern p = Pattern.compile("(\\D*)(\\d+)(\\D*)");
                    Matcher m = p.matcher(text);
                    int id = Integer.parseInt(m.group(1));
                    instance = Main.getGameInstance(id);
                    log.info("Joined Room " + id);
                    writer.println("joined");
                } else if (text.contains("##guess")) {
                    log.info("I FUCKING GUESSED IT");
                } else if (text.contains("##close")) {
                    socket.close();
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
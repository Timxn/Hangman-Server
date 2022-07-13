package de.oose.gameservice.gamelogic;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class HighscoreSystemImpl implements de.oose.gameservice.gamelogic.interfaces.HighscoreSystem {
    ArrayList<HighscoreUserImpl> list = new ArrayList<>();

    public HighscoreSystemImpl() {
        try {
            loadData();
        } catch (Exception e) {
            list = new ArrayList<>();
        }
    }

    @Override
    public void incrementUser(String username) {
        boolean incremented = true;
        for (HighscoreUserImpl user:list) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                user.setPoints(user.getPoints()+1);
                incremented = false;
            }
        }
        if (incremented) addUser(username);
        writeData();
    }

    private void addUser(String username) {
        this.list.add(new HighscoreUserImpl(username));
    }

    @Override
    public ArrayList<String> getSort() {
        ArrayList<HighscoreUserImpl> tmp = new ArrayList<>(list.stream().sorted(Comparator.comparing(HighscoreUserImpl::getPoints).reversed()).collect(Collectors.toList()));
        ArrayList<String> tmp2 = new ArrayList<>();
        for (HighscoreUserImpl user:tmp) {
            tmp2.add(user.getUsername() + ": " + user.getPoints());
        }
        return tmp2;
    }

    @Override
    public void writeData() {
        try {
            FileOutputStream fos = new FileOutputStream("highscores.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            System.out.println("Data written - Highscores!");
        } catch (IOException e) {
            System.err.println("Data couldn't be written - Highscores!");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadData() {
        try {
            FileInputStream fis = new FileInputStream("highscores.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (ArrayList<HighscoreUserImpl>) ois.readObject();
            ois.close();
            System.out.println("Data loaded - Highscores!");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Data couldn't be loaded - Highscores!");
            throw new RuntimeException(e);
        }
    }
}

package de.oose.gameservice.gamelogic;

import java.util.*;
import java.util.stream.Collectors;

public class HighscoreSystemImpl {
    ArrayList<HighscoreUserImpl> list;

    public HighscoreSystemImpl() {
        this.list = new ArrayList<>();
    }

    public void incrementUser(String username) {
        for (HighscoreUserImpl user:list) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                user.setPoints(user.getPoints()+1);
                return;
            }
        }
        addUser(username);
    }

    private void addUser(String username) {
        this.list.add(new HighscoreUserImpl(username));
    }

    public ArrayList<String> getSort() {
        ArrayList<HighscoreUserImpl> tmp = new ArrayList<>(list.stream().sorted(Comparator.comparing(HighscoreUserImpl::getPoints).reversed()).collect(Collectors.toList()));
        ArrayList<String> tmp2 = new ArrayList<>();
        for (HighscoreUserImpl user:tmp) {
            tmp2.add(user.getUsername() + ": " + user.getPoints());
        }
        return tmp2;
    }
}

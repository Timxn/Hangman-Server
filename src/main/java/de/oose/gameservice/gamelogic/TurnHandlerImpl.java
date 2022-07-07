package de.oose.gameservice.gamelogic;

import java.util.ArrayList;

public class TurnHandlerImpl {
    int currentTurn;
    private int order[];
    ArrayList<Integer> integers;

    public void setOrder(int count) {
        integers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            integers.add(i);
        }
        order  = new int[count];
        for (int i = 0; i < count; i++) {
            int temp = (int)((count - i) * Math.random());
            order[i] = temp;
            integers.remove(temp);
        }
        currentTurn = order[0];
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
}

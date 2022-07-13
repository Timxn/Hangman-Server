package de.oose.gameservice.gamelogic;

import java.util.ArrayList;

public class TurnHandlerImpl implements de.oose.gameservice.gamelogic.interfaces.TurnHandler {
    private int currentIndex = 0;
    private int[] order;

    @Override
    public void setOrder(int count, int skipper) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < count + 1; i++) {
            if (i != skipper)
                integers.add(i);
        }
        order  = new int[count];
        for (int i = 0; i < count; i++) {
            int temp = (int)((count - i) * Math.random());
            order[i] = integers.get(temp);
            integers.remove(temp);
        }
        currentIndex = 0;
    }

    @Override
    public int getCurrentTurn() {
        return order[currentIndex];
    }

    @Override
    public void nextTurn() {
        currentIndex++;
        if (currentIndex == order.length) {
            currentIndex = 0;
        }
    }
}

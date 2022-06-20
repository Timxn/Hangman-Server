package de.oose.gameservice.gamelogic;

import java.util.ArrayList;

public class GameController {
    public ArrayList<String> update() {
        move(); // make all moves for this frame (the function gets called every x seconds)
        ArrayList<String> tmp = new ArrayList<>();
        DrawBody tmp2 = new DrawBody(snake.getPositionX(), snake.getPositionY(), "snake_head_" + snake.getOrientation());
        tmp.add(tmp2.toString());
        return tmp;
    }
}

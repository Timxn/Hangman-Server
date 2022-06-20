package de.oose.gameservice.gamelogic;

public class DrawBody {
    private int positionX;
    private int positionY;
    private String type;

    public DrawBody(int positionX, int positionY, String type) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.type = type;
    }

    @Override
    public String toString() {
        return "DrawBody{" +
                "positionX=" + positionX +
                ", positionY=" + positionY +
                ", type='" + type + '\'' +
                '}';
    }
}

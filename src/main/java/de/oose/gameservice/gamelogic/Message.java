package de.oose.gameservice.gamelogic;

public class Message {
    public String key;
    public String value;

    @Override
    public String toString() {
        return "Message{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Message(String key, String value) {
        this.key = key;
        this.value = value;
    }
}


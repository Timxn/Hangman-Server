package de.oose.gameservice.gamelogic.utils;

public class RandomStringImpl {
    public static String getRandomString(int n) {
        final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int)(CHARS.length() * Math.random());
            sb.append(CHARS.charAt(index));
        }
        return sb.toString();
    }
}

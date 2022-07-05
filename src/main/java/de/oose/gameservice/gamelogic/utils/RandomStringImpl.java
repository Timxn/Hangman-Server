package de.oose.gameservice.gamelogic.utils;

public class RandomStringImpl {
    /**
     * generates a String with length n which includes random letters and numbers
     * @param n length of the String
     * @return the generated string
     */
    public static String getRandomString(int n) {
        if (n==0) throw new IllegalArgumentException("n must not be 0");
        final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int)(CHARS.length() * Math.random());
            sb.append(CHARS.charAt(index));
        }
        return sb.toString();
    }
}

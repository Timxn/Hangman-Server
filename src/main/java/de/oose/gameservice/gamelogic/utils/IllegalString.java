package de.oose.gameservice.gamelogic.utils;

public class IllegalString {
    public static boolean isAlpha(String text) {
        return text.chars().allMatch(Character::isLetter);
    }

    public static boolean isAlphaNumeric(String text) {
        return text.chars().allMatch(Character::isLetterOrDigit);
    }
}

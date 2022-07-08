package de.oose.gameservice.gamelogic.utils;

public class IllegalString {
    public static boolean isNotAlpha(String text) {
        return text == null || !text.chars().allMatch(Character::isLetter);
    }

    public static boolean isAlphaNumeric(String text) {
        return text != null && text.chars().allMatch(Character::isLetterOrDigit);
    }
}

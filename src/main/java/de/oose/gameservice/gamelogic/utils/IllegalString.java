package de.oose.gameservice.gamelogic.utils;

public class IllegalString {

    /**
     * checks if the string contains illegal characters
     * @param text the string to check
     * @return true if the string contains illegal characters, false otherwise
     */
    public static boolean isNotAlpha(String text) {
        return text == null || !text.chars().allMatch(Character::isLetter);
    }

    /**
     * checks if the string contains only alphabetical characters and numbers
     * @param text the string to check
     * @return returns true if the string contains only alphabetical characters and numbers, false otherwise
     */
    public static boolean isAlphaNumeric(String text) {
        return text != null && text.chars().allMatch(Character::isLetterOrDigit);
    }
}

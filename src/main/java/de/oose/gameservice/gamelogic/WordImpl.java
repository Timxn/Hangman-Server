package de.oose.gameservice.gamelogic;

public class WordImpl implements de.oose.gameservice.gamelogic.interfaces.Word {
    private String word = null;
    private String hiddenWord = "";

    @Override
    public String getWord() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <hiddenWord.length() ; i++) {
            sb.append(hiddenWord.charAt(i));
            if (i != hiddenWord.length() - 1)
                sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public void setWord(String word) throws Exception {
        if (this.word != null) throw new Exception("Word already set");
        this.word = word;
        StringBuilder sb = new StringBuilder();
        for (char ignored : word.toCharArray()) {
            sb.append("_");
        }
        hiddenWord = sb.toString();
    }

    @Override
    public boolean guessLetter(char letter) {
        boolean correct = false;
        for (int i = 0; i <word.length() ; i++) {
            if (word.charAt(i) == letter && hiddenWord.charAt(i) == '_') {
                hiddenWord = hiddenWord.substring(0, i) + letter + hiddenWord.substring(i + 1);
                correct = true;
            }
        }
        return correct;
    }

    @Override
    public boolean isWordGuessed() {
        return word.equals(hiddenWord);
    }

    @Override
    public void resetWord() {
        word = null;
        hiddenWord = "";
    }
}

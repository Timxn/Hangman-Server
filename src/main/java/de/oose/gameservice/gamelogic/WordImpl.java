package de.oose.gameservice.gamelogic;

public class WordImpl {
    private String word = null;
    private String hiddenWord = "";

    public String getWord() {
        return hiddenWord;
    }

    public void setWord(String word) throws Exception {
        if (this.word != null) throw new Exception("Word already set");
        this.word = word;
        StringBuilder sb = new StringBuilder();
        for (char ignored : word.toCharArray()) {
            sb.append("_");
        }
        hiddenWord = sb.toString();
    }
}

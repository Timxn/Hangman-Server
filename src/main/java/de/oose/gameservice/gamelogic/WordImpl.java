package de.oose.gameservice.gamelogic;

public class WordImpl {
    private String word = null;
    private String hiddenWord = "";

    public String getWord() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <hiddenWord.length() ; i++) {
            sb.append(hiddenWord.charAt(i));
            if (i != hiddenWord.length() - 1)
                sb.append("X");
        }
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

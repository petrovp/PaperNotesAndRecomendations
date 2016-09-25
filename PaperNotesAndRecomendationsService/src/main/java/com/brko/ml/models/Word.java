package com.brko.ml.models;

/**
 * Simplified word model for {@link Word2Vec}.
 *
 * Created by ppetrov on 9/18/2016.
 */
public class Word {

    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }
}

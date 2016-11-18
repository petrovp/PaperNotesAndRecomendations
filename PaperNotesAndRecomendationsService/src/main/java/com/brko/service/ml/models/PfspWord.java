package com.brko.service.ml.models;

/**
 * Simplified word model for {@link PfspWord2Vec}.
 *
 * Created by ppetrov on 9/18/2016.
 */
public class PfspWord {

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

package com.brko.ml.models;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Simplified Word2Vec model.
 *
 * Created by ppetrov on 9/18/2016.
 */
public class Word2Vec {

    private Map<Word, Vector<Float>> wordVectorMap;

    public Map<Word, Vector<Float>> getWordVectorMap() {
        return wordVectorMap;
    }

    public void setWordVectorMap(Map<Word, Vector<Float>> wordVectorMap) {
        this.wordVectorMap = wordVectorMap;
    }

    public void addWordVecToModel(String wordStr, float[] vectorArray) {
        Word word = new Word();
        word.setWord(wordStr);

        Vector<Float> vector = new Vector<Float>();
        for (float vecIn : vectorArray) {
            vector.add(vecIn);
        }

        wordVectorMap.put(word, vector);
    }

}

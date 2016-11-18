package com.brko.service.ml.models;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Vector;

/**
 * Simplified Word2Vec model.
 *
 * Created by ppetrov on 9/18/2016.
 */
public class PfspWord2Vec {

    private Map<String, PfspVector> wordVectorMap;
    private Map<String, Float> wordVectorsLength;

    public PfspWord2Vec() {
        this.wordVectorMap = Maps.newHashMap();
    }

    public Map<String, PfspVector> getWordVectorMap() {
        return wordVectorMap;
    }

    public void setWordVectorMap(Map<String, PfspVector> wordVectorMap) {
        this.wordVectorMap = wordVectorMap;
    }

    public void addWordVecToModel(String wordStr, float[] vectorArray) {
        PfspVector pfspVector = new PfspVector(vectorArray);
        wordVectorMap.put(wordStr, pfspVector);
    }

    public double similarity(String word1, String word2) {
        PfspVector vector1 = wordVectorMap.get(word1);
        PfspVector vector2 = wordVectorMap.get(word2);

        if (vector1 == null || vector2 == null) {
            return 0.0;
        }

        return word1.equals(word2)
                ? 1.0D
                : innerProduct(vector1.getVector(), vector2.getVector())/(vector1.getLength() * vector2.getLength());
    }

    private double innerProduct(Vector<Float> vector1, Vector<Float> vector2) {
        double product = 0.0;
        for(int i=0;i<vector1.size();i++) {
            product += vector1.get(i)*vector2.get(i);
        }
        return product;
    }
}

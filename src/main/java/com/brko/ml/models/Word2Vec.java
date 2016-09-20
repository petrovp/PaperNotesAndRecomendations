package com.brko.ml.models;

import com.google.common.collect.Lists;
import org.nd4j.linalg.ops.transforms.Transforms;

import java.util.*;

/**
 * Simplified Word2Vec model.
 *
 * Created by ppetrov on 9/18/2016.
 */
public class Word2Vec {

    private Map<String, Vector<Float>> wordVectorMap;

    public Map<String, Vector<Float>> getWordVectorMap() {
        return wordVectorMap;
    }

    public void setWordVectorMap(Map<String, Vector<Float>> wordVectorMap) {
        this.wordVectorMap = wordVectorMap;
    }

    public void addWordVecToModel(String wordStr, float[] vectorArray) {
        Vector<Float> vector = new Vector<Float>();
        for (float vecIn : vectorArray) {
            vector.add(vecIn);
        }
        wordVectorMap.put(wordStr, vector);
    }

    public double similarity(String word1, String word2) {
        Vector<Float> vector1 = wordVectorMap.get(word1);
        Vector<Float> vector2 = wordVectorMap.get(word2);

        if (vector1 == null || vector2 == null) {
            return 0.0;
        }
        return word1.equals(word2)?1.0D: innerProduct(vector1, vector2)/(length(vector1) * length(vector2));
    }

    private double length(Vector<Float> vector2) {
        double length = 0.0;
        for (Float f : vector2) {
            length+= f*f;
        }
        return Math.sqrt(length);
    }

    private double innerProduct(Vector<Float> vector1, Vector<Float> vector2) {
        double product = 0.0;
        for(int i=0;i<vector1.size();i++) {
            product += vector1.get(i)*vector2.get(i);
        }
        return product;
    }
}

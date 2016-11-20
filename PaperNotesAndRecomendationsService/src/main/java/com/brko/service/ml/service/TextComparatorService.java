package com.brko.service.ml.service;

/**
 * Created by ppetrov on 9/20/2016.
 */
public interface TextComparatorService {

    /**
     * Computes the similarity between a text from paper and note with word2vec.
     * @param paperText the text from Paper
     * @param noteText the text from Note
     * @return the similarity
     */
    double computeDistance(String paperText, String noteText);
}

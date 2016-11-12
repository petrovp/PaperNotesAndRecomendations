package com.brko.service.ml.service.impl;

import com.brko.service.ml.models.Word2Vec;
import com.brko.service.ml.service.TextComparatorService;
import com.google.common.collect.Lists;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.Collection;
import java.util.List;

/**
 * Created by ppetrov on 9/20/2016.
 */
@Service
public class TextComparatorServiceImpl implements TextComparatorService {

    private Word2Vec word2VecModel;

    public double computeSimilarity(String paperText, String noteText) {

        List<String> paperWords = getWordsFromText(paperText);
        List<String> noteWords = getWordsFromText(noteText);

        double similarity = 1.0;

        for (String noteWord : noteWords) {
            double maxSim = 0.0;
            for (String paperWord : paperWords) {
                maxSim = Math.max(maxSim, word2VecModel.similarity(noteWord, paperWord));
            }

            if (maxSim != 0) {
                similarity *= maxSim;
            }
        }

        return similarity;
    }

    private List<String> getWordsFromText(String text) {
        DocumentPreprocessor documentPreprocessor1 = new DocumentPreprocessor(new StringReader(text));

        List<String> firstTextWords = Lists.newArrayList();
        for (List<HasWord> sentence : documentPreprocessor1) {
            firstTextWords.addAll(getWordsFromSentance(sentence));
        }
        return firstTextWords;
    }

    private Collection<? extends String> getWordsFromSentance(List<HasWord> sentence) {
        List<String> sentanceWords = Lists.newArrayList();
        for (HasWord hasWord : sentence) {
            sentanceWords.add(hasWord.word());
        }
        return sentanceWords;
    }
}

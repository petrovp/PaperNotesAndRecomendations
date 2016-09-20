package com.brko.ml.service.impl;

import com.brko.ml.service.TextComparatorService;
import com.google.common.collect.Lists;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.process.DocumentPreprocessor;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.springframework.scheduling.annotation.Scheduled;
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

    public double computeSimilarity(String firstText, String secondText) {

        List<String> firstTextWords = getWordsFromText(firstText);
        List<String> secondTextWords = getWordsFromText(secondText);

        double similarity = 0.0;

        // TODO 2016-21-09 ppetrov: make the computation like in "From word embedings to document distances" paper.

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

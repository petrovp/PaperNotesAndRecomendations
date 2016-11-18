package com.brko.service.ml.service.impl;

import com.brko.service.ml.models.PfspStopWords;
import com.brko.service.ml.models.PfspWord2Vec;
import com.brko.service.ml.service.TextComparatorService;
import com.google.common.collect.Lists;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipFile;

/**
 * Created by ppetrov on 9/20/2016.
 */
@Service
public class TextComparatorServiceImpl implements TextComparatorService {

    final Logger logger = Logger.getLogger(this.getClass().getName());

    private PfspWord2Vec pfspWord2VecModel;

    @PostConstruct
    public void loadWord2VecModel() {
        pfspWord2VecModel = new PfspWord2Vec();

        try (ZipFile word2VecZip = new ZipFile("C:\\Users\\ppetrov\\Documents\\ppt_private\\diplomska\\word2vec.zip");
             InputStream inputStream = word2VecZip.getInputStream(word2VecZip.entries().nextElement());
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String parts [] = br.readLine().split(" ");
            int numWords = Integer.parseInt(parts[0]);
            int vectorSize = Integer.parseInt(parts[1]);
            logger.info(String.format("Loading %s words with vectors. Vector size is %s.", numWords, vectorSize));

            for (int t=0;t<numWords;t++) {

                String line = br.readLine();
                parts = line.split("<->");

                String word = parts[0];

                String[] vectorParts = parts[1].split(" ");
                float[] vector = new float[vectorParts.length];
                for (int i = 0; i < vector.length; i++) {
                    vector[i] = Float.parseFloat(vectorParts[i]);
                }

                pfspWord2VecModel.addWordVecToModel(word, vector);
            }

            logger.info("PfspWord2Vec model is loaded.");
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public double computeSimilarity(String paperText, String noteText) {

        List<String> paperWords = getWordsFromText(paperText);
        List<String> noteWords = getWordsFromText(noteText);

        double similarity = 1.0;

        for (String noteWord : noteWords) {
            double maxSim = 0.0;

            for (String paperWord : paperWords) {
                maxSim = Math.max(maxSim, pfspWord2VecModel.similarity(noteWord, paperWord));
            }

            if (maxSim != 0) {
                similarity *= maxSim;
            }
        }

        return similarity;
    }

    private List<String> getWordsFromText(String text) {
        DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(new StringReader(text));

        List<String> firstTextWords = Lists.newArrayList();
        for (List<HasWord> sentence : documentPreprocessor) {
            firstTextWords.addAll(getWordsFromSentance(sentence));
        }
        return firstTextWords;
    }

    private Collection<? extends String> getWordsFromSentance(List<HasWord> sentence) {
        List<String> sentanceWords = Lists.newArrayList();
        for (HasWord hasWord : sentence) {
            if (PfspStopWords.isStopWord(hasWord.word())) {
                continue;
            }

            sentanceWords.add(hasWord.word());
        }
        return sentanceWords;
    }
}

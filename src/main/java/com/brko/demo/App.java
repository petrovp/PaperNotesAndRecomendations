package com.brko.demo;

import com.brko.service.crawler.exceptions.CrawlingPapersException;
import com.brko.service.crawler.exceptions.ReadPaperSummaryFromHtmlException;
import com.brko.service.crawler.exceptions.ReadPdfContentException;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class App {

    public static void main(String[] args ) throws IOException,
            ReadPdfContentException, ReadPaperSummaryFromHtmlException, CrawlingPapersException {

        DocumentPreprocessor documentPreprocessor =
            new DocumentPreprocessor(new StringReader("asdasd . asda sdw Petre. Petrov e dobar."));
        Iterator<List<HasWord>> iterator = documentPreprocessor.iterator();
        while (iterator.hasNext()) {
            List<HasWord> sentanceWords = iterator.next();
            for (HasWord hasWord : sentanceWords) {
                System.out.println(hasWord.word());
            }
        }
    }
}

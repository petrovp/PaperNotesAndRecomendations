package com.brko.service.demo;

import com.brko.service.services.crawler.exceptions.CrawlingPapersException;
import com.brko.service.services.crawler.exceptions.ReadPaperSummaryFromHtmlException;
import com.brko.service.services.crawler.exceptions.ReadPdfContentException;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class App {

    private static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args ) throws IOException,
            ReadPdfContentException, ReadPaperSummaryFromHtmlException, CrawlingPapersException {

        DocumentPreprocessor documentPreprocessor =
            new DocumentPreprocessor(new StringReader("asdasd . asda sdw Petre. Petrov e dobar."));
        Iterator<List<HasWord>> iterator = documentPreprocessor.iterator();
        while (iterator.hasNext()) {
            List<HasWord> sentanceWords = iterator.next();
            for (HasWord hasWord : sentanceWords) {
                logger.info(hasWord.word());
            }
        }
    }
}

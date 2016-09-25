package com.brko.service.crawler.impl;

import com.brko.GlobalConstants;
import com.brko.persistance.datamodel.PaperSummary;
import com.brko.persistance.repository.PaperSummaryRepository;
import com.brko.service.crawler.exceptions.CrawlingPapersException;
import com.brko.service.crawler.ArxivPaperSearcherService;
import com.brko.service.crawler.PaperSummeryReaderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Implementation class of {@link ArxivPaperSearcherService}.
 *
 * Created by Petre on 9/4/2016.
 */
@Service
public class ArxivPaperSearcherServiceImpl implements ArxivPaperSearcherService {

    Logger logger = Logger.getLogger(getClass());

    private static final int CONSISTENT_ERRORS_THRESHOLD = 50;

    @Autowired
    private PaperSummeryReaderService paperSummeryReaderService;

    @Autowired
    private PaperSummaryRepository paperSummaryRepository;

    public void searchPapersByMonthAndYearInArxiv(int year, int month)
            throws CrawlingPapersException, IOException {

        logger.info("Crawling for " + month + " " +year);
        int numberOfConsistentErrors = 0;
        int consecutiveNumber = 0;
        while (numberOfConsistentErrors < CONSISTENT_ERRORS_THRESHOLD) {

            consecutiveNumber++;
            PaperSummary paperSummary;

            try {
                String url = constructPaperUrl(year, month, consecutiveNumber);
                paperSummary = paperSummeryReaderService.readAbstractFromArxivHtml(url);
            } catch (Exception e) {
                numberOfConsistentErrors++;
                continue;
            }

            numberOfConsistentErrors = 0;
            savePaperSummaryInTheFileSystem(paperSummary);
            paperSummaryRepository.save(paperSummary);
        }
    }

    private void savePaperSummaryInTheFileSystem(PaperSummary paperSummary) throws IOException {

        File summaryFile = new File(
                GlobalConstants.INTERNAL_PATH_FOR_SAVING_THE_SUMMARIES + "\\" +paperSummary.getArxivId());
        summaryFile.createNewFile();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(summaryFile));

        String summaryStringValue = paperSummary.toString();
        byte[] summaryStringValueBytes = summaryStringValue.getBytes(GlobalConstants.CHARSET);
        bufferedOutputStream.write(summaryStringValueBytes);
        bufferedOutputStream.close();
    }

    private String constructPaperUrl(int year, int month, int consecutiveNumber) {

        return String.format("%s/%02d%02d.%05d",
                GlobalConstants.ARXIV_ABSTRACTS_BASE_URL,
                year % 100,
                month,
                consecutiveNumber);
    }
}

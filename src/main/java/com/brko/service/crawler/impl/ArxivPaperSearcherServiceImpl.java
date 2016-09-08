package com.brko.service.crawler.impl;

import com.brko.GlobalConstants;
import com.brko.persistance.datamodel.PaperSummary;
import com.brko.service.crawler.exceptions.CrawlingPapersException;
import com.brko.service.crawler.exceptions.ReadPaperSummaryFromHtmlException;
import com.brko.service.crawler.interfaces.ArxivPaperSearcherService;
import com.brko.service.crawler.interfaces.PaperSummeryReaderService;
import org.jsoup.HttpStatusException;
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

    private static final int CONSISTENT_ERRORS_THRESHOLD = 4;

    private String INTERNAL_PATH_FOR_SAVING_THE_SUMMARIES =
            "D:\\Fakultet\\Osmi Semestar\\Diplomska rabota\\crawled_summaries";

    @Autowired
    private PaperSummeryReaderService paperSummeryReaderService;

    public void searchPapersByMonthAndYearInArxiv(int year, int month)
            throws CrawlingPapersException, IOException {

        int numberOfConsistentErrors = 0;
        int consecutiveNumber = 0;
        while (numberOfConsistentErrors < CONSISTENT_ERRORS_THRESHOLD) {

            consecutiveNumber++;
            PaperSummary paperSummary;

            try {

                String url = constructPaperUrl(year, month, consecutiveNumber);
                paperSummary = paperSummeryReaderService.readAbstractFromArxivHtml(url);

            } catch (HttpStatusException e) {
                numberOfConsistentErrors++;
                continue;
            } catch (ReadPaperSummaryFromHtmlException e) {
                throw new CrawlingPapersException("Exception occurred during crawling the papers.", e);
            }

            numberOfConsistentErrors = 0;
            savePaperSummaryInTheFileSystem(paperSummary);
        }
    }

    private void savePaperSummaryInTheFileSystem(PaperSummary paperSummary) throws IOException {

        File summaryFile = new File(INTERNAL_PATH_FOR_SAVING_THE_SUMMARIES + "\\" +paperSummary.getArxivId());
        summaryFile.createNewFile();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(summaryFile));

        String summaryStringValue = paperSummary.toString();
        byte[] summaryStringValueBytes = summaryStringValue.getBytes(GlobalConstants.CHARSET);
        bufferedOutputStream.write(summaryStringValueBytes);
        bufferedOutputStream.close();
    }

    private String constructPaperUrl(int year, int month, int consecutiveNumber) {

        String url = String.format("%s/%02d%02d.%05d",
                GlobalConstants.ARXIV_ABSTRACTS_BASE_URL,
                year % 100,
                month,
                consecutiveNumber);

        return url;
    }
}

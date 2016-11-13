package com.brko.service.services.crawler.impl;

import com.brko.service.GlobalConstants;
import com.brko.service.persistance.datamodel.PaperSummary;
import com.brko.service.persistance.repository.PaperSummaryRepository;
import com.brko.service.services.crawler.ArxivPaperSearcherService;
import com.brko.service.services.crawler.PaperSummeryReaderService;
import com.brko.service.services.crawler.exceptions.CrawlingPapersException;
import com.google.common.collect.Sets;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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

        Set<String> alreadyCrawled = getCrawledPaperIds();

        logger.info("Crawling for " + month + " " +year);
        int numberOfConsistentErrors = 0;
        int consecutiveNumber = 0;
        while (numberOfConsistentErrors < CONSISTENT_ERRORS_THRESHOLD) {

            consecutiveNumber++;
            PaperSummary paperSummary;

            try {
                String arxivId = getArxivId(year, month, consecutiveNumber);
                if (alreadyCrawled.contains(arxivId)) {
                    continue;
                }

                String url = constructPaperUrl(arxivId);
                paperSummary = paperSummeryReaderService.readAbstractFromArxivHtml(url);
            } catch (Exception e) {
                numberOfConsistentErrors++;
                continue;
            }

            numberOfConsistentErrors = 0;
            //savePaperSummaryInTheFileSystem(paperSummary);
            paperSummaryRepository.save(paperSummary);
        }
    }

    private Set<String> getCrawledPaperIds() {
        Set<String> ids = Sets.newHashSet();

        List<PaperSummary> all = paperSummaryRepository.findAll();
        for (PaperSummary paperSummary : all) {
            ids.add(paperSummary.getArxivId());
        }

        return ids;
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

    private String constructPaperUrl(String arxivId) {
        return String.format("%s/%s", GlobalConstants.ARXIV_ABSTRACTS_BASE_URL, arxivId);
    }

    private String getArxivId(int year, int month, int consecutiveNumber) {
        return String.format("%02d%02d.%05d",
                year % 100,
                month,
                consecutiveNumber);
    }
}

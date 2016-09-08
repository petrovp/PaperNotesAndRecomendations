package com.brko.service.crawler.impl;

import com.brko.persistance.datamodel.PaperSummary;
import com.brko.service.crawler.interfaces.PaperSummeryReaderService;
import com.brko.service.crawler.exceptions.ReadPaperSummaryFromHtmlException;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * Implementation class of {@link PaperSummeryReaderService}.
 *
 * Created by Petre on 9/3/2016.
 */
@Service
public class PaperSummeryReaderServiceImpl implements PaperSummeryReaderService {

    private final static String ABSTRACT_SELECTOR = "blockquote.abstract";

    private final static String TITLE_SELECTOR = "h1.title";

    private final static String CATEGORY_SELECTOR = "div.subheader";


    public PaperSummary readAbstractFromArxivHtml(String paperSummeryUrl) throws ReadPaperSummaryFromHtmlException, HttpStatusException {

        PaperSummary paperSummary = null;
        try{
            Document doc = Jsoup.connect(paperSummeryUrl).get();

            paperSummary = new PaperSummary();
            paperSummary.setAbstractContent(extractElement(doc, ABSTRACT_SELECTOR, 10));
            paperSummary.setTitle(extractElement(doc, TITLE_SELECTOR, 7));
            paperSummary.setCategory(extractElement(doc, CATEGORY_SELECTOR, 0));

            String arxivId = paperSummeryUrl.substring(paperSummeryUrl.lastIndexOf('/') + 1);
            paperSummary.setArxivId(arxivId);
        } catch (HttpStatusException e) {
            throw e;
        }catch (Exception e) {
            throw new ReadPaperSummaryFromHtmlException(String.format(
                    "Exception occurred while reading the paper summary of paper with url=%s.", paperSummeryUrl),
                    e);
        }

        return paperSummary;
    }

    private String extractElement (Document document, String selector, int beginIndex) {
        Elements elements = document.select(selector);
        String text = elements.text();
        return text != null ? text.substring(beginIndex) : null;
    }
}

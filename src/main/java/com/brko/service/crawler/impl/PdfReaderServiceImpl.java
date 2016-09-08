package com.brko.service.crawler.impl;

import com.brko.service.crawler.interfaces.PdfReaderService;
import com.brko.service.crawler.exceptions.ReadPdfContentException;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;

/**
 * Implementation class of {@link PdfReaderService}
 *
 * Created by Petre on 9/3/2016.
 */
@Service
public class PdfReaderServiceImpl implements PdfReaderService {

    public String readPdfContent(String pdfUrl) throws ReadPdfContentException {
        String content = null;

        PDFTextStripper pdfStripper = null;
        PDDocument pdfDocument = null;
        COSDocument cosDocument = null;

        try {
            URL url = new URL(pdfUrl);
            InputStream inputStream = url.openStream();

            RandomAccessRead randomAccessRead = new RandomAccessBufferedFileInputStream(inputStream);
            PDFParser parser = new PDFParser(randomAccessRead);
            parser.parse();

            cosDocument = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdfDocument = new PDDocument(cosDocument);

            int numberOfPages = pdfDocument.getNumberOfPages();

            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(numberOfPages);

            content = pdfStripper.getText(pdfDocument);
        } catch (Exception e) {
            throw new ReadPdfContentException("Exception occurred while reading pdf content.", e);
        }

        return content;
    }
}

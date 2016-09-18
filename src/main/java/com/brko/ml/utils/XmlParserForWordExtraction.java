package com.brko.ml.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.TreeSet;

/**
 * Created by ppetrov on 9/17/2016.
 */
public class XmlParserForWordExtraction {

    static TreeSet<String> words = new TreeSet<String> ();

    public static void main (String [] args) throws IOException, ParserConfigurationException, SAXException {

        String acaFolder = "C:\\Users\\ppetrov\\Documents\\ppt_private\\diplomska\\words\\2553\\2553\\download\\Texts\\aca";
        String demFolder = "C:\\Users\\ppetrov\\Documents\\ppt_private\\diplomska\\words\\2553\\2553\\download\\Texts\\dem";
        String ficFolder = "C:\\Users\\ppetrov\\Documents\\ppt_private\\diplomska\\words\\2553\\2553\\download\\Texts\\fic";
        String newsFolder = "C:\\Users\\ppetrov\\Documents\\ppt_private\\diplomska\\words\\2553\\2553\\download\\Texts\\news";

        extractWordsFromFolder(acaFolder);
        extractWordsFromFolder(demFolder);
        extractWordsFromFolder(ficFolder);
        extractWordsFromFolder(newsFolder);

        String words1FileName = "C:\\Users\\ppetrov\\Documents\\ppt_private\\diplomska\\words\\words1.txt";
        String words2FileName = "C:\\Users\\ppetrov\\Documents\\ppt_private\\diplomska\\words\\words2.txt";
        String words3FileName = "C:\\Users\\ppetrov\\Documents\\ppt_private\\diplomska\\words\\words3.txt";
        String words1GitFileName = "C:\\Users\\ppetrov\\Documents\\ppt_private\\diplomska\\words\\words1-git.txt";
        String words2GitFileName = "C:\\Users\\ppetrov\\Documents\\ppt_private\\diplomska\\words\\words2-git.txt";
        String words3GitFileName = "C:\\Users\\ppetrov\\Documents\\ppt_private\\diplomska\\words\\words3-git.txt";

        readWordsFromWordsFile(words1FileName);
        readWordsFromWordsFile(words2FileName);
        readWordsFromWordsFile(words3FileName);
        readWordsFromWordsFile(words1GitFileName);
        readWordsFromWordsFile(words2GitFileName);
        readWordsFromWordsFile(words3GitFileName);

        String finalWordsFilename = "C:\\Users\\ppetrov\\Documents\\ppt_private\\diplomska\\words\\final_words.txt";
        saveWordsInFile(finalWordsFilename);
    }

    private static void saveWordsInFile(String fileName) throws IOException {
        File file = new File(fileName);
        file.createNewFile();

        PrintWriter pw = new PrintWriter(new FileWriter(file));
        for(String word : words) {
            pw.write(word);
            pw.write("\n");
        }

        pw.flush();
        pw.close();
    }

    private static void readWordsFromWordsFile(String fileName) throws IOException {
        File wordsFile = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(wordsFile));

        String line = null;
        while ((line=br.readLine())!=null) {
            line = line.trim();
            words.add(line);
        }
    }

    private static void extractWordsFromFolder(String folderName) throws ParserConfigurationException, SAXException, IOException {
        File folder = new File(folderName);
        File[] fileList = folder.listFiles();
        for (File file : fileList) {
            getWordsFromFile(file.getAbsolutePath());
        }
    }

    private static void getWordsFromFile(String fileName) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new FileInputStream(new File(fileName)));

        NodeList wordNodes = document.getElementsByTagName("w");

        int length = wordNodes.getLength();
        for (int i=0;i<length;i++) {
            Node item = wordNodes.item(i);

            String textContent = item.getTextContent();
            words.add(textContent.trim());
        }
    }
}

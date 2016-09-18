package com.brko.ml.utils;

import com.brko.GlobalConstants;
import org.apache.commons.compress.compressors.gzip.GzipUtils;

import java.io.*;
import java.util.*;
import java.util.zip.GZIPInputStream;

import static org.deeplearning4j.models.embeddings.loader.WordVectorSerializer.readFloat;
import static org.deeplearning4j.models.embeddings.loader.WordVectorSerializer.readString;

/**
 * Filter the set of 100B words to my set of 500K words.
 *
 * Created by ppetrov on 9/18/2016.
 */
public class FilterGooglesWord2Vec {

    private static final int ONE_MILLION = 1000000;
    static Set<String> myWords = new HashSet<String>();
    static Map<String, float[]> wordVsVector = new TreeMap<String, float[]>();

    public static void main (String [] args) throws IOException {

        fillMyWordsSet();

        readAndFilterWordsFromGoogleModel();

        saveFilteredWordToVec();
    }

    private static void saveFilteredWordToVec() throws FileNotFoundException {
        File modelFile = new File("C:\\Users\\ppetrov\\Documents\\ppt_private\\diplomska\\word2vec.txt");
        PrintWriter pw = new PrintWriter(modelFile);

        pw.write(wordVsVector.size()+" ");
        pw.write(GlobalConstants.VECTOR_SIZE+"\n");
        for (Map.Entry<String, float[]> entry : wordVsVector.entrySet()) {
            pw.write(entry.getKey()+" ");
            pw.write(Arrays.toString(entry.getValue())+"\n");
        }

        pw.flush();
        pw.close();
    }

    static void readAndFilterWordsFromGoogleModel() throws IOException {

        File modelFile = new File("C:\\Users\\ppetrov\\Documents\\ppt_private\\diplomska\\GoogleNews-vectors-negative300.bin.gz");
        BufferedInputStream ret = new BufferedInputStream((InputStream)(GzipUtils.isCompressedFilename(modelFile.getName())?new GZIPInputStream(new FileInputStream(modelFile)):new FileInputStream(modelFile)));

        DataInputStream dis = new DataInputStream(ret);
        int words = Integer.parseInt(readString(dis));
        int size = Integer.parseInt(readString(dis));

        System.out.println(words);

        for (int i = 0; i < words; ++i) {
            String word = readString(dis);

            if (i % ONE_MILLION == 0) {
                System.out.println(i + " : " + word);
            }

            float[] vector = new float[size];

            for (int j = 0; j < size; ++j) {
                vector[j] = readFloat(dis);
            }

            if (myWords.contains(word)){
                wordVsVector.put(word, vector);
            }
        }
    }

    private static void fillMyWordsSet() throws IOException {
        String fileName = "C:\\Users\\ppetrov\\Documents\\ppt_private\\diplomska\\final_words.txt";

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String word = null;
        while ((word = br.readLine()) != null) {
            myWords.add(word.trim());
        }
    }
}

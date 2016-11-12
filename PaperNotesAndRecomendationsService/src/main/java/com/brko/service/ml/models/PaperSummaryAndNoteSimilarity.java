package com.brko.service.ml.models;

import com.brko.service.persistance.datamodel.Note;
import com.brko.service.persistance.datamodel.PaperSummary;

/**
 * Created by ppetrov on 11/12/2016.
 */
public class PaperSummaryAndNoteSimilarity implements Comparable<PaperSummaryAndNoteSimilarity>{
    private PaperSummary paperSummary;
    private Note note;
    private double similarity;

    public PaperSummaryAndNoteSimilarity(PaperSummary paperSummary, Note note, double similarity) {
        this.paperSummary = paperSummary;
        this.note = note;
        this.similarity = similarity;
    }

    public int compareTo(PaperSummaryAndNoteSimilarity o) {
        return Double.compare(o.similarity, similarity);
    }

    public PaperSummary getPaperSummary() {
        return paperSummary;
    }

    public void setPaperSummary(PaperSummary paperSummary) {
        this.paperSummary = paperSummary;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public double getSimilarity() {
        return similarity;
    }
}

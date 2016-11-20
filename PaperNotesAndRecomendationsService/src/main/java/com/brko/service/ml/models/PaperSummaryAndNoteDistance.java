package com.brko.service.ml.models;

import com.brko.service.persistance.datamodel.Note;
import com.brko.service.persistance.datamodel.PaperSummary;

/**
 * Created by ppetrov on 11/12/2016.
 */
public class PaperSummaryAndNoteDistance implements Comparable<PaperSummaryAndNoteDistance>{
    private PaperSummary paperSummary;
    private Note note;
    private double distance;

    public PaperSummaryAndNoteDistance(PaperSummary paperSummary, Note note, double distance) {
        this.paperSummary = paperSummary;
        this.note = note;
        this.distance = distance;
    }

    public int compareTo(PaperSummaryAndNoteDistance o) {
        return Double.compare(o.distance, distance);
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

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public double getSimilarity() {
        return 1.0 - this.getDistance();
    }
}

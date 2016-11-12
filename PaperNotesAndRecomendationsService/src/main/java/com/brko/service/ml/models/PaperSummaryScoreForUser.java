package com.brko.service.ml.models;

import com.brko.service.persistance.datamodel.PaperSummary;
import com.brko.service.persistance.datamodel.User;

/**
 * Created by ppetrov on 11/12/2016.
 */
public class PaperSummaryScoreForUser implements Comparable<PaperSummaryScoreForUser>{

    private PaperSummary paperSummary;
    private User user;
    private  double score;

    public PaperSummaryScoreForUser() {
    }

    public PaperSummaryScoreForUser(PaperSummary paperSummary, User user, double score) {
        this.paperSummary = paperSummary;
        this.user = user;
        this.score = score;
    }

    public PaperSummary getPaperSummary() {
        return paperSummary;
    }

    public void setPaperSummary(PaperSummary paperSummary) {
        this.paperSummary = paperSummary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int compareTo(PaperSummaryScoreForUser o) {
        return Double.compare(o.score, score);
    }
}

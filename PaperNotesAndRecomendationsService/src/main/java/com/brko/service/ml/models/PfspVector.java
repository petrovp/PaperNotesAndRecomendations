package com.brko.service.ml.models;

import java.util.Vector;

/**
 * Created by ppetrov on 11/18/2016.
 */
public class PfspVector {

    private Vector<Float> vector;
    private double length;

    public PfspVector(float [] elements) {
        this.vector = new Vector<>();
        for (float element : elements) {
            this.vector.add(element);
        }
        this.length = calculateLength();

        normalizeVector();
    }

    private void normalizeVector() {
        for (float el : vector) {
            el/=this.length;
        }
        this.length = 1.0;
    }

    private double calculateLength() {
        double length = 0.0;
        for (Float f : vector) {
            length+= f*f;
        }
        return Math.sqrt(length);
    }

    public Vector<Float> getVector() {
        return vector;
    }

    public void setVector(Vector<Float> vector) {
        this.vector = vector;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}

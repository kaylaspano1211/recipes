package com.web.recipes.model;

public class Measurements {

    private int measurementId;
    private String measurementName;

    public Measurements(int measurementId, String measurementName) {
        this.measurementId = measurementId;
        this.measurementName = measurementName;
    }

    public Measurements() {

    }

    public int getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }
}

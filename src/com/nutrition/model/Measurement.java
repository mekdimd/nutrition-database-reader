package com.nutrition.model;

public class Measurement {
    private int id;
    private String name;
    private double conversionFactor;

    public Measurement(int id, String name, double conversionFactor) {
        this.id = id;
        this.name = name;
        this.conversionFactor = conversionFactor;
    }

    public Measurement(String[] strRecord) {
        this.id = Integer.parseInt(strRecord[0]);
        this.name = strRecord[1];
        this.conversionFactor = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }
}

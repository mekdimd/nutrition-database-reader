package com.nutrition.model;

public class Nutrient {
    private final int ID;
    private final int CODE;
    private final String SYMBOL;
    private final String UNIT;
    private final String NAME;

    public Nutrient(String[] strRecord) {
        this.ID = Integer.parseInt(strRecord[0]);
        this.CODE = Integer.parseInt(strRecord[1]);
        this.SYMBOL = strRecord[2];
        this.UNIT = strRecord[3];
        this.NAME = strRecord[4];
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return NAME;
    }

    public String getUnit() {
        return UNIT;
    }
}

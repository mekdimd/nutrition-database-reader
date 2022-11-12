package com.nutrition.model;

public class NutrientValue {
    private int id = 0;
    private int foodId = 0;
    private double value = 0;
    private String name = "";
    private String portion = "100g";
    private String unit = "";

    public NutrientValue(String[] strRecord) {
        this.foodId = Integer.parseInt(strRecord[0]);
        this.id = Integer.parseInt(strRecord[1]);
        this.value = Float.parseFloat(strRecord[2]);
    }

    public NutrientValue(String[] strRecords, String strPortion) {
        this(strRecords);
        this.portion = strPortion;
    }

    public int getId() {
        return id;
    }

    public int getFoodId() {
        return foodId;
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

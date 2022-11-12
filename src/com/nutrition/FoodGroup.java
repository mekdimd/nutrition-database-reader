package com.nutrition;

public class FoodGroup {
    private final int FOOD_ID;
    private final int CODE;
    private final String NAME;

    public FoodGroup(String[] strRecord) {
        this.FOOD_ID = Integer.parseInt(strRecord[0]);
        this.CODE = Integer.parseInt(strRecord[1]);
        this.NAME = strRecord[2];
    }

    public int getFoodId() {
        return FOOD_ID;
    }
}

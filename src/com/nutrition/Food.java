package com.nutrition;

import com.nutrition.model.Measurement;
import com.nutrition.model.Nutrient;
import com.nutrition.model.NutrientValue;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Food {
    private static final String PATH_CONVERSION_FACTOR = "src/NutritionDB/CONVERSION FACTOR.csv";

    private final int FOOD_ID;
    private final String NAME;
    private final MasterNutrition MASTER_NUTRITION;

    private final List<NutrientValue> nutrientValList;
    private final List<Measurement> measurementList;

    public Food(String[] strRecord, MasterNutrition masterNutrition) {
        this.FOOD_ID = Integer.parseInt(strRecord[0]);
        this.NAME = strRecord[4];
        this.MASTER_NUTRITION = masterNutrition;

        this.nutrientValList = new ArrayList<>();
        this.measurementList = new ArrayList<>();
    }

    public void initializeLists() {
        this.LoadMeasurements();
        this.LoadNutrientValues();
    }

    private void LoadMeasurements() {
        FileManager fman = new FileManager(PATH_CONVERSION_FACTOR);
        List<String[]> fdMeasurements = fman.ReadCSV(PATH_CONVERSION_FACTOR, 0, String.valueOf(this.FOOD_ID));
        for (String[] fdMeasurement : fdMeasurements) {

            int measurementId = Integer.parseInt(fdMeasurement[1]);
            double convertFactor = Double.parseDouble(fdMeasurement[2]);
            String measurementName = MASTER_NUTRITION.GetFoodMeasurements(measurementId).getName();

            measurementList.add(new Measurement(measurementId, measurementName, convertFactor));
        }
    }

    private void LoadNutrientValues() {
        String strPath = Paths.get("src/NutritionDB/NUTRIENT AMOUNT.csv").toString();
        FileManager fman = new FileManager(strPath);
        List<String[]> NutrientList = fman.ReadCSV(strPath, 0, String.valueOf(this.FOOD_ID));
        for (String[] nutrients : NutrientList) {
            // create a nutrient value record with every line of values
            NutrientValue nutrientVal = new NutrientValue(nutrients);

            // fetch the nutrient name of the nutrient from the nutrition object
            Nutrient nutrientName = MASTER_NUTRITION.GetNutrient(nutrientVal.getId());

            nutrientVal.setName(nutrientName.getName());
            nutrientVal.setPortion("100g");
            nutrientVal.setUnit(nutrientName.getUnit());
            // add it to the list of nutrients of the food
            this.nutrientValList.add(nutrientVal);
        }
    }

    public int getFoodId() {
        return FOOD_ID;
    }

    public String getName() {
        return NAME;
    }

    public NutrientValue getNutrientVal(int i) {
        return nutrientValList.get(i);
    }

    public Measurement getMeasurement(int i) {
        return measurementList.get(i);
    }

    public int getMeasurementListSize() {
        return measurementList.size();
    }

    public int getNutrientValListSize() {
        return nutrientValList.size();
    }
}
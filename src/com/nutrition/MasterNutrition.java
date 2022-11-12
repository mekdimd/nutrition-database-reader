package com.nutrition;

import com.nutrition.model.Measurement;
import com.nutrition.model.Nutrient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MasterNutrition {
    private static final String PATH_MEASUREMENTS = "src/NutritionDB/MEASURE NAME.csv";
    private static final String PATH_FOOD = "src/NutritionDB/FOOD NAME.csv";
    private static final String PATH_FOOD_GROUP = "src/NutritionDB/FOOD GROUP.csv";
    private static final String PATH_NUTRIENT_NAME = "src/NutritionDB/NUTRIENT NAME.csv";

    private final List<Food> foodList;
    private final List<FoodGroup> foodGroupList;
    private final List<Nutrient> nutrientList;
    private final List<Measurement> measurementsList;

    public MasterNutrition() {
        // Initialize Lists
        foodList = new ArrayList<>();
        foodGroupList = new ArrayList<>();
        nutrientList = new ArrayList<>();
        measurementsList = new ArrayList<>();

        LoadFood();
        LoadFoodGroup();
        LoadNutrientName();
        LoadMeasurement();
    }

    public Measurement GetFoodMeasurements(int intId) {
        for (Measurement ms : measurementsList) {
            if (ms.getId() == intId) {
                return ms;
            }
        }
        return null;
    }

    public List<Food> getListFoods(String foodName) {
        List<Food> SearchResults = new ArrayList<>();
        for (Food fd : foodList) {
            if (fd.getName().toLowerCase().trim().contains(foodName.toLowerCase().trim())) {
                SearchResults.add(fd);
            }
        }
        return SearchResults;
    }

    public Nutrient GetNutrient(int intNutrientId) {
        for (Nutrient nutrient : nutrientList) {
            if (nutrient.getID() == intNutrientId) {
                return nutrient;
            }
        }
        return null;
    }

    public Food GetFood(int intFodId) {
        for (Food fd : this.foodList) {
            if (fd.getFoodId() == intFodId) {
                fd.initializeLists();
                return fd;
            }
        }
        return null;
    }

    public FoodGroup GetFoodGroup(int intFoodGroupId) {
        for (FoodGroup group : this.foodGroupList) {
            if (group.getFoodId() == intFoodGroupId) {
                return group;
            }
        }
        return null;
    }

    private void LoadFood() {
        FileManager fman = new FileManager(PATH_FOOD);
        try {
            fman.ReadCSV();

            // Start reading at 1 to avoid reading CSV Header
            for (int i = 1; i < fman.fileContent.size(); i++) {
                Food fd = new Food(fman.fileContent.get(i), this);
                this.foodList.add(fd);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LoadFoodGroup() {
        FileManager fman = new FileManager(PATH_FOOD_GROUP);
        try {
            fman.ReadCSV();

            // Start reading at 1 to avoid reading CSV Header
            for (int i = 1; i < fman.fileContent.size(); i++) {
                FoodGroup fg = new FoodGroup(fman.fileContent.get(i));
                this.foodGroupList.add(fg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LoadNutrientName() {
        FileManager fman = new FileManager(PATH_NUTRIENT_NAME);
        try {
            fman.ReadCSV();

            // Start reading at 1 to avoid reading CSV Header
            for (int i = 1; i < fman.fileContent.size(); i++) {
                Nutrient nt = new Nutrient(fman.fileContent.get(i));
                this.nutrientList.add(nt);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LoadMeasurement() {
        FileManager fman = new FileManager(PATH_MEASUREMENTS);
        try {
            fman.ReadCSV();

            // Start reading at 1 to avoid reading CSV Header
            for (int i = 1; i < fman.fileContent.size(); i++) {
                Measurement msr = new Measurement(fman.fileContent.get(i));
                this.measurementsList.add(msr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
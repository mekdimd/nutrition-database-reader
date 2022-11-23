package com.nutrition;

import com.nutrition.model.NutrientValue;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final byte MAX_NUM_ATTRIBUTES_PER_PAGE = 15;
    private static final String DASH_LINE = "-------------------------------------------------------------------------------";
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        MasterNutrition masterNutrition = new MasterNutrition();

        boolean userContinue = true;
        String userFoodSearchQuery;
        while (userContinue) {

            // Prompt for the user's food selection
            System.out.print("Search for any food item: ");
            userFoodSearchQuery = getStringInput();
            List<Food> searchResults = masterNutrition.getListFoods(userFoodSearchQuery);      // find the food item from the database

            // More than one result that matches food
            if (searchResults.size() > 1) {
                // show the list of food items found
                System.out.println("The following results were found.\n" + DASH_LINE);

                // Print foods
                for (int i = 0; i < searchResults.size(); i++) {
                    System.out.println((i + 1) + ") " + searchResults.get(i).getName());
                }
                System.out.print("\n");

                // Get the user's food selection
                int selectedFoodId = getIntInputRange(1, searchResults.size()) - 1;

                Food fd = searchResults.get(selectedFoodId);
                fd.initializeLists();
                ShowNutrients(fd, MAX_NUM_ATTRIBUTES_PER_PAGE);

            } else {
                System.out.println(DASH_LINE + "\nThe search for " + userFoodSearchQuery + " produced no results");
            }

            userContinue = getUserContinue();
        }

        scan.close();
    }

    private static boolean getUserContinue() {
        System.out.println(DASH_LINE);
        // Prompt user to continue
        String userExit = getUserContinue(new String[]{"y", "n"});
        boolean userContinue = userExit.equalsIgnoreCase("y");
        System.out.println(DASH_LINE);

        return userContinue;
    }

    // Show all nutrients for a single food
    public static void ShowAllNutrients(Food fd) {
        ShowNutrients(fd, fd.getNutrientValListSize());
    }

    // Show nutrients for a single food item
    public static void ShowNutrients(Food fd, int maxNumAttributeResults) {
        System.out.println(DASH_LINE
                + "\nNutritional Information for " + fd.getName() + "\n"
                + DASH_LINE);

        for (int i = 0; i < fd.getNutrientValListSize(); i++) {
            NutrientValue currentNutrient = fd.getNutrientVal(i);
            System.out.println(
                    currentNutrient.getName() + " = " +
                            currentNutrient.getValue() + currentNutrient.getUnit() +
                            " per " + currentNutrient.getPortion());

            // Limits number of results displayed
            if (i > maxNumAttributeResults) {
                return;
            }
        }
    }

    // Prompt the user to enter an input given a list of valid inputs
    private static String getUserContinue(String[] validInputs) {
        System.out.print("Do you want to continue? (y/n): ");
        String userInput = getStringInput().trim();

        boolean isValidInput = false;

        // Update valid input
        for (String nextValidInput : validInputs) {
            if (userInput.equalsIgnoreCase(nextValidInput)) {
                isValidInput = true;
                break;
            }
        }

        while (!isValidInput) {
            System.out.print("\tInvalid Input! Do you want to continue? (y/n): ");
            userInput = getStringInput().trim();

            // Update valid input
            for (String nextValidInput : validInputs) {
                if (userInput.equalsIgnoreCase(nextValidInput)) {
                    isValidInput = true;
                    break;
                }
            }
        }

        return userInput;
    }

    // Prompt the use to enter a NON-EMPTY string with no empty spaces
    private static String getStringInput() {
        String result = scan.nextLine();

        while (result.isBlank()) {
            System.out.print("\tField can not be empty! Try again: ");
            result = scan.nextLine();
        }
        return result;
    }

    // Prompt a user to enter a valid integer
    private static int getIntInput() {
        while (!scan.hasNextInt()) {
            System.out.print("\tNot an integer! Try again: ");
            scan.next();
            scan.nextLine();
        }

        int result = scan.nextInt();
        scan.nextLine();
        return result;
    }

    // Prompt a user to enter an integer within a specified range
    private static int getIntInputRange(int minOption, int maxOption) {
        System.out.print("Pick the number corresponding to your selection" + ": ");
        int userInput = getIntInput();

        // Validate input
        while (userInput < minOption || userInput > maxOption) {
            System.out.print("\tOut of range! Pick the number corresponding to your selection" + " (" + minOption + "-" + maxOption + "): ");
            userInput = getIntInput();
        }

        return userInput;
    }
}

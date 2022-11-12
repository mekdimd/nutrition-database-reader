package com.nutrition;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public final String filePath;
    List<String[]> fileContent = new ArrayList<>();

    public FileManager(String strPath) {
        this.filePath = strPath;
    }

    public void ReadCSV() throws IOException {
        ReadCSV(filePath);
    }

    public void ReadCSV(String strCSVFilePath) {
        // Create an object of filereader
        // class with CSV file as a parameter.
        try {
            FileReader filereader = new FileReader(strCSVFilePath);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);

            try {
                fileContent = csvReader.readAll();
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> ReadCSV(String strCSVFilePath, Integer iColNum, String iColValue) {
        List<String[]> returnList = new ArrayList<>();

        // Create an object of file-reader
        // class with CSV file as a parameter.
        boolean bFound = false;
        try {
            FileReader filereader = new FileReader(strCSVFilePath);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);

            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                // if there is a match then add to the list of values to return
                if (nextLine[iColNum].equals(iColValue)) {
                    returnList.add(nextLine);
                    bFound = true;
                } else if (bFound) {
                    return returnList;
                }
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return returnList;
    }
}
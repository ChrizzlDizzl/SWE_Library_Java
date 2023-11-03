package com.example.sweLibrary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PrepareData {

    private static String[] cutterInput(String line) {
        String[] cuttedLine;
        int counter = 0;
        cuttedLine = line.split(","); //split based on ";"
        for (String element : cuttedLine) {
            cuttedLine[counter] = element.replace("\"", ""); //removes unneeded character
            counter++;
        }
        return cuttedLine;
    }

    private static int identifyInputs(String line) { //identify each character set, giving id as return, method onlyfor better structure
        int taskID = -1;
        if (line.contains("Person")) {
            //create object
            if (line.contains("Kunde")) {
                taskID = 0;
            } else if (line.contains("Mitarbeiter")) {
                taskID = 1;
            }
        }
        if (line.contains("Media")) {
            if (line.contains("DVD") || line.contains("Landkarte")) {
                taskID = 3;
            } else {
                taskID = 2;
            }
        }
        return taskID;
    }

    public static void dataReader(String file) { //reads the data input and safes it as the correct object
        String line;
        String[] cuttedLine;
        int taskID = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Data: ")) {
                    taskID = identifyInputs(line);

                } else {

                    switch (taskID) {
                        case 0 -> {
                            cuttedLine = cutterInput(line);
                            ObjectsDB.createCustomer(cuttedLine[0], cuttedLine[1], cuttedLine[2]); //personID, personPW, personType
                        }
                        case 1 -> {
                            cuttedLine = cutterInput(line);
                            ObjectsDB.createEmployee(cuttedLine[0], cuttedLine[1], cuttedLine[2]); //personID, personPW, personType
                        }
                        case 2 -> {
                            cuttedLine = cutterInput(line);
                            ObjectsDB.createMedia(cuttedLine[0], cuttedLine[1], cuttedLine[2], cuttedLine[3], cuttedLine[4]); //mediaCategory, mediaID, mediaName, date, publisher
                        }
                        case 3 -> {
                            cuttedLine = cutterInput(line);
                            ObjectsDB.createMedia(cuttedLine[0], cuttedLine[1], cuttedLine[2], cuttedLine[3], "NaN"); //mediaCategory, mediaID, mediaName, date, publisher=NaN
                        }
                        case -1 -> {
                            System.out.println("Error reading input!");
                        }
                    }

                }
            }
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void returnDateReader(String file) { //reads the data input and safes it as the correct object
        String line;
        String[] cuttedLine;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Data: ")) {
                    //skip line
                } else {
                    cuttedLine = cutterInput(line);
                    ObjectsDB.loadRentList(cuttedLine[0], cuttedLine[1], cuttedLine[2]); //customerID, mediaID, returnDate
                }
            }
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
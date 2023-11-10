package com.example.swe_library;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class CreateBackup {
    public static void createBackup(String filePath) {
        String csvData = "";
        String csvDataBook = "Data: \"Media\",\"Buch\"\n";
        String csvDataDVD = "Data: \"Media\",\"DVD\"\n";
        String csvDataMap = "Data: \"Media\",\"Landkarte\"\n";
        String csvDataNewspaper = "Data: \"Media\",\"Zeitschrift\"\n";

        //Backup employee
        String csvDataEmployee = "Data: \"Person\",\"Mitarbeiter\"\n";
        Set<Integer> personKeys = ObjectsDB.employeeMap.keySet();

        for (int key : personKeys) {
            Employee employee = ObjectsDB.employeeMap.get(key);
            csvDataEmployee += employee.toCsvString();
        }

        //Backup customer
        String csvDataCustomer = "Data: \"Person\",\"Kunde\"\n";
        personKeys = ObjectsDB.customerMap.keySet();

        for (int key : personKeys) {
            Customer customer = ObjectsDB.customerMap.get(key);
            csvDataCustomer += customer.toCsvString();
        }

        //Backup media
        Set<String> keys = ObjectsDB.mediaMap.keySet();

        for (String key : keys) {
            Media media = ObjectsDB.mediaMap.get(key);
            if (media.mediaCategory == MediaCategory.valueOf("Buch"))
                csvDataBook += media.toCsvString();
            if (media.mediaCategory == MediaCategory.valueOf("DVD"))
                csvDataDVD += media.toCsvString();
            if (media.mediaCategory == MediaCategory.valueOf("Zeitschrift"))
                csvDataNewspaper += media.toCsvString();
            if (media.mediaCategory == MediaCategory.valueOf("Landkarte"))
                csvDataMap += media.toCsvString();
        }
        csvData = csvDataEmployee + csvDataCustomer + csvDataBook + csvDataDVD + csvDataNewspaper + csvDataMap;
        csvData = csvData.replaceAll("\\n$", "");

        File csvFile = new File(filePath);

        // Write CSV data to the file, overwriting if it already exists
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, false)) /* "false" for overwriting */) {
            writer.write(csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void backupReturnDates(String filePath) {
        Customer.rentedMedia = new HashMap<>();
        //Backup returnDates
        Set<Integer> returnKeys = Customer.rentMap.keySet();
        Set<String> mediaToReturnKeys;
        StringBuilder csvString = new StringBuilder();

        for (int key : returnKeys) {
            Customer.rentedMedia = Customer.rentMap.get(key);
            mediaToReturnKeys = Customer.rentedMedia.keySet();
            for (String keyReturnMedia : mediaToReturnKeys) {
                csvString.append("\"");
                csvString.append(key).append("\",\"");
                csvString.append(keyReturnMedia).append("\",\"");
                csvString.append(Customer.rentedMedia.get(keyReturnMedia)).append("\"\n");
            }
        }
        String csvReturnDates = csvString.toString();
        csvReturnDates = csvReturnDates.replaceAll("\\n$", "");

        File csvFile = new File(filePath);

        // Write CSV data to the file, overwriting if it already exists
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, false)) /* "false" for overwriting */) {
            writer.write(csvReturnDates);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}

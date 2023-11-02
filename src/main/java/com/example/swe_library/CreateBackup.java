package com.example.swe_library;

import java.util.*;

public class CreateBackup {
    public static void createBackup () {
        String csvData="";

        //Backup employee
        csvData += "Data: \"Person\", \"Mitarbeiter\"\n";
        Set<Integer> personKeys  = ObjectsDB.employeeMap.keySet();

        for (int key : personKeys) {
            Employee employee = ObjectsDB.employeeMap.get(key);
            csvData += employee.toCsvString();
        }

        //Backup customer
        csvData += "Data: \"Person\", \"Kunde\"\n";
        personKeys = ObjectsDB.customerMap.keySet();

        for (int key : personKeys) {
            Customer customer = ObjectsDB.customerMap.get(key);
            csvData += customer.toCsvString();
        }

        //Backup media
        csvData += "Data: \"Media\", \"Buch\"\n";
        Set<String> keys = ObjectsDB.mediaMap.keySet();

        for (String key : keys) {
            Media media = ObjectsDB.mediaMap.get(key);
            csvData += media.toCsvString();
        }
        System.out.println(csvData);
    }
}

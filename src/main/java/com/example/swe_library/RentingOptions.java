package com.example.swe_library;

import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

public class RentingOptions {

    public static void returnMedia(int customerID, String mediaID) throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (!ObjectsDB.customerMap.containsKey(customerID)) {
            System.out.println("Error, customer doesn't exists!");
            throw new Exception("Error!");
        }
        if (!ObjectsDB.mediaMap.containsKey(mediaID)) {
            System.out.println("Error, Media doesn't exists!");
            throw new Exception("Error!");
        }

        if (Customer.rentMap.containsKey(customerID)) {
            Customer.rentedMedia = new HashMap<>();
            HashMap rentedMedia = Customer.rentMap.get(customerID);
            if (rentedMedia.containsKey(mediaID)) {
                rentedMedia = Customer.rentMap.get(customerID);

                LocalDate dateNow = LocalDate.now();
                LocalDate dateReturn = (LocalDate) rentedMedia.get(mediaID);
                if (dateNow.isAfter(dateReturn)) {
                    Period days = dateReturn.until(dateNow);
                    System.out.println("Return was " + days.getDays() + " days to late");
                    alert.setTitle("Achtung!");
                    alert.setHeaderText("Medium wurde zu spät zurückgegeben!");
                    alert.showAndWait();
                }
                rentedMedia.remove(mediaID);
                alert.setTitle("Succeeded!");
                alert.setHeaderText("Rückgabe war erfolgreich!");
                alert.showAndWait();
                System.out.println("Return successfully");
                if (Customer.rentMap.get(customerID) == null) {
                    Customer.rentMap.remove(customerID);
                } else {
                    Customer.rentMap.put(customerID, rentedMedia);
                }
            } else {
                alert.setTitle("Error!");
                System.out.println("Kunde hat dieses Medium nicht ausgeliehen");
                throw new Exception("Error!");
            }
        } else {
            System.out.println("Customer doesn't have rent any books!");
            throw new Exception("Error!");
        }
        CreateBackup.backupReturnDates("returnDates.csv");
    }

    public static void rentMedia(int customerID, String mediaID) throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (!ObjectsDB.customerMap.containsKey(customerID)) {
            System.out.println("Error, customer doesn't exists!");
            throw new Exception("Error!");
        }
        if (!ObjectsDB.mediaMap.containsKey(mediaID)) {
            System.out.println("Error, Media doesn't exists!");
            throw new Exception("Error!");
        }
        for (HashMap<Integer, HashMap> innerMap : Customer.rentMap.values()) {
            // Check if mediaID is present in the inner HashMap
            if (innerMap.containsKey(mediaID)) {
                // Media is already rented, throw an exception or handle the error
                throw new Exception("Error!");
            }
        }
        Customer.rentedMedia = new HashMap<>();
        if (Customer.rentMap.containsKey(customerID)) {
            Customer.rentedMedia = Customer.rentMap.get(customerID);
        }
        LocalDate date = LocalDate.now();
        date = date.plusDays(14); //return date
        alert.setTitle("Succeded!");
        alert.setHeaderText("Ausleihe war erfolgreich!");
        alert.setContentText("Rückgabedatum: " + date);
        System.out.println("The date for return is: " + date);

        Customer.rentedMedia.put(mediaID, date);
        Customer.rentMap.put(customerID, Customer.rentedMedia);

        CreateBackup.backupReturnDates("returnDates.csv");
        alert.showAndWait();
    }
}

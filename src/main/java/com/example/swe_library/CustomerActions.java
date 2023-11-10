package com.example.swe_library;
import java.time.LocalDate;
import java.util.HashMap;


public class CustomerActions {
    public static void customerAccount(int userID) throws Exception {
        int counter = 0;
        String answer;

        if (!Customer.rentMap.containsKey(userID)) {
            System.out.println("This option is only for customers! \n");
            return;
        }

        HashMap<String, LocalDate> accountInformation = Customer.rentMap.get(userID);

        if (accountInformation == null || accountInformation.isEmpty()) {
            System.out.println("Customer has no media rent!");
            Main.options(userID);
            return;
        }

        for (String key : accountInformation.keySet()) {
            Media media = ObjectsDB.mediaMap.get(key);
            answer = "Search result " + counter + ": ";
            answer += "\nMediaCategory: " + media.mediaCategory;
            answer += "\nMediaID: " + media.id;
            answer += "\nMediaName: " + media.name;
            answer += "\nPublishDate: " + media.publishDate;
            answer += "\nPublisher: " + media.publisher;
            System.out.println(answer + "\n");
            System.out.println("Your Return-date is: " + accountInformation.get(key));
            System.out.println("\n");
            counter++;
        }
    }

}
package com.example.sweLibrary;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CustomerAccount {
    public static List<Media> accountList = new ArrayList<Media>();
    public static void customerAccount(int userID) throws Exception {
        accountList.clear();
        int counter = 0;
        String answer;

        if (!Customer.rentMap.containsKey(userID)) {
            System.out.println("This option is only for customers! \n");
            throw new Exception("Fail!");
        }

        HashMap<String, LocalDate> accountInformation = Customer.rentMap.get(userID);

        if (accountInformation == null || accountInformation.isEmpty()) {
            System.out.println("Customer has no media rent!");
            throw new Exception("Fail!");
        }

        for (String key : accountInformation.keySet()) {
            Media media = ObjectsDB.mediaMap.get(key);
            media.returnDate=accountInformation.get(key);
            accountList.add(media);
            answer = "Search result " + counter + ": ";
            answer += "\nMediaCategory: " + media.mediaCategory;
            answer += "\nMediaID: " + media.id;
            answer += "\nMediaName: " + media.name;
            answer += "\nPublishDate: " + media.publishDate;
            answer += "\nPublisher: " + media.publisher;
            System.out.println(answer + "\n");
            System.out.println("Your Return-date is: " + media.returnDate);
            System.out.println("\n");
            counter++;
        }
    }

}
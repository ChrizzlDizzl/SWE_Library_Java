package com.example.swe_library;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class RentOptions {
     static HashMap<String, LocalDate> rentedMedia = new HashMap<>();

    public static void rentOptions(int userID) {
        if(!ObjectsDB.employeeMap.containsKey(userID)) {
            System.out.println("Option only allowed as employee, please Log-In as employee!");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("CustomerID: ");
        int customerID = Integer.parseInt(scanner.nextLine());
        System.out.print("MediaID: ");
        String mediaID = scanner.nextLine();
        String acceptInput = " ";

        System.out.print("What do you want to do? (rent/return/exit): ");
        acceptInput = scanner.nextLine();
        if (acceptInput.equals("return")) {
            returnMedia(userID, customerID, mediaID);
        } else if (acceptInput.equals("rent")) {
            rentMedia(userID, customerID, mediaID);
        } else if (acceptInput.equals("exit")) {
            Main.options(userID);
        } else {
            rentOptions(userID);
        }
    }

    public static void returnMedia(int userID, int customerID, String mediaID) {
        if (!ObjectsDB.customerMap.containsKey(customerID)) {
            System.out.println("Error, customer doesn't exists!");
            rentOptions(userID);
        }
        if (!ObjectsDB.mediaMap.containsKey(mediaID)) {
            System.out.println("Error, Media doesn't exists!");
            rentOptions(userID);
        }

        if (Customer.rentMap.containsKey(customerID)) {
            rentedMedia = Customer.rentMap.get(customerID);
            if (rentedMedia.containsKey(mediaID)) {
                rentedMedia = Customer.rentMap.get(customerID);

                LocalDate dateNow = LocalDate.now();
                LocalDate dateReturn = rentedMedia.get(mediaID);
                if (dateNow.isAfter(dateReturn)) {
                    Period days = dateReturn.until(dateNow);
                    System.out.println("Return was " + days.getDays() + " days to late");
                }
                rentedMedia.remove(mediaID);
                System.out.println("Return successfully");

                if (rentedMedia.isEmpty()) {
                    Customer.rentMap.remove(customerID);
                }
            } else {
                System.out.println("Customer didn't rent this media!");
            }
        } else {
            System.out.println("Customer doesn't have rent any books!");
        }
    }

    public static void rentMedia(int userID, int customerID, String mediaID) {
        if (!ObjectsDB.customerMap.containsKey(customerID)) {
            System.out.println("Error, customer doesn't exists!");
            rentOptions(userID);
        }
        if (!ObjectsDB.mediaMap.containsKey(mediaID)) {
            System.out.println("Error, Media doesn't exists!");
            rentOptions(userID);
        }

        if (Customer.rentMap.containsKey(customerID)) {
            rentedMedia = Customer.rentMap.get(customerID);
            if (!rentedMedia.containsKey(mediaID)) {
                rentedMedia = Customer.rentMap.get(customerID);
                System.out.println(rentedMedia);
            } else {
                System.out.println("Costumer already has this media!");
                rentOptions(userID);
            }
        }

        LocalDate date = LocalDate.now();
        date = date.plusDays(14); //return date
        System.out.println("The date for return is: " + date);

        rentedMedia.put(mediaID, date);
        Customer.rentMap.put(customerID, rentedMedia);

        System.out.println("Rent successfully!");
    }
}

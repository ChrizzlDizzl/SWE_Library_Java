package com.example.swe_library;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

public class EmployeeActions {

    public static void rentOptions(int userID) throws Exception {
        if (!ObjectsDB.employeeMap.containsKey(userID)) {
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
            returnMedia(customerID, mediaID);
        } else if (acceptInput.equals("rent")) {
            rentMedia(customerID, mediaID);
        } else if (acceptInput.equals("exit")) {
            Main.options(userID);
        } else {
            rentOptions(userID);
        }
    }

    public static void returnMedia(int customerID, String mediaID) {
        if (!ObjectsDB.customerMap.containsKey(customerID)) {
            System.out.println("Error, customer doesn't exists!");
        }
        if (!ObjectsDB.mediaMap.containsKey(mediaID)) {
            System.out.println("Error, Media doesn't exists!");
        }

        if (Customer.rentMap.containsKey(customerID)) {
            HashMap rentedMedia = Customer.rentMap.get(customerID);
            if (rentedMedia.containsKey(mediaID)) {
                rentedMedia = Customer.rentMap.get(customerID);

                LocalDate dateNow = LocalDate.now();
                LocalDate dateReturn = (LocalDate) rentedMedia.get(mediaID);
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

    public static void rentMedia(int customerID, String mediaID) throws Exception{
        if (!ObjectsDB.customerMap.containsKey(customerID)) {
            throw new java.lang.Exception("Error, customer doesn't exists!");
        }
        if (!ObjectsDB.mediaMap.containsKey(mediaID)) {
            throw new java.lang.Exception("Error, Media doesn't exists!");
        }
        if (Customer.rentedMedia.containsKey(mediaID)) {
            throw new java.lang.Exception("Error, Media already rented!");
        }

        if (Customer.rentMap.containsKey(customerID)) {
            Customer.rentedMedia = Customer.rentMap.get(customerID);
            if (!Customer.rentedMedia.containsKey(mediaID)) {
                Customer.rentedMedia = Customer.rentMap.get(customerID);
                System.out.println(Customer.rentedMedia);
            } else {
                throw new java.lang.Exception("Costumer already has this media!");
            }
        }

        LocalDate date = LocalDate.now();
        date = date.plusDays(14); //return date
        System.out.println("The date for return is: " + date);

        Customer.rentedMedia.put(mediaID, date);
        Customer.rentMap.put(customerID, Customer.rentedMedia);

        CreateBackup.backupReturnDates();

        System.out.println("Rent successfully!");
    }

    public static void changeData(int userID) {
        if (!ObjectsDB.employeeMap.containsKey(userID)) {
            System.out.println("Option only allowed as employee, please Log-In as employee!");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the MediaID for the Media you want to change: ");
        String mediaID = scanner.nextLine();
        if (!ObjectsDB.mediaMap.containsKey(mediaID)) {
            System.out.println("Media ID doesn't exists");
            return;
        }

        System.out.println("Enter \"1\" if you want to delete this media: ");
        int delete = scanner.nextInt();
        if (delete == 1) {
            ObjectsDB.mediaMap.remove(mediaID);
            return;
        }

        Media media = ObjectsDB.mediaMap.get(mediaID);
        String id;
        String mediaCategory;
        String name;
        String publishDate;
        String publisher;

        System.out.println("Print in the Data you want to change. Press enter to skip a category. Insert * to delete the information of the current Attribute (not possible with MediaID)");
        scanner.nextLine(); //consume newLineEntry
        System.out.println("MediaID (Has to have values!): ");
        id = scanner.nextLine();
        System.out.println("MediaCategory: ");
        mediaCategory = scanner.nextLine();
        System.out.println("MediaName: ");
        name = scanner.nextLine();
        System.out.println("PublishDate: ");
        publishDate = scanner.nextLine();
        System.out.println("Publisher: ");
        publisher = scanner.nextLine();

        if (!id.equals("")) {
            media.id = id;
        }

        if (mediaCategory.equals("*")) {
            media.mediaCategory = null;
        } else if (!mediaCategory.equals("")) {
            try {
                media.mediaCategory = MediaCategory.valueOf(mediaCategory);
            } catch (IllegalArgumentException e) {
                System.out.println("Illegal input for MediaCategory, try again");
                System.out.println("MediaCategory (Buch/DVD/Zeitschrift/Landkarte): ");
                mediaCategory = scanner.nextLine();
            }
        }

        if (name.equals("*")) {
            name = null;
        } else if (!name.equals("")) {
            media.name = name;
        }

        if (publishDate.equals("*")) {
            publishDate = null;
        } else if (!publishDate.equals("")) {
            media.publishDate = LocalDate.parse(publishDate);
        }

        if (publisher.equals("*")) {
            publisher = null;
        } else if (!publisher.equals("")) {
            try {
                media.publishDate = LocalDate.parse(publishDate);
            } catch (DateTimeParseException e) {
                System.out.println("Illegal input for date, try again");
                System.out.println("PublishDate (yyyy-mm-dd): ");
                publishDate = scanner.nextLine();
            }
        }
    }

    //Absolet
    public static void newData(int userID) {
        if (!ObjectsDB.employeeMap.containsKey(userID)) {
            System.out.println("Option only allowed as employee, please Log-In as employee!");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        String id;
        String mediaCategory;
        String name;
        String publishDate;
        String publisher;

        System.out.println("Insert the Media Data. Press enter to skip a category leave it empty\n");
        System.out.println("MediaID: ");
        id = scanner.nextLine();
        System.out.println("MediaCategory (Buch/DVD/Zeitschrift/Landkarte): ");
        mediaCategory = scanner.nextLine();
        System.out.println("MediaName: ");
        name = scanner.nextLine();
        System.out.println("PublishDate: ");
        publishDate = scanner.nextLine();
        System.out.println("Publisher: ");
        publisher = scanner.nextLine();


        Media media = new Media();
        media.id = id;
        try {
            media.mediaCategory = MediaCategory.valueOf(mediaCategory);
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal input for MediaCategory, try again");
            System.out.println("MediaCategory (Buch/DVD/Zeitschrift/Landkarte): ");
            mediaCategory = scanner.nextLine();
        }
        media.name = name;
        try {
            media.publishDate = LocalDate.parse(publishDate);
        } catch (DateTimeParseException e) {
            System.out.println("Illegal input for date, try again");
            System.out.println("PublishDate (yyyy-mm-dd): ");
            publishDate = scanner.nextLine();
        }
        media.publisher = publisher;

        ObjectsDB.mediaMap.put(media.id, media);
    }
}
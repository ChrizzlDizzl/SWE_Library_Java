package com.example.sweLibrary.noLongerNeeded;

import com.example.sweLibrary.Media;
import com.example.sweLibrary.MediaCategory;
import com.example.sweLibrary.ObjectsDB;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

//only for test used
public class ChangeData {
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

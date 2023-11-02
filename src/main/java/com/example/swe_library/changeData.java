package com.example.swe_library;

import java.time.LocalDate;
import java.util.Scanner;

public class ChangeData {
    public static void changeData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the MediaID for the Media you want to change: ");
        String mediaID = scanner.nextLine();
        if(!ObjectsDB.customerMap.containsKey(mediaID)) {
            System.out.println("Media ID doesn't exists");
            return;
        }

        System.out.println("Enter \"1\" if you want to delete this media: ");
        int delete = scanner.nextInt();
        if (delete==1) {
            ObjectsDB.mediaMap.remove(mediaID);
            return;
        }

        Media media = ObjectsDB.mediaMap.get(mediaID);
        String id = media.id;
        String mediaCategory = String.valueOf(media.mediaCategory);
        String name = media.name;
        String publishDate = String.valueOf(media.publishDate);
        String publisher = media.publisher;

        System.out.println("Print in the Data you want to change. Press enter to skip a category. Insert * to delete the information of the current Attribute");
        scanner.nextLine();
        System.out.println("MediaID: ");
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
        } else if (mediaID.equals("*")) {
            media.id = null;
        }

        if (!mediaCategory.equals("")) {
            media.mediaCategory = MediaCategory.valueOf(mediaCategory);
        } else if (mediaCategory.equals("*")) {
            mediaCategory = null;
        }

        if (!name.equals("")) {
            media.name = name;
        } else if (name.equals("*")) {
            name = null;
        }

        if (!publishDate.equals("")) {
            media.publishDate = LocalDate.parse(publishDate);
        } else if (publishDate.equals("*")) {
            publishDate = null;
        }

        if (!publisher.equals("")) {
            media.publisher = publisher;
        } else if (publisher.equals("*")) {
            publisher = null;
        }
    }
}


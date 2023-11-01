package com.example.swe_library;
import java.util.*;

public class InteractionsMedia {
    public static void searchItem() {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        String answer;
        System.out.println("Search: ");
        String input = scanner.nextLine();
        input = input.toLowerCase();
        for (Map.Entry<String, Media> entry : ObjectsDB.mediaMap.entrySet()) {
            Media mediaToCompare = entry.getValue();
            String compare = mediaToCompare.name;
            compare = compare.toLowerCase();
            if (compare.contains(input)) {
                Media media = ObjectsDB.mediaMap.get(entry.getKey());
                answer = "Searchresult " + counter + ": ";
                answer += "\nMediaCategory: " + media.mediaCategory;
                answer += "\nMediaID: " + media.id;
                answer += "\nMediaName: " + media.name;
                answer += "\nPublishDate: " + media.publishDate;
                answer += "\nPublisher: " + media.publisher;
                System.out.println(answer + "\n");
                counter++;
            }
        }
        if (counter == 0) {
            answer = "Product don't exists in this storage";
            System.out.println(answer);
        }
    }

    public static void showInventory() {
        // Define a custom comparator to sort Media objects first by mediaCategory and then by name
        Comparator<Map.Entry<String, Media>> mediaCategoryNameComparator = new Comparator<Map.Entry<String, Media>>() {
            @Override
            public int compare(Map.Entry<String, Media> entry1, Map.Entry<String, Media> entry2) {
                String mediaCategory1 = String.valueOf(entry1.getValue().mediaCategory);
                String mediaCategory2 = String.valueOf(entry2.getValue().mediaCategory);
                int mediaCategoryComparison = mediaCategory1.compareTo(mediaCategory2);

                if (mediaCategoryComparison != 0) {
                    return mediaCategoryComparison; // Sort by mediaCategory
                } else {
                    // If mediaCategories are the same, compare by name
                    String name1 = entry1.getValue().name;
                    String name2 = entry2.getValue().name;
                    return name1.compareTo(name2);
                }
            }
        };

        // Convert the HashMap entries into a list for sorting
        List<Map.Entry<String, Media>> entryList = new ArrayList<>(ObjectsDB.mediaMap.entrySet());

        // Sort the list using the custom comparator
        Collections.sort(entryList, mediaCategoryNameComparator);

        int counter = 0;

        for (Map.Entry<String, Media> entry : entryList) {
            Media media = entry.getValue();
            String answer = "Item " + counter + ": ";
            answer += "\nMediaCategory: " + media.mediaCategory;
            answer += "\nMediaID: " + media.id;
            answer += "\nMediaName: " + media.name;
            answer += "\nPublishDate: " + media.publishDate;
            answer += "\nPublisher: " + media.publisher;
            System.out.println(answer + "\n");
            counter++;
        }

        if (counter == 0) {
            String answer = "Product doesn't exist in this storage";
            System.out.println(answer);
        }
    }
}

package com.example.sweLibrary;

import java.util.*;

public class InteractionsMedia {
    //Statische Medienliste von letzter Suchanfrage
    public static List<Media> searchList = new ArrayList<Media>();
    public static void searchItem(String item) {
        searchList.clear();
        int counter = 0;
        String answer;
        List<Media> searchResults = new ArrayList<>();

        System.out.println("Search: ");
        String input = item.toLowerCase();

        // Step 1: Collect matching media items in the searchResults list
        for (Map.Entry<String, Media> entry : ObjectsDB.mediaMap.entrySet()) {
            Media media = entry.getValue();

            // Check if the user's input exists in any field of the media item
            if ((media.mediaCategory != null && media.mediaCategory.toString().toLowerCase().contains(input)) ||
                    media.id.toLowerCase().contains(input) ||
                    media.name.toLowerCase().contains(input) ||
                    media.publishDate.toString().toLowerCase().contains(input) ||
                    media.publisher.toLowerCase().contains(input)) {
                searchResults.add(media);
            }
        }

        // Step 2: Sort the search results
        Collections.sort(searchResults, new Comparator<Media>() {
            @Override
            public int compare(Media media1, Media media2) {
                // First, sort by mediaCategory
                int categoryComparison = media1.mediaCategory.compareTo(media2.mediaCategory);
                if (categoryComparison != 0) {
                    return categoryComparison;
                }

                // If mediaCategory is the same, sort by name
                return media1.name.compareTo(media2.name);
            }

            @Override
            public Comparator<Media> reversed() {
                return Comparator.super.reversed();
            }
        });
        String returnDate;
        // Step 3: Print the sorted results
        for (Media media : searchResults) {
            searchList.add(media);
            answer = "Searchresult " + counter + ": ";
            answer += "\nMediaCategory: " + media.mediaCategory;
            answer += "\nMediaID: " + media.id;
            answer += "\nMediaName: " + media.name;
            answer += "\nPublishDate: " + media.publishDate;
            answer += "\nPublisher: " + media.publisher;
            answer += "\nAvailability: " + media.availability;
            System.out.println(answer + "\n");
            counter++;
        }

        if (counter == 0) {
            answer = "Product doesn't exist in this storage";
            System.out.println(answer);
        }
    }


    public static void showInventory() {
        searchList.clear();
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
            searchList.add(media);
            String answer = "Item " + counter + ": ";
            answer += "\nMediaCategory: " + media.mediaCategory;
            answer += "\nMediaID: " + media.id;
            answer += "\nMediaName: " + media.name;
            answer += "\nPublishDate: " + media.publishDate;
            answer += "\nPublisher: " + media.publisher;
            answer += "\nAvailability: " + media.availability;
            System.out.println(answer + "\n");
            counter++;
        }

        if (counter == 0) {
            String answer = "Product doesn't exist in this storage";
            System.out.println(answer);
        }
    }
}

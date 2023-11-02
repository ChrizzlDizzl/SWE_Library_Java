package com.example.swe_library;

import java.time.LocalDate;

public class Media {
    String id;
    MediaCategory mediaCategory;
    String name;
    LocalDate publishDate;
    String publisher;
    LocalDate returnDate;

    public String toCsvString() {
        StringBuilder csvString = new StringBuilder();
        csvString.append("\"");
        csvString.append(mediaCategory).append("\",\"");
        csvString.append(id).append("\",\"");
        csvString.append(name).append("\",\"");
        csvString.append(publishDate).append("\"");
        if (!publisher.equals("NaN")) {
            csvString.append(",\"").append(publisher).append("\"\n");
        } else {
            csvString.append("");
        }

        return csvString.toString();
    }
}


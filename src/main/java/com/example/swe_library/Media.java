package com.example.swe_library;

import java.time.LocalDate;

public class Media {
    public String id;
    public MediaCategory mediaCategory;
    public String name;
    public LocalDate publishDate;
    public String publisher;
    public Boolean availability;

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
            csvString.append("\n");
        }

        return csvString.toString();
    }
}


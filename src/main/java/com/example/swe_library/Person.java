package com.example.swe_library;
public class Person {
    public String password;
    public PersonType personType;
    public int id;

    public String toCsvString() {
        StringBuilder csvString = new StringBuilder();
        csvString.append("\"");
        csvString.append(id).append("\",\"");
        csvString.append(password).append("\",\"");
        csvString.append(personType).append("\"\n");

        return csvString.toString();
    }
}

package com.example.sweLibrary;
import java.time.LocalDate;
import java.util.HashMap;


public class Customer extends Person{
    public static HashMap<Integer, HashMap> rentMap = new HashMap<>();
    static HashMap<String, LocalDate> rentedMedia = new HashMap<>();
}

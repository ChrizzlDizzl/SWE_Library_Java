package com.example.swe_library;
import java.time.LocalDate;
import java.util.HashMap;


public class Customer extends Person{
    public static HashMap<Integer, HashMap> rentMap = new HashMap<>();
    public static HashMap<String, LocalDate> rentedMedia = new HashMap<>();
}

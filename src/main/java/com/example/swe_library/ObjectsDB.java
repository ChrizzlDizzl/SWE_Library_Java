package com.example.swe_library;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ObjectsDB {
    public static HashMap<Integer, Customer> customerMap = new HashMap<>();
    public static HashMap<Integer, Employee> employeeMap = new HashMap<>();
    public static HashMap<String, Media> mediaMap = new HashMap<>();

    public static void createCustomer(String personReaderID, String personPW, String personType) {
        Integer id = Integer.parseInt(personReaderID);
        if (customerMap.containsValue(id)) {
            return;
        }
        Customer customer = new Customer();
        customer.id = id;
        customer.password = personPW;
        customer.personType = PersonType.valueOf(personType);
        customerMap.put(id, customer);
    }

    public static void createEmployee(String personReaderID, String personPW, String personType) {
        Integer id = Integer.parseInt(personReaderID);
        if (employeeMap.containsValue(id)) {
            return;
        }
        Employee employee = new Employee();
        employee.id = id;
        employee.password = personPW;
        employee.personType = PersonType.valueOf(personType);
        employeeMap.put(id, employee);
    }

    public static void createMedia(String mediaCategory, String mediaID, String mediaName, String publishDate, String publisher) {
        if (mediaMap.containsValue(mediaID)) {
            return;
        }
        Media media = new Media();
        media.id = mediaID;
        media.name = mediaName;
        media.mediaCategory = MediaCategory.valueOf(mediaCategory);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        media.publishDate = LocalDate.parse(publishDate, formatter);
        media.publisher = publisher;
        media.availability=true;
        mediaMap.put(mediaID, media);
    }

    public static void loadRentList (String customerID, String mediaID, String returnDate) {
        if (!ObjectsDB.customerMap.containsKey(Integer.valueOf(customerID))) {
            System.out.println("Error, customer doesn't exists!");
        }
        if(!ObjectsDB.mediaMap.containsKey(mediaID)) {
            System.out.println("Media doesn't exists!");
        }
        else {
            Media media = ObjectsDB.mediaMap.get(mediaID);
            Customer.rentedMedia.put(mediaID, LocalDate.parse(returnDate));
            Customer.rentMap.put(Integer.valueOf(customerID), Customer.rentedMedia);
            media.availability = false;
            ObjectsDB.mediaMap.put(mediaID, media);
        }
    }
}
package com.example.swe_library;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("LogIn");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        //Data to object
        String dataFile = "library.csv";
        PrepareData.dataReader(dataFile);
        String fileReturnDates = "returnDates.csv";
        PrepareData.returnDateReader(fileReturnDates);
        launch();

     //   int userID = Authentication.logIn();
       // options(userID);
    }
    public static void options(int userID) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1 SeeCostumerAccount \n2 ShowInventory \n3 SearchItem \n4 rentOptions \n5 logOut  \n6 ChangeData \n7 newData \n8 End \n\nMedia Enter your action: ");
        int action = Integer.parseInt(scanner.nextLine());

        switch (action) {
            case 1 -> {
                CustomerActions.customerAccount(userID);
            }
            case 2 -> {
                InteractionsMedia.showInventory();
            }
            case 3 -> {
                InteractionsMedia.searchItem();
            }
            case 4 -> {
                EmployeeActions.rentOptions(userID);
            }
            case 5 -> {
                userID = Authentication.logOut();
            }
            case 6 -> {
                EmployeeActions.changeData(userID);
            }
            case 7 -> {
                EmployeeActions.newData(userID);
            }
            case 8 -> {
                CreateBackup.createBackup();
                CreateBackup.backupReturnDates();
                System.exit(0);
            }
        }
        CreateBackup.createBackup();
        CreateBackup.backupReturnDates();
        options(userID);
    }

}
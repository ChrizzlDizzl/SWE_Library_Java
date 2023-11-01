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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        //Data to object
        String file = "library.csv";
        PrepareData.dataReader(file);
        int userID = Authentication.logIn(); //noch als Auswahl, spÃ¤ter nÃ¶tig
        options(userID);
    }
    public static void options(int userID) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1 SeeCostumerAccount \n2 ShowInventory \n3 SearchItem \n4 rentOptions \n5 logOut  \n\nMedia Enter your action: ");
        int action = Integer.parseInt(scanner.nextLine());

        switch (action) {
            case 1 -> {
                CostumerAccount.seeCostumerAccount(userID);
            }
            case 2 -> {
                InteractionsMedia.showInventory();
            }
            case 3 -> {
                InteractionsMedia.searchItem();
            }
            case 4 -> {
                RentOptions.rentOptions(userID);
            }
            case 5 -> {
                userID= Authentication.logOut();
            }
        }
        options(userID);
    }

}
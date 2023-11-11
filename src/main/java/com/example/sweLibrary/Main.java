package com.example.sweLibrary;

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
        stage.setFullScreen(true);
    }

    public static void main(String[] args) {

        //Data to object
        String dataFile = "library.csv";
        PrepareData.dataReader(dataFile);
        String fileReturnDates = "returnDates.csv";
        PrepareData.returnDateReader(fileReturnDates);
        launch();
    }
}
package com.example.swe_library;

import com.example.swe_library.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

public class SceneSwitcher {
    @FXML
    public static void switchScene(ActionEvent event, String fxml_Name) throws IOException{
        Parent homePage = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml_Name)));
        Scene homepageScene = new Scene(homePage);
        Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(homepageScene);
        appStage.show();
    }
}

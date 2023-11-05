package controller;

import com.example.swe_library.SceneSwitcher;
import javafx.event.ActionEvent;

import java.io.IOException;

public class HeaderEmployee {
    public void logOut(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "login.fxml");
    }
    public void homeEmployee(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "headerEmployee.fxml");
    }
    public void showInventoryEmployee(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "itemListEmployee.fxml");
    }
    public void searchItemEmployee(ActionEvent actionEvent) throws IOException {
        //erst eingabe noch für Medium ID und dann entsprechendes Buch anzeigen
        SceneSwitcher.switchScene(actionEvent, "showItemEmployee.fxml");
    }
    public void addMedia(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "addMedia.fxml");
    }
    public void rentingOptions(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "rentingOptions.fxml");
    }
}

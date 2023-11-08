package controller;

import com.example.swe_library.InteractionsMedia;
import com.example.swe_library.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HeaderEmployee {
    @FXML
    private TextField inputSearch;
    public void logOut(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "login.fxml");
    }
    public void homeEmployee(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "headerEmployee.fxml");
    }
    public void showInventoryEmployee(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "inventoryEmployee.fxml");
    }
    public void searchItemEmployee(ActionEvent actionEvent) throws IOException {
        String item = inputSearch.getText();
        //erst eingabe noch für Medium ID und dann entsprechendes Buch anzeigen
        SceneSwitcher.switchScene(actionEvent, "searchEmployee.fxml");
        InteractionsMedia.searchItem(item);
    }
    public void addMedia(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "addMediaEmployee.fxml");
    }
    public void rentingOptions(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "rentingOptions.fxml");
    }
}

package controller;

import com.example.sweLibrary.InteractionsMedia;
import com.example.sweLibrary.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HeaderEmployee {
    @FXML
    private TextField inputSearch;
    public void logOut(ActionEvent actionEvent) throws IOException {
        Authentication.logOut(actionEvent);
    }
    public void homeEmployee(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "headerEmployee.fxml");
    }
    @FXML
    public void showInventoryEmployee(ActionEvent actionEvent) throws IOException {
        InteractionsMedia.showInventory();
        SceneSwitcher.switchScene(actionEvent, "inventoryEmployee.fxml");
    }
    @FXML
    public void searchItemEmployee(ActionEvent actionEvent) throws IOException {
        String item = inputSearch.getText();
        //erst eingabe noch für Medium ID und dann entsprechendes Buch anzeigen
        InteractionsMedia.searchItem(item);
        SceneSwitcher.switchScene(actionEvent, "searchEmployee.fxml");

    }
    public void changeMedia(ActionEvent actionEvent) throws IOException {
        //Zuletzt gesuchtes Item wird gelöscht und zuständige Maske wird mit leeren Feldern geladen
        ChangeMediaEmployee.resetMedia();
        SceneSwitcher.switchScene(actionEvent, "changeMediaEmployee.fxml");


    }
    public void rentingOptions(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "rentingOptions.fxml");
    }
}

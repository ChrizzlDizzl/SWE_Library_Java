package controller;

import com.example.swe_library.SceneSwitcher;
import javafx.event.ActionEvent;

import java.io.IOException;

public class HeaderCustomer {
    public void logOut(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "login.fxml");
    }
    public void homeCustomer(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "headerCustomer.fxml");
    }
    public void showInventoryCustomer(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "showItemCustomer.fxml");
    }
    public void searchItemCustomer(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "showItemCustomer.fxml");
    }
}

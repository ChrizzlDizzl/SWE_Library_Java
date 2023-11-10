package controller;

import com.example.swe_library.Authentication;
import com.example.swe_library.CustomerActions;
import com.example.swe_library.InteractionsMedia;
import com.example.swe_library.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HeaderCustomer {
    @FXML
    private TextField inputSearch;
    public void logOut(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "login.fxml");
    }
    public void homeCustomer(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "headerCustomer.fxml");
    }
    public void searchItemCustomer(ActionEvent actionEvent) throws IOException {
        String item = inputSearch.getText();
        InteractionsMedia.searchItem(item);
        SceneSwitcher.switchScene(actionEvent, "searchCustomer.fxml");
    }
    public void showCustomerAccount(ActionEvent actionEvent) throws Exception {
        CustomerActions.customerAccount(Authentication.id);
        SceneSwitcher.switchScene(actionEvent, "customerAccount.fxml");
    }
}

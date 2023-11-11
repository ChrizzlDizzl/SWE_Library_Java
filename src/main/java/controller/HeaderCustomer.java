package controller;

import com.example.sweLibrary.CustomerAccount;
import com.example.sweLibrary.InteractionsMedia;
import com.example.sweLibrary.Media;
import com.example.sweLibrary.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HeaderCustomer {
    @FXML
    private TextField inputSearch;
    public void logOut(ActionEvent actionEvent) throws IOException {
        Authentication.logOut(actionEvent);
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
        CustomerAccount.customerAccount(Authentication.id);
        SceneSwitcher.switchScene(actionEvent, "customerAccount.fxml");
    }
}

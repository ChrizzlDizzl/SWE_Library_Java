package controller;

import com.example.swe_library.EmployeeActions;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class RentingOptions extends HeaderEmployee{
    @FXML
    private TextField mediumID;
    @FXML
    private TextField customerID;

    @FXML
    private void rentMedia(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            EmployeeActions.rentMedia(Integer.parseInt(customerID.getText()), mediumID.getText());
        } catch (Exception e) {
            System.out.println("Failed");
            alert.setTitle("Fail!");
            alert.setHeaderText("Etwas is schief gelaufen!");
            alert.showAndWait();
            mediumID.clear();
            customerID.clear();
        }

        // Show the alert and wait for the user response
        mediumID.clear();
        customerID.clear();
    }
    @FXML
    private void returnMedia(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            EmployeeActions.returnMedia(Integer.parseInt(customerID.getText()), mediumID.getText());
        } catch (Exception e) {
            alert.setTitle("Fail!");
            alert.setHeaderText("Etwas is schief gelaufen!");
            System.out.println("Failed");
            alert.showAndWait();
            mediumID.clear();
            customerID.clear();
        }
        mediumID.clear();
        customerID.clear();
    }
}

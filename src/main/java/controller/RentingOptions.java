package controller;

import com.example.swe_library.EmployeeActions;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RentingOptions extends HeaderEmployee{
    @FXML
    private TextField mediumID;
    @FXML
    private TextField customerID;

    @FXML
    private void rentMedia(){
        try {
            EmployeeActions.rentMedia(Integer.parseInt(customerID.getText()), mediumID.getText());
        } catch (Exception e) {
            System.out.println("Failed");
            mediumID.clear();
            customerID.clear();
        }
        mediumID.clear();
        customerID.clear();
    }
    @FXML
    private void returnMedia(){
        try {
            EmployeeActions.returnMedia(Integer.parseInt(customerID.getText()), mediumID.getText());
        } catch (Exception e) {
            System.out.println("Failed");
            mediumID.clear();
            customerID.clear();
        }
        mediumID.clear();
        customerID.clear();
    }
}

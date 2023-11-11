package controller;

import com.example.sweLibrary.Customer;
import com.example.sweLibrary.Employee;
import com.example.sweLibrary.ObjectsDB;
import com.example.sweLibrary.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class Authentication {
    @FXML
    public Button buttonLogin;
    @FXML
    public TextField inputKennung;
    @FXML
    public PasswordField inputPassword;

    public static int id;

    public void logIn(ActionEvent event) throws IOException {
        int id = Integer.parseInt(inputKennung.getText());
        String pw = inputPassword.getText();
        Customer customer = ObjectsDB.customerMap.get(id);
        Employee employee = ObjectsDB.employeeMap.get(id);
        //Prüfen der Eingaben
        if (customer == null) {
            if (employee == null) { //Wenn keine Kennung erkannt wird
                inputPassword.clear();
                inputKennung.clear();
            } else if (Objects.equals(employee.password, pw)) { //Wenn Mitarbeiter erkannt wird
                Authentication.id= id;
                SceneSwitcher.switchScene(event, "headerEmployee.fxml");
            }
            else {
                inputPassword.clear();
                inputKennung.clear();
            }
        } else if (Objects.equals(customer.password, pw)) { //Wenn Kunde erkannt wird
            Authentication.id= id;
            SceneSwitcher.switchScene(event, "headerCustomer.fxml");
        }
        else {
            inputPassword.clear();
            inputKennung.clear();
        }
    }

    public static int logOut(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "login.fxml");
        id = -1;
        return 0;
    }
}

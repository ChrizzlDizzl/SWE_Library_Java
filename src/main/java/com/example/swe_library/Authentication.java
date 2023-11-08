package com.example.swe_library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class Authentication {
    @FXML
    public Button button_login;
    @FXML
    public TextField input_kennung;
    @FXML
    public PasswordField input_password;

    public void authenticate(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "headerCustomer.fxml");
    }
    public void logIn(ActionEvent event) throws IOException {
        //Sicherheit kann mit Token verbessert werden
      //  Scanner scanner = new Scanner(System.in);

      //  System.out.print("Enter your ID: ");
     //   int id = Integer.parseInt(scanner.nextLine());
        int id = Integer.parseInt(input_kennung.getText());
     //   System.out.print("Enter your PW: ");
       // String pw = scanner.nextLine();
        String pw = input_password.getText();
        Customer customer = ObjectsDB.customerMap.get(id);
        Employee employee = ObjectsDB.employeeMap.get(id);
        //Prüfen der Eingaben
        if (customer == null) {
            if (employee == null) { //Wenn keine Kennung erkannt wird
                input_password.clear();
                input_kennung.clear();
            } else if (Objects.equals(employee.password, pw)) { //Wenn Mitarbeiter erkannt wird
                SceneSwitcher.switchScene(event, "headerEmployee.fxml");
            }
            else {
                input_password.clear();
                input_kennung.clear();
            }
        } else if (Objects.equals(customer.password, pw)) { //Wenn Kunde erkannt wird
            SceneSwitcher.switchScene(event, "headerCustomer.fxml");
        }
        else {
            input_password.clear();
            input_kennung.clear();
        }
       // return id;
    }

    public static int logOut(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "login.fxml");
        return 0;
    }
}

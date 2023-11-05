package com.example.swe_library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Authentication {
    @FXML
    private Button button_login;
    @FXML
    private TextField input_kennung;
    @FXML
    private PasswordField input_password;

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
        System.out.println(id);
        System.out.println(pw);
        Customer customer = ObjectsDB.customerMap.get(id);
        Employee employee = ObjectsDB.employeeMap.get(id);
        if (customer == null) {
            if (employee == null) {
                input_password.clear();
                input_kennung.clear();
            } else if (Objects.equals(employee.password, pw)) {
                SceneSwitcher.switchScene(event, "headerEmployee.fxml");
            }
            else {

                input_password.clear();
                input_kennung.clear();
            }
        } else if (Objects.equals(customer.password, pw)) {
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

package com.example.sweLibrary.noLongerNeeded.crappyTests;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import com.example.sweLibrary.Authentication;

public class AuthenticationTest extends ApplicationTest {

    private Authentication authentication;
    private Button buttonLogin;
    private TextField inputKennung;
    private PasswordField inputPassword;

    @Override
    public void start(Stage stage) {
        buttonLogin = new Button();
        inputKennung = new TextField();
        inputPassword = new PasswordField();

        buttonLogin.setOnAction(actionEvent -> {
            try {
                authentication.logIn(actionEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        StackPane root = new StackPane(buttonLogin, inputKennung, inputPassword);
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testLogInWithValidCredentials() {
        // Set valid credentials
        inputKennung.setText("validID");
        inputPassword.setText("validPassword");

        clickOn(buttonLogin);

        // Assert that the scene has been switched to "headerCustomer.fxml"
        // You can add your assertion here
    }

    @Test
    public void testLogInWithInvalidCredentials() {
        // Set invalid credentials
        inputKennung.setText("invalidID");
        inputPassword.setText("invalidPassword");

        clickOn(buttonLogin);

        // Assert that the input fields have been cleared
        // You can add your assertion here
    }
}

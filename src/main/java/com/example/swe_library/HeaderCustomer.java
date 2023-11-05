package com.example.swe_library;

import javafx.event.ActionEvent;

import java.io.IOException;

public class HeaderCustomer {
    public void logOut(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "login.fxml");
    }
}

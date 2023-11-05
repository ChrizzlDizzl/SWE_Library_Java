package controller;

import com.example.swe_library.SceneSwitcher;
import javafx.event.ActionEvent;

import java.io.IOException;

public class HeaderEmployee {
    public void logOut(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "login.fxml");
    }
}

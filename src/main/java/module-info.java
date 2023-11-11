module com.example.swe_library {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.sweLibrary to javafx.fxml;
    exports com.example.sweLibrary;
    exports controller;
    opens controller to javafx.fxml;
    exports com.example.sweLibrary.noLongerNeeded;
    opens com.example.sweLibrary.noLongerNeeded to javafx.fxml;
    exports com.example.sweLibrary.noLongerNeeded.crappyTests;
    opens com.example.sweLibrary.noLongerNeeded.crappyTests to javafx.fxml;
}
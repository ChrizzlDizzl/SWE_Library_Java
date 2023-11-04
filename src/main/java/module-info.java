module com.example.swe_library {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.swe_library to javafx.fxml;
    exports com.example.swe_library;
    exports controller;
    opens controller to javafx.fxml;
}
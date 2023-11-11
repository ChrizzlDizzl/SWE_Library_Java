module com.example.swe_library {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.sweLibrary to javafx.fxml;
    exports com.example.sweLibrary;
    exports controller;
    opens controller to javafx.fxml;
}
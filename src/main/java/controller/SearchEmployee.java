package controller;

import com.example.swe_library.InteractionsMedia;
import com.example.swe_library.Media;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class SearchEmployee extends HeaderEmployee {
    @FXML
    private TableView<Media> tableView;
    @FXML
    private TableColumn<Media, String> category;
    @FXML
    private TableColumn<Media, String> name;
    @FXML
    private TableColumn<Media, String> publisher;
    @FXML
    private TableColumn<Media, String> id;
    private TableColumn<Media, String> publishDate;
    @FXML
    private TableColumn<Media, String> availability;
    @FXML
    private void initialize(){
        //Spalten mit den jeweiligen Media Attributen markieren
        category.setCellValueFactory(item -> new ReadOnlyObjectWrapper(item.getValue().mediaCategory));
        name.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().name));
        publisher.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().publisher));
        id.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().id));
        publishDate.setCellValueFactory(item -> {
            LocalDate date = item.getValue().getReturnDate(); // Replace this with your actual method to get the LocalDate
            String formattedDate = (date != null) ? date.toString() : "";
            return new ReadOnlyStringWrapper(formattedDate);
        });
        availability.setCellValueFactory(item -> new ReadOnlyBooleanWrapper(item.getValue().availability).asString());

        //Suchliste der Medien in ObservableList casten und Tabelle befüllen
        ObservableList<Media> list = FXCollections.observableArrayList(InteractionsMedia.searchList);
        tableView.setItems(list);
    }
}

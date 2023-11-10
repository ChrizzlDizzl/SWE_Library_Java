package controller;

import com.example.swe_library.InteractionsMedia;
import com.example.swe_library.Media;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    // @FXML
    // private TableColumn<Media, String> availability;

    @FXML
    private void initialize(){
        //Spalten mit den jeweiligen Media Attributen markieren
        category.setCellValueFactory(item -> new ReadOnlyObjectWrapper(item.getValue().mediaCategory));
        name.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().name));
        publisher.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().publisher));
        id.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().id));
       // availability.setCellValueFactory(item -> new ReadOnlyStringWrapper("returnDate"));

        //Suchliste der Medien in ObservableList casten und Tabelle befüllen
        ObservableList<Media> list = FXCollections.observableArrayList(InteractionsMedia.searchList);
        tableView.setItems(list);
    }
}
package controller;

import com.example.sweLibrary.InteractionsMedia;
import com.example.sweLibrary.Media;
import com.example.sweLibrary.SceneSwitcher;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDate;

public class InventoryEmployee extends HeaderEmployee{
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
    @FXML
    private TableColumn<Media, String> publishDate;
    @FXML
    private TableColumn<Media, String> bookshelf;
    @FXML
    private TableColumn<Media, String> availability;

    @FXML
    private void initialize(){
        //Spalten mit den jeweiligen Media Attributen markieren
        category.setCellValueFactory(item -> new ReadOnlyObjectWrapper(item.getValue().mediaCategory));
        name.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().name));
        publisher.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().publisher));
        id.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().id));
        bookshelf.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().shelf));
        publishDate.setCellValueFactory(item -> {
            LocalDate date = item.getValue().publishDate; // Replace this with your actual method to get the LocalDate
            String formattedDate = (date != null) ? date.toString() : "";
            return new ReadOnlyStringWrapper(formattedDate);
        });
        availability.setCellValueFactory(item -> new ReadOnlyBooleanWrapper(item.getValue().availability).asString());

        //Suchliste der Medien in ObservableList casten und Tabelle befüllen
        ObservableList<Media> list = FXCollections.observableArrayList(InteractionsMedia.searchList);
        tableView.setItems(list);
    }

    public void clickItem(MouseEvent event) {
        // Der EventHandler hat ein hohe Loadtime
        // Ausgewähltes Item wird herausgefiltert und mit dem Item wird die Maske geladen
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Media media = tableView.getSelectionModel().getSelectedItem();
                ActionEvent ae = new ActionEvent(event.getSource(), event.getTarget());
                try {
                    ChangeMediaEmployee.setMedia(media);
                    SceneSwitcher.switchScene(ae, "changeMediaEmployee.fxml");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

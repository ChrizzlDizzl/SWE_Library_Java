package controller;

import com.example.sweLibrary.CreateBackup;
import com.example.sweLibrary.Media;
import com.example.sweLibrary.MediaCategory;
import com.example.sweLibrary.ObjectsDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ChangeMediaEmployee extends HeaderEmployee {
    @FXML
    private TextField id;
    @FXML
    private TextField category;
    @FXML
    private TextField title;
    @FXML
    private TextField publishDate;
    @FXML
    private TextField publisher;
    @FXML
    private TextArea description;
    @FXML
    private TextField floor;
    @FXML
    private TextField bookshelf;

    @FXML
    private void newMedia(ActionEvent actionEvent) throws Exception {
        //Neues Medium erstellen und Werte eintragen
        //Medium erstellen und Werte eintragen
        Media media = new Media();
        media.id = id.getText();
        if(ObjectsDB.mediaMap.containsKey(media.id)) {
            throw new Exception("Error, key already exists!");
        }
        media.name = title.getText();
        media.publisher = publisher.getText();
        //Medienkategorie prüfen
        try {
            media.mediaCategory = MediaCategory.valueOf(category.getText());
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal input for MediaCategory, try again");
            System.out.println("MediaCategory (Buch/DVD/Zeitschrift/Landkarte)");
            clearField(category);
            return;
        }
        try {
            media.publishDate = LocalDate.parse(publishDate.getText());
        } catch (DateTimeParseException e) {
            System.out.println("Illegal input for date, try again");
            System.out.println("PublishDate (yyyy-mm-dd)");
            clearField(publishDate);
            return;
        }
        String filePath = "library.csv";
        String filePathReturn = "returnDates.csv";

        //Medium der MediaMap hinzufügen
        ObjectsDB.mediaMap.put(media.id, media);
        CreateBackup.createBackup(filePath);
        CreateBackup.backupReturnDates(filePathReturn);
        //Clear bei Erfolg
        clearAll();
    }
    @FXML
    private void save(ActionEvent actionEvent) throws Exception {
        //Medium überschreiben
        Media media = new Media();
        media.id = id.getText();

        if (media.id==null) {
            throw new Exception("Error, key is empty!"); //Schlüssel muss existieren, sonst gibt es Fehler in der Datenbank
        }

        media.name = title.getText();
        media.publisher = publisher.getText();
        //Medienkategorie prüfen
        try {
            media.mediaCategory = MediaCategory.valueOf(category.getText());
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal input for MediaCategory, try again");
            System.out.println("MediaCategory (Buch/DVD/Zeitschrift/Landkarte)");
            clearField(category);
            return;
        }
        try {
            media.publishDate = LocalDate.parse(publishDate.getText());
        } catch (DateTimeParseException e) {
            System.out.println("Illegal input for date, try again");
            System.out.println("PublishDate (yyyy-mm-dd)");
            clearField(publishDate);
            return;
        }
        String filePath = "library.csv";
        String filePathReturn = "returnDates.csv";

        //Medium der MediaMap hinzufügen
        if(ObjectsDB.mediaMap.containsKey(media.id)) {
            ObjectsDB.mediaMap.remove(media.id);
        }
        ObjectsDB.mediaMap.put(media.id, media);
        CreateBackup.createBackup(filePath);
        CreateBackup.backupReturnDates(filePathReturn);
        //Clear bei Erfolg
        clearAll();
    }
    private void clearField(TextField field){
        field.clear();
    }
    private void clearAll(){
        id.clear();
        category.clear();
        title.clear();
        publishDate.clear();
        publisher.clear();
        description.clear();
        floor.clear();
        bookshelf.clear();
    }
    @FXML
    private void delete(ActionEvent actionEvent) throws Exception { //delete a media
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String mediaID = id.getText();
        if (!ObjectsDB.mediaMap.containsKey(mediaID) || mediaID.isEmpty()) {
            clearAll();
            alert.setTitle("Error!");
            alert.setHeaderText("Medium-ID wurde nicht gefunden oder ist leer");
            alert.showAndWait();
            throw new Exception("Error!");
        }
        Media media = ObjectsDB.mediaMap.get(mediaID);
        if (!media.availability) { //check media availability
            clearAll();
            alert.setTitle("Error!");
            alert.setHeaderText("Medium ist noch ausgeliehen!");
            alert.showAndWait();
            throw new Exception("Error!");
        }
        ObjectsDB.mediaMap.remove(mediaID); //delete media
        alert.setTitle("Succeeded!");
        alert.setHeaderText("Löschen war erfolgreich!");
        alert.showAndWait();
        clearAll();
    }
}

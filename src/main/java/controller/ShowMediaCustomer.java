package controller;

import com.example.sweLibrary.Media;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ShowMediaCustomer extends HeaderCustomer{
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
    private TextField bookshelf;

    private static Media m;

    @FXML
    public void initialize(){
        //Felder werden befüllt, wenn ein Media mitgegeben wird
        if(m != null){
            id.setText(m.id);
            category.setText(""+m.mediaCategory);
            title.setText(m.name);
            publishDate.setText(""+m.publishDate);
            publisher.setText(m.publisher);
            bookshelf.setText(m.shelf);
        }
    }
    public static void setMedia(Media media){
        m = media;
    }
    public static void resetMedia(){
        m = null;
    }
}

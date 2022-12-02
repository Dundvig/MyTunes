package GUI.Controller;

import GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.sql.Time;

public class SongAddController {
public class SongAddController extends AbstractController {
    public TextField txtTitle;
    public TextField txtArtist;
    public Button btnAddSong;
    public TextField txtGenre;
    public TextField txtTimer;
    public TextField txtURL;
    private SongModel songModel;

    public SongAddController() {
        try {
            songModel = new SongModel();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    public void handleAdd(ActionEvent actionEvent) {
        String title = txtTitle.getText();
        String artist = txtArtist.getText();
        String genre = txtGenre.getText();
        Time timer = Time.valueOf(txtTimer.getText());
        String url = txtURL.getText();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();

        try{
            songModel.createSong(title, artist, genre, timer, url);
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    @Override
    public void setup() {

    }
}

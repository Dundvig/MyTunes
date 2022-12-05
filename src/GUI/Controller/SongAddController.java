package GUI.Controller;

import GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.File;
import java.sql.Time;

public class SongAddController extends AbstractController {
    public TextField txtTitle;
    public TextField txtArtist;
    public TextField txtTimer;
    public TextField txtUrl;
    private SongModel songModel;
    public ComboBox cboxCategory;

    private final String[] category = {
            "Pop",
            "Rock",
            "Blues",
            "Classical",
            "Country",
            "Folk",
            "Jazz",
            "Newage",
            "Reggae",
            "Soundtrack",
            "Electronic",
            "Funk/Soul",
            "Hip-Hop",
            "Religious",
            "Latin",
            "Non-Music",
            "Rap",
            "R&B",
            "International",
            "Dødstramp"
    };

    public SongAddController() {
    }

    @Override
    public void setup() {
        songModel = getModel().getSongModel();
        cboxCategory.getItems().addAll(category);
    }

    public void handleCancelSaveSong(ActionEvent actionEvent) {
        //Close the window by clicking the cancel bottom.
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void handleSaveSong(ActionEvent actionEvent) {
        try{
        String title = txtTitle.getText();
        String artist = txtArtist.getText();
        String genre = (String) cboxCategory.getSelectionModel().getSelectedItem();
        Time timer = Time.valueOf(txtTimer.getText());
        String url = txtUrl.getText();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();

            songModel.createSong(title, artist, genre, timer, url);
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    public void handleFileChooser(ActionEvent actionEvent) {
        //create a new stage for picking files with the title "Pick a Song" and start in the applications dir
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a Song");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        //add which filetype is valid
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Sound Files (*.mp3, *.wav)","*.mp3","*.wav"),
                new FileChooser.ExtensionFilter("MP3 Files (*.mp3)", "*.mp3"),
                new FileChooser.ExtensionFilter("WAV Files (*.wav)","*.wav")
        );
        //put selected file into the url textfield
        File selectedFile = fileChooser.showOpenDialog(stage);

        txtUrl.setText(selectedFile.getPath());
    }
}

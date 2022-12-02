package GUI.Controller;

import BE.Song;
import GUI.Model.SongModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class EditSongController extends AbstractController {

    public TextField txtTitle;

    public TextField txtArtist;
    public Button CancelSaveSong;
    public ComboBox cboxCategory;
    public TextField txtUrl;
    public TextField txtTimer;
    private SongModel songModel;
    private String[] category = {
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

    public EditSongController() {
    }

    public void handleCancelSaveSong(ActionEvent actionEvent) {
        //Close the window by clicking the cancel bottom.
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void handleSaveSong(ActionEvent actionEvent) {
        try {
            String updatedTitle = txtTitle.getText();
            String updatedArtist = txtArtist.getText();
            int updatedTimer = Integer.parseInt(txtTimer.getText());
            String updatedMp3 = txtUrl.getText();
            Song updatedSong = new Song(songModel.getSelectedSong().getId(), updatedTitle, updatedArtist, songModel.getSelectedSong().getGenre(), updatedTimer, updatedMp3);

            songModel.updateSong(updatedSong);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    @Override
    public void setup() {
        songModel = getModel().getSongModel();
        txtTitle.setText(songModel.getSelectedSong().getTitle());
        txtArtist.setText(songModel.getSelectedSong().getArtist());
        //txtGenre.setText(songModel.getSelectedSong().getGenre());
        txtTimer.setText(String.valueOf(songModel.getSelectedSong().getTimer()));
        txtUrl.setText(songModel.getSelectedSong().getURL());
        cboxCategory.getItems().addAll(category);
    }

    public void handleFileChooser(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a Song");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MP3 Files (*.mp3)", "*.mp3"),
                new FileChooser.ExtensionFilter("WAV Files (*.wav)","*.wav")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);
        txtUrl.setText(selectedFile.getPath());
    }
}

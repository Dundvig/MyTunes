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
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EditSongController extends AbstractController {

    public TextField txtTitle;

    public TextField txtArtist;
    public Button CancelSaveSong;
    public ChoiceBox cboxCategory;
    public TextField txtUrl;
    public TextField txtTimer;
    private SongModel songModel;
    public EditSongController(){
    }

    public EditSongController(ChoiceBox cboxCategory) {
        ObservableList<String> list = FXCollections.observableArrayList();
        ChoiceBox<String> box = new ChoiceBox<>(FXCollections.observableArrayList(
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
                "Dødstramp"));
        SortedList<String> sortedList = new SortedList<>(list);
    }

    public void handleCancelSaveSong(ActionEvent actionEvent) {
        //Close the window by clicking the cancel bottom.
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void handleSaveSong(ActionEvent actionEvent) throws Exception {
        String updatedTitle = txtTitle.getText();
        String updatedArtist = txtArtist.getText();
        int updatedTimer = Integer.parseInt(txtTimer.getText());
        String updatedMp3 = txtUrl.getText();
        Song updatedSong = new Song(songModel.getSelectedSong().getId(), updatedTitle, updatedArtist, songModel.getSelectedSong().getGenre(),  updatedTimer, updatedMp3);

        songModel.updateSong(updatedSong);

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void setup() {
        songModel = getModel().getSongModel();
        txtTitle.setText(songModel.getSelectedSong().getTitle());
        txtArtist.setText(songModel.getSelectedSong().getArtist());
        //txtGenre.setText(songModel.getSelectedSong().getGenre());
        txtTimer.setText(String.valueOf(songModel.getSelectedSong().getTimer()));
        txtUrl.setText(songModel.getSelectedSong().getURL());
    }
}

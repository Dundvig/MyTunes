package GUI.Controller;

import BE.Song;
import GUI.Model.MyTunesModel;
import GUI.Model.SongModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EditSongController {

    public TextField txtTitle;

    public TextField txtArtist;
    public Button CancelSaveSong;
    public ChoiceBox cboxCategory;
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
        Stage stage = (Stage) CancelSaveSong.getScene().getWindow();
        stage.close();
    }

    public void handleSaveSong(ActionEvent actionEvent) {

        try{
            MyTunesController mtc = new MyTunesController();
            Song updatedSong = mtc.lstSong.getSelectionModel().getSelectedItem();
            updatedSong.setTitle(txtTitle.getText());
            updatedSong.setArtist(txtArtist.getText());

            songModel.updateSong(updatedSong);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

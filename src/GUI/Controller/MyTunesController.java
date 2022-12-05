package GUI.Controller;

import BE.Song;
import GUI.Model.MyTunesModel;
import GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyTunesController extends AbstractController {

    public ListView<Song> lstSong;
    public ImageView imgSearch;
    public ImageView imgUpArrow;
    public ImageView imgDownArrow;
    public ImageView imgLeftArrow;
    public ImageView imgBack;
    public ImageView imgPlay;
    public ImageView imgNext;
    public ImageView imgVolume;
    public Button btnClose;
    public ListView lstPlaylist;
    public ListView lstPlaylistSongs;
    public TextField txtFilter;
    public Text txtPlaying;
    public Button btnEditSong;
    private SongModel songModel;

    @Override
    public void setup() {
        songModel = getModel().getSongModel();

        lstSong.setItems(songModel.getObservableSongs());
    }


    // Opens a new window to create a new playlist with title.
    public void handleNewPlaylist(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/EditPlaylistView.fxml"));
            Parent root = null;
            root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("New Playlist");
            stage.show();
        } catch (IOException e) {
            displayError(e);
            e.printStackTrace();
        }

    }

    public void handleEditPlaylist(ActionEvent actionEvent) {
    }

    public void handleDelete(ActionEvent actionEvent) {
    }

    public void handleSongUp(ActionEvent actionEvent) {
    }

    public void handleSongDown(ActionEvent actionEvent) {
    }

    public void handlePlaylistDeleteSong(ActionEvent actionEvent) {
    }

    public void handleAdd(ActionEvent actionEvent) {
    }

    public void handleNewSong(ActionEvent actionEvent) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUI/View/SongAddView.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            SongAddController controller = loader.getController();
            controller.setModel(super.getModel());
            controller.setup();

            Stage dialogWindow = new Stage();
            dialogWindow.setTitle("Add Song");
            dialogWindow.initModality(Modality.WINDOW_MODAL);
            dialogWindow.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogWindow.setScene(scene);
            dialogWindow.show();
    }

    public void handleEditSong(ActionEvent actionEvent) throws IOException {
        Song selectedSong = lstSong.getSelectionModel().getSelectedItem();
        songModel.setSelectedSong(selectedSong);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/EditSongView.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        EditSongController controller = loader.getController();
        controller.setModel(super.getModel());
        controller.setup();

        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("Edit Song information");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);
        dialogWindow.show();
    }

    public void handleDeleteSong(ActionEvent actionEvent) {
        try {
            Song selectedSong = lstSong.getSelectionModel().getSelectedItem();
            songModel.setSelectedSong(selectedSong);
            songModel.deleteSong(songModel.getSelectedSong());
        } catch (Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    public void handleClose(ActionEvent actionEvent) {
        cancel(btnClose);
    }

    public void handleSearch(ActionEvent actionEvent) {
    }

    public void handleBack(ActionEvent actionEvent) {
        // Play the previous song.
    }

    public void handlePlay(ActionEvent actionEvent) {
        MediaPlayer mp = new MediaPlayer(new Media(""));
        mp.play();
    }

    public void handleNext(ActionEvent actionEvent) {
        // Plays the next song.
    }

    public void handleVolume(MouseEvent mouseEvent) {
    }
}

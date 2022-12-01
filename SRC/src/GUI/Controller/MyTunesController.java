package GUI.Controller;

import BE.Song;
import GUI.Model.MyTunesModel;
import GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyTunesController implements Initializable {

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
    private MyTunesModel myTunesModel;
    private SongModel songModel;
    public MyTunesController() {
        try {
            myTunesModel = new MyTunesModel();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lstSong.setItems(myTunesModel.getObservableSongs());
    }

    private void displayError(Throwable t) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    // Opens a new window to create a new playlist with title.
    public void handleNewPlaylist(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/EditPlaylistView.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("New Playlist");
        stage.show();
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
        //Opens the add/edit song window.
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/EditSongView.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("New Song");
        stage.show();
    }

    public void handleEditSong(ActionEvent actionEvent) throws IOException {
        //Opens the Add/edit song window.
        handleNewSong(actionEvent);
    }

    public void handleDeleteSong(ActionEvent actionEvent) {
        try {
            Song song = lstSong.getSelectionModel().getSelectedItem();
            songModel.deleteSong(song);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleClose(ActionEvent actionEvent) {
        //get the stage
        Stage stage = (Stage) btnClose.getScene().getWindow();
        //close the stage
        stage.close();
    }

    public void handleSearch(ActionEvent actionEvent) {
    }

    public void handleBack(ActionEvent actionEvent) {

    }

    public void handlePlay(ActionEvent actionEvent) {
        MediaPlayer mp = new MediaPlayer(new Media(""));
        mp.play();
    }

    public void handleNext(ActionEvent actionEvent) {

    }

    public void handleVolume(MouseEvent mouseEvent) {
    }
}

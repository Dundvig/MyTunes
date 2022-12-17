package GUI.Controller;

import BE.Playlist;
import BE.Song;
import GUI.Model.PlaylistModel;
import GUI.Model.SongModel;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

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
    public ListView<Playlist> lstPlaylist;
    public ListView<Song> lstPlaylistSongs;
    public TextField txtFilter;
    public Text txtPlaying;
    public Button btnEditSong;
    public Slider sldrVolume;
    private SongModel songModel;
    private PlaylistModel playlistModel;
    private boolean isPlaying;
    private MediaPlayer mediaPlayer;
    private int songNumber;
    private Song songPlaying;
    private Media media;
    private String path;
    private File file;


    @Override
    public void setup() {
        songModel = getModel().getSongModel();
        playlistModel = getModel().getPlaylistModel();

        lstSong.setItems(songModel.getObservableSongs());
        lstPlaylist.setItems(playlistModel.getObservablePlaylists());
        txtPlaying.setText("Select a song");
    }


    // Opens a new window to create a new playlist with title.
    public void handleNewPlaylist(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/PlaylistAddView.fxml"));
            AnchorPane pane = null;
            pane = (AnchorPane) loader.load();

            PlaylistAddController controller = loader.getController();
            controller.setModel(super.getModel());
            controller.setup();

            stage.setScene(new Scene(pane));
            stage.setTitle("Add Playlist");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException ex) {
            displayError(ex);
            ex.printStackTrace();
        }
    }

    //Open the edit playlist window to edit the selected playlist
    public void handleEditPlaylist(ActionEvent actionEvent) {
        try {
            Playlist selectedPlaylist = (Playlist) lstPlaylist.getSelectionModel().getSelectedItem();
            playlistModel.setSelectedPlaylist(selectedPlaylist);

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/EditPlaylistView.fxml"));
            AnchorPane pane = null;
            pane = (AnchorPane) loader.load();

            EditPlaylistController controller = loader.getController();
            controller.setModel(super.getModel());
            controller.setup();

            stage.setScene(new Scene(pane));
            stage.setTitle("Edit Playlist");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException ex) {
            displayError(ex);
            ex.printStackTrace();
        }
    }

    //Delete a playlist
    public void handleDelete(ActionEvent actionEvent) {
        try {
            Playlist selectedPlaylist = (Playlist) lstPlaylist.getSelectionModel().getSelectedItem();
            playlistModel.setSelectedPlaylist(selectedPlaylist);
            playlistModel.deletePlaylist(playlistModel.getSelectedPlaylist());
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    public void handleSongUp(ActionEvent actionEvent) {
        Playlist selectedPlaylist = lstPlaylist.getSelectionModel().getSelectedItem();
        int selectedSong = lstPlaylistSongs.getSelectionModel().getSelectedIndex();
        if (selectedSong == 0) return;
        playlistModel.swapSong(selectedPlaylist, selectedPlaylist.getSongs().get(selectedSong), selectedPlaylist.getSongs().get(selectedSong-1));
        Collections.swap(selectedPlaylist.getSongs(), selectedSong, selectedSong-1);
        lstPlaylistSongs.setItems(FXCollections.observableArrayList(selectedPlaylist.getSongs()));
    }

    public void handleSongDown(ActionEvent actionEvent) {
        Playlist selectedPlaylist = lstPlaylist.getSelectionModel().getSelectedItem();
        int selectedSong = lstPlaylistSongs.getSelectionModel().getSelectedIndex();
        if (selectedPlaylist.getSongs().size() == selectedSong+1) return;
        playlistModel.swapSong(selectedPlaylist, selectedPlaylist.getSongs().get(selectedSong), selectedPlaylist.getSongs().get(selectedSong+1));
        System.out.println(selectedPlaylist.getSongs());
        Collections.swap(selectedPlaylist.getSongs(), selectedSong, selectedSong+1);
        System.out.println(selectedPlaylist.getSongs());
        lstPlaylistSongs.setItems(FXCollections.observableArrayList(selectedPlaylist.getSongs()));
    }

    //Deletes a song in a playlist
    public void handlePlaylistDeleteSong(ActionEvent actionEvent) {
        //Get the selected playlist and song
        Playlist selectedPlaylist = lstPlaylist.getSelectionModel().getSelectedItem();
        Song selectedSong = lstPlaylistSongs.getSelectionModel().getSelectedItem();

        //Delete that shit
        playlistModel.deletePlaylistSong(selectedPlaylist, selectedSong);
    }

    //Adds a song to a playlist
    public void handleAdd(ActionEvent actionEvent) {
        try {
            Playlist selectedPlaylist = lstPlaylist.getSelectionModel().getSelectedItem();
            Song selectedSong = lstSong.getSelectionModel().getSelectedItem();

            playlistModel.addSongToPlaylist(selectedPlaylist,selectedSong);
            lstPlaylistSongs.setItems(FXCollections.observableArrayList(selectedPlaylist.getSongs()));
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    public void handleNewSong(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUI/View/SongAddView.fxml"));
            AnchorPane pane = null;
            pane = (AnchorPane) loader.load();
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
        } catch (IOException ex) {
            displayError(ex);
            ex.printStackTrace();
        }


    }

    public void handleEditSong(ActionEvent actionEvent) {
        try {
            Song selectedSong = lstSong.getSelectionModel().getSelectedItem();
            songModel.setSelectedSong(selectedSong);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUI/View/EditSongView.fxml"));
            AnchorPane pane = null;
            pane = (AnchorPane) loader.load();

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
        } catch (IOException ex) {
            displayError(ex);
            ex.printStackTrace();
        }
    }

    public void handleDeleteSong(ActionEvent actionEvent) {
        try {
            Song selectedSong = lstSong.getSelectionModel().getSelectedItem();
            songModel.setSelectedSong(selectedSong);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you wanna delete this song?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK) {
                songModel.deleteSong(songModel.getSelectedSong());
            }
        } catch (Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    //Close the window
    public void handleClose(ActionEvent actionEvent) {
        cancel(btnClose);
    }

    public void handleSearch(ActionEvent actionEvent) throws Exception {
        songModel.searchSong(txtFilter.getText().toLowerCase());
    }

    public void handleBack(ActionEvent actionEvent) {
        // Play the previous song.
        if (lstPlaylistSongs.getSelectionModel().getSelectedItem() != null) {
            songNumber--;
            mediaPlayer.stop();
            isPlaying = false;
            lstPlaylistSongs.getSelectionModel().selectPrevious();
            handlePlay(actionEvent);
        } else {
            mediaPlayer.stop();
            isPlaying = false;
            lstPlaylistSongs.getSelectionModel().select(null);
            txtPlaying.setText("Select a song");
        }

        if (lstSong.getSelectionModel().getSelectedItem() != null) {
            if (songNumber > 0) {
                songNumber--;
                mediaPlayer.stop();
                isPlaying = false;
                lstSong.getSelectionModel().selectPrevious();
                handlePlay(actionEvent);
                //songPlaying = songModel.getObservableSongs().get(songNumber);
            } else {
                mediaPlayer.stop();
                isPlaying = false;
                lstSong.getSelectionModel().select(null);
                txtPlaying.setText("Select a song");

            }
        }
    }

    public void handlePlay(ActionEvent actionEvent) {
        if(lstPlaylistSongs.getSelectionModel().getSelectedItem() != null){
            if(!isPlaying){
                songPlaying = lstPlaylistSongs.getSelectionModel().getSelectedItem();
                songNumber = playlistModel.getObservablePlaylists().indexOf(songPlaying);
                playSong();
            }
            else if(songPlaying != lstPlaylistSongs.getSelectionModel().getSelectedItem()){
                mediaPlayer.stop();
                isPlaying = false;
                handlePlay(actionEvent);
            }
            else{
                mediaPlayer.stop();
                isPlaying = false;
            }
        }
        if(lstSong.getSelectionModel().getSelectedItem() != null) {
            if (!isPlaying) {
                songPlaying = lstSong.getSelectionModel().getSelectedItem();
                songNumber = songModel.getObservableSongs().indexOf(songPlaying);
                playSong();
            } else if(songPlaying != lstSong.getSelectionModel().getSelectedItem()){
                mediaPlayer.stop();
                isPlaying = false;
                handlePlay(actionEvent);
            }
                else{
                mediaPlayer.stop();
                isPlaying = false;
            }
        }

    }

    public void playSong(){
        file = new File(songPlaying.getURL());
        path = file.getAbsolutePath();
        path.replace("\\", "/");

        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        sldrVolume.setValue(mediaPlayer.getVolume() * 100);
        mediaPlayer.play();
        isPlaying = true;
        txtPlaying.setText(songPlaying.getTitle() + " " + "is playing");
        mediaPlayer.setOnEndOfMedia(this::handleNext);
    }

    public void handleNext() {
        // Plays the next song.
        if(lstPlaylistSongs.getSelectionModel().getSelectedItem() != null) {
                songNumber++;
                mediaPlayer.stop();
                isPlaying = false;
                lstPlaylistSongs.getSelectionModel().selectNext();
                handlePlay(new ActionEvent());
            } else {
                mediaPlayer.stop();
                isPlaying = false;
                lstPlaylistSongs.getSelectionModel().select(null);
                txtPlaying.setText("Select a song");
            }
        if(lstSong.getSelectionModel().getSelectedItem() != null) {
            if (songNumber < songModel.getObservableSongs().size() - 1) {
                songNumber++;
                mediaPlayer.stop();
                isPlaying = false;
                lstSong.getSelectionModel().selectNext();
                handlePlay(new ActionEvent());
            } else {
                mediaPlayer.stop();
                isPlaying = false;
                lstSong.getSelectionModel().select(null);
                txtPlaying.setText("Select a song");
            }
        }
    }

    public void handleVolume(MouseEvent mouseEvent) {
        sldrVolume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {

                mediaPlayer.volumeProperty().bind(sldrVolume.valueProperty().divide(100));
            }
        });
    }

    //Get the right list of song for the clicked playlist, you ookin dooker
    public void onPlaylistClick(MouseEvent mouseEvent) {
        Playlist selectedItem = lstPlaylist.getSelectionModel().getSelectedItem();
        playlistModel.getAllPlaylistSongs(selectedItem);
        lstPlaylistSongs.setItems(FXCollections.observableArrayList(selectedItem.getSongs()));
    }

    public void handleLstSongsClicked(MouseEvent mouseEvent) {
        lstPlaylistSongs.getSelectionModel().select(null);
    }

    public void handleLstPlaylistSongsClicked(MouseEvent mouseEvent) {
        lstSong.getSelectionModel().select(null);
    }
}

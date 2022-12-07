package GUI.Controller;

import BE.Playlist;
import GUI.Model.PlaylistModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Time;

public class EditPlaylistController extends AbstractController{

    public TextField txtTitle;
    private PlaylistModel playlistModel;
    public Button cancelWindow;
    public Button savePlaylist;

    public EditPlaylistController() {
        try {
            playlistModel = new PlaylistModel();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    public void handleCancelWindow(ActionEvent actionEvent) {
        cancel(cancelWindow);
    }

    public void handleSavePlaylist(ActionEvent actionEvent) {
        try {
            String updatedTitle = txtTitle.getText();
            Time time = playlistModel.getSelectedPlaylist().getTime();
            int totalSongs = playlistModel.getSelectedPlaylist().getTotalSongs();

            Playlist updatedPlaylist = new Playlist(playlistModel.getSelectedPlaylist().getId(), updatedTitle, time, totalSongs);
            playlistModel.updatePlaylist(updatedPlaylist);

            cancel(savePlaylist);
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }

    }

    public Time getPlaylistDuration() {
        return new Time(0);
    }

    public int getTotalSongs() {
        //return playlist.length();
        return 0;
    }

    public int getId() {
        return 1;
    }

    @Override
    public void setup() {
        playlistModel = getModel().getPlaylistModel();
        txtTitle.setText(playlistModel.getSelectedPlaylist().getTitle());
    }
}
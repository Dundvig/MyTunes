package GUI.Controller;

import GUI.Model.PlaylistModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Time;

public class EditPlaylistController {

    public TextField txtTitle;
    private PlaylistModel playlistModel;

    public Button cancelWindow;
    public Button savePlaylist;

    public EditPlaylistController() throws Exception {
        playlistModel = new PlaylistModel();
    }

    public void handleCancelWindow(ActionEvent actionEvent) {


    }

    public void handleSavePlaylist(ActionEvent actionEvent) throws Exception {

        String title = txtTitle.getText();

        playlistModel.createNewPlaylist(title, getPlaylistDuration(), getTotalSongs(), getId());

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

}

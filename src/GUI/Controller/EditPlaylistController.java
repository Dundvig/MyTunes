package GUI.Controller;

import GUI.Model.PlaylistModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
            String title = txtTitle.getText();

            playlistModel.createNewPlaylist(title, getPlaylistDuration(), getTotalSongs(), getId());
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

    }
}
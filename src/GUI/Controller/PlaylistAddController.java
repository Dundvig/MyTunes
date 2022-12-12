package GUI.Controller;

import GUI.Model.PlaylistModel;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.awt.*;
import java.sql.Time;

public class PlaylistAddController extends AbstractController{

    public javafx.scene.control.Button cancelWindow;
    public javafx.scene.control.Button savePlaylist;
    public TextField txtTitle;
    private PlaylistModel playlistModel;
    

    public PlaylistAddController(){
        try {
            playlistModel = new PlaylistModel();
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
    public void setup() {playlistModel = getModel().getPlaylistModel();}

    //Save the new playlist
    public void handleSavePlaylist(javafx.event.ActionEvent actionEvent) {
        try {
            String title = txtTitle.getText();

            playlistModel.createNewPlaylist(title, getPlaylistDuration(), getTotalSongs(), getId());
            cancel(savePlaylist);
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    //Close the window
    public void handleCancelWindow(ActionEvent actionEvent) {
        cancel(cancelWindow);
    }
}

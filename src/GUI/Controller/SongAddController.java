package GUI.Controller;

import GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SongAddController {
    public TextField txtTitle;
    public TextField txtArtist;
    public Button btnAddSong;
    public TextField txtGenre;
    public TextField txtTimer;
    public TextField txtURL;
    private SongModel songModel;

    public SongAddController() throws Exception {
        songModel = new SongModel();
    }

    public void handleAdd(ActionEvent actionEvent) throws Exception {
        String title = txtTitle.getText();
        String artist = txtArtist.getText();
        String genre = txtGenre.getText();
        int timer = Integer.parseInt(txtTimer.getText());
        String url = txtURL.getText();

        try{
            songModel.createSong(title, artist, genre, timer, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

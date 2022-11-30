package GUI.Controller;

import GUI.Model.MyTunesModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EditSongController {

    public TextField txtTitle;

    public TextField txtArtist;
    public Button CancelSaveSong;
    private MyTunesModel myTunesModel;
    public EditSongController(){
        try{
            myTunesModel = new MyTunesModel();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleCancelSaveSong(ActionEvent actionEvent) {
        Stage stage = (Stage) CancelSaveSong.getScene().getWindow();
        stage.close();
    }

    public void handleSaveSong(ActionEvent actionEvent) {
        String title = txtTitle.getText();
        String artist = txtArtist.getText();
    }
}

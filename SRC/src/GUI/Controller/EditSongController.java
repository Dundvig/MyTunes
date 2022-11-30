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
        //Close the window by clicking the cancel bottom.
        Stage stage = (Stage) CancelSaveSong.getScene().getWindow();
        stage.close();
    }

    public void handleSaveSong(ActionEvent actionEvent) {
        //Get/add the values for the song so it can be added or changed.
        String title = txtTitle.getText();
        String artist = txtArtist.getText();
        //TODO DO THIS
    }
}

package GUI.Controller;

import GUI.Model.MTModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class AbstractController {
    private MTModel mtModel;
    //Getters and setters for Model choosing.
    public void setModel(MTModel mtModel)
    {
        this.mtModel = mtModel;
    }

    public MTModel getModel(){
        return mtModel;
    }

    public abstract void setup();

    public void displayError(Throwable t) {
        //if an error occur, display it to the user
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    public void cancel(Button btn) {
        //get the stage
        Stage stage = (Stage) btn.getScene().getWindow();
        //close the stage
        stage.close();
    }
}


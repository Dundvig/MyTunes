package GUI.Controller;

import GUI.Model.MTModel;
import javafx.scene.control.Alert;

public abstract class AbstractController {
    private MTModel mtModel;

    public void setModel(MTModel mtModel)
    {
        this.mtModel = mtModel;
    }

    public MTModel getModel(){
        return mtModel;
    }

    public abstract void setup();

    public void displayError(Throwable t) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }
}


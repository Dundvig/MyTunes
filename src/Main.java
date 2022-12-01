import GUI.Controller.MyTunesController;
import GUI.Model.MTModel;
import GUI.Model.MyTunesModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GUI/View/MyTunesView.fxml"));
        Parent root = loader.load();

        MyTunesController controller = loader.getController();
        controller.setModel(new MTModel());
        controller.setup();

        primaryStage.setTitle("MyTunes");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
package sample.FXML_CSS;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerAbout {
    public Button okButton;

    public void close(ActionEvent actionEvent){
        Stage stage = (Stage)okButton.getScene().getWindow();
        stage.close();
    }
}

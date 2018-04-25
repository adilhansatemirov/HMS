/*Author's information
* Author: Adilkhan Satemirov
* Email: adilkhansatemirovv@gmail.com
* Phone number: 8(775)216-01-56
*/
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

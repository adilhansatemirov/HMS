/*Author's information
* Author: Adilkhan Satemirov
* Email: adilkhansatemirovv@gmail.com
* Phone number: 8(775)216-01-56
*/
package sample.Manual;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.ControllerHome;

import java.io.IOException;

public class ManualWFourthController {
    public void next() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ManualZFifth.fxml"));
        Scene scene = new Scene(root);
        ControllerHome.stageManual.setScene(scene);
    }

}

/*Author's information
* Author: Adilkhan Satemirov
* Email: adilkhansatemirovv@gmail.com
* Phone number: 8(775)216-01-56
*/
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

    //WE START APPLICATION BY LOADING LOGIN PAGE
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent home = FXMLLoader.load(getClass().getResource("FXML_CSS/LogIn.fxml"));
        Scene sceneHome = new Scene(home, 400, 200);
        primaryStage.getIcons().add(new Image("/HMS.png"));
        primaryStage.setTitle("HMS");
        primaryStage.setScene(sceneHome);
        primaryStage.setMinWidth(471);
        primaryStage.setMinHeight(400);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

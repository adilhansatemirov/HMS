/*Author's information
* Author: Adilkhan Satemirov
* Email: adilkhansatemirovv@gmail.com
* Phone number: 8(775)216-01-56
*/
package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogIn implements Initializable {
    ModelLogIn modelLogIn = new ModelLogIn();
    @FXML
    public TextField usernamePlaceHolder;
    public PasswordField passwordPlaceHolder;
    public Button logInButton;
    public Label dbStatus;
    public Label access;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(modelLogIn.databaseIsConnected()){
            dbStatus.setText("Database connected");
        }else{
            dbStatus.setText("Not connected");
        }
    }

    //GETS THE DATA FROM TEXTFIELDS AND SENDS IT TO MODEL WHERE IT IS BEING CHECKED
    public void login() throws Exception{
        if(modelLogIn.isLogin(usernamePlaceHolder.getText(), passwordPlaceHolder.getText())){
            Stage stage = (Stage)logInButton.getScene().getWindow();
            stage.close();
            accessPermitted();
        }else{
            access.setText("Wrong username or password");
        }
    }

    //CREATE A STAGE WITH A MENU IF USERNAME AND PASSWORD ARE CORRECT
    public void accessPermitted() throws Exception{
        Stage stage = new Stage();
        Parent home = FXMLLoader.load(getClass().getResource("FXML_CSS/MainPageHome.fxml"));
        stage.getIcons().add(new Image("/HMS.png"));

        Scene sceneHome = new Scene(home, 950,600);

        stage.setTitle("HMS v1.0");
        stage.setScene(sceneHome);
        stage.setMinWidth(650);
        stage.setMinHeight(450);
        stage.show();
    }
}

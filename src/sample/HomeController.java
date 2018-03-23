package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable{
    public BorderPane borderPane;

    public void setSceneGuests() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MenuGuests/Guest.fxml"));
        borderPane.setCenter(root);

    }
    public void setSceneHome() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MenuHome/Home.fxml"));
        borderPane.setCenter(root);
    }
    public void setSceneRooms() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MenuRooms/Rooms.fxml"));
        borderPane.setCenter(root);
    }
    public void setSceneStaff() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MenuStaff/Staff.fxml"));
        borderPane.setCenter(root);
    }
    public void setSceneTransactions() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MenuTransactions/Transactions.fxml"));
        borderPane.setCenter(root);
    }
    public void setSceneTransport() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MenuTransport/Transport.fxml"));
        borderPane.setCenter(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setSceneHome();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

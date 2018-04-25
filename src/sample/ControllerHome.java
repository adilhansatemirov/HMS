/*Author's information
* Author: Adilkhan Satemirov
* Email: adilkhansatemirovv@gmail.com
* Phone number: 8(775)216-01-56
*/
package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHome implements Initializable{
    public BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setSceneHome();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSceneGuests() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MenuGuests/ViewGuest.fxml"));
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
    public void setSceneTransactions() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MenuTransactions/Transactions.fxml"));
        borderPane.setCenter(root);
    }
    public static Stage stageManual;
    public void manual() throws IOException{
        stageManual = new Stage();
        stageManual.setMinWidth(610);
        stageManual.setMaxWidth(610);
        stageManual.setMinHeight(435);
        stageManual.setMaxHeight(435);
        stageManual.getIcons().add(new Image("/HMS.png"));
        Parent root = FXMLLoader.load(getClass().getResource("Manual/ManualFirst.fxml"));
        Scene scene = new Scene(root);
        stageManual.setScene(scene);
        stageManual.setTitle("Manual");
        stageManual.initModality(Modality.APPLICATION_MODAL);
        stageManual.setResizable(false);
        stageManual.show();
    }
    public void notAvailable(){
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/HMS.png"));
        Label notAvailable = new Label("In HMS v1.0 is it not available yet");
        Button okButton = new Button("Ok");
        notAvailable.setStyle("-fx-font-size: 10pt;\n" +
                "    -fx-text-fill: white;");
        okButton.setStyle("-fx-font-size: 12pt;\n" +
                "    -fx-background-radius: 0;\n" +
                "    -fx-background-color: #050c0c;\n" +
                "    -fx-text-fill: white;");
        okButton.setMinWidth(110);

        notAvailable.setPadding(new Insets(5,0,0,15));
        HBox hBox = new HBox(20);
        hBox.getChildren().add(okButton);
        hBox.setPadding(new Insets(0,0,0,55));


        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(notAvailable,hBox);
        vBox.setStyle("-fx-background-color: #132227;");

        Scene scene = new Scene(vBox,212.5,68);
        stage.setScene(scene);

        stage.setTitle("Sorry(");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();

        okButton.setOnAction(event -> stage.close());
    }

    public void close(){
        Stage stage = (Stage)borderPane.getScene().getWindow();
        stage.close();
    }
    public void showInfo(){
        try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/HMS.png"));
            Parent root = FXMLLoader.load(getClass().getResource("FXML_CSS/About.fxml"));
            stage.setTitle("Guest");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        }catch (IOException e) {
            System.out.println("File not found");
        }
    }
}

package sample.MenuGuests;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.dbUtil.dbConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerGuestInfo implements Initializable {
    public Label name;
    public Label surname;
    public Label timeOfArrival;
    public Label timeOfDeparture;
    public Label passport;
    public Label room;
    public Label contactNumber;
    public Label creditCardNumber;
    public Label cvv;
    public Label dateOfExpire;
    public Label currentBalance;


    public Button removeGuestButton;
    public Button addTransaction;

    static String nameString;
    static String surnameString;
    static String roomString;
    static String balanceString;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText(ControllerGuest.rowData.getName());
        surname.setText(ControllerGuest.rowData.getSurname());
        timeOfArrival.setText(ControllerGuest.rowData.getTimeOfArrival());
        timeOfDeparture.setText(ControllerGuest.rowData.getTimeOfDeparture());
        passport.setText(ControllerGuest.rowData.getPassport());
        room.setText(ControllerGuest.rowData.getRoomID());
        contactNumber.setText(ControllerGuest.rowData.getContactNumber());
        creditCardNumber.setText(ControllerGuest.rowData.getCreditCardNumber());
        cvv.setText(ControllerGuest.rowData.getCVV());
        dateOfExpire.setText(ControllerGuest.rowData.getDateOfExpire());
        currentBalance.setText(ControllerGuest.rowData.getCurrentBalance());

        nameString = name.getText();
        surnameString = surname.getText();
        roomString = room.getText();
        balanceString = currentBalance.getText();
    }

    public void confirmRemoveGuest() {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/HMS.png"));

        Label confirmDeleting = new Label("Are you sure you want to delete this guest?");
        Button delete = new Button("Delete");
        Button cancel = new Button("Cancel");

        delete.setOnAction(event -> {
            String sqlRemove = "DELETE FROM Client WHERE Name = ? AND Surname = ?;";
            String sqlUpdateRoom = "UPDATE Room SET Free = 'yes' WHERE Number = ?";
            String sqlRoomChargeZero = "UPDATE Room Set RoomCharge = '0' WHERE Number = ?";
            try {
                Connection connection = dbConnection.getConnection();
                //statement to remove guest from database
                PreparedStatement removeGuestStatement = connection.prepareStatement(sqlRemove);
                removeGuestStatement.setString(1,ControllerGuestInfo.nameString);
                removeGuestStatement.setString(2,ControllerGuestInfo.surnameString);
                removeGuestStatement.executeUpdate();

                //setting room to free
                PreparedStatement updateRoomStatement = connection.prepareStatement(sqlUpdateRoom);
                updateRoomStatement.setString(1,ControllerGuestInfo.roomString);
                updateRoomStatement.executeUpdate();

                PreparedStatement roomChargeZero = connection.prepareStatement(sqlRoomChargeZero);
                roomChargeZero.setString(1,ControllerGuestInfo.roomString);
                roomChargeZero.executeUpdate();
                connection.close();

                Stage confirmStage = (Stage)delete.getScene().getWindow();
                confirmStage.close();

                Stage infoStage = (Stage)removeGuestButton.getScene().getWindow();
                infoStage.close();
            }catch (SQLException e){
                e.getCause();
            }
        });

        cancel.setOnAction(event -> {
            Stage confirmStage = (Stage)delete.getScene().getWindow();
            confirmStage.close();
        });
        confirmDeleting.setPadding(new Insets(5,0,0,15));

        delete.setMinWidth(110);
        cancel.setMinWidth(110);
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(delete,cancel);
        delete.setStyle("-fx-font-size: 12pt;\n" +
                "    -fx-background-radius: 0;\n" +
                "    -fx-background-color: #050c0c;\n" +
                "    -fx-text-fill: white;");
        cancel.setStyle("-fx-font-size: 12pt;\n" +
                "    -fx-background-radius: 0;\n" +
                "    -fx-background-color: #050c0c;\n" +
                "    -fx-text-fill: white;");
        hBox.setPadding(new Insets(0,0,0,20));


        VBox vBox = new VBox(12.5);
        vBox.setStyle("-fx-background-color: #132227;");
        vBox.getChildren().addAll(confirmDeleting,hBox);
        confirmDeleting.setStyle("-fx-font-size: 10pt;\n" +
                "    -fx-text-fill: white;");


        Scene scene = new Scene(vBox,265,75);
        stage.setScene(scene);

        stage.setTitle("Remove guest");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }
    public void addTransaction() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ViewAddTransaction.fxml"));
        Scene scene = new Scene(root);
        ControllerGuest.stage.setScene(scene);
    }
}

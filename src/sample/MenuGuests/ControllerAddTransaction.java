package sample.MenuGuests;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class ControllerAddTransaction {

    //returns back to information of guest
    public void backToInfo() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ViewGuestInfo.fxml"));
        Scene scene = new Scene(root);
        ControllerGuest.stage.setScene(scene);
    }


    private String typeID = null;
    private String nameOfTransaction = null;
    public void transactionChoise(ActionEvent ActionEvent) {
        //GETTING WHAT KIND OF TRANSACTION USER HAS CHOSEN
        String typeOfTransactionTextButton = ((Button) ActionEvent.getSource()).getText();


        switch (typeOfTransactionTextButton) {
            case "Day  ticket/20$":
                typeID = "1";
                nameOfTransaction = "Fitness 1 day";
                break;
            case "2 Days ticket/35$":
                typeID = "2";
                nameOfTransaction = "Fitness 2 days";
                break;
            case "Week ticket/70$":
                typeID = "3";
                nameOfTransaction = "Fitness week";
                break;
            case "Hour reservation/50$":
                nameOfTransaction = "Conference hall";
                typeID = "4";
                break;
        }


//STYLING AND SHOWING STAGE*******************************************************************************************
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/HMS.png"));
        Label paymentMethod = new Label("Chose payment method");
        Button cardButton = new Button("Credit card");
        Button chargeButton = new Button("Room charge");
        paymentMethod.setStyle("-fx-font-size: 10pt;\n" +
                "    -fx-text-fill: white;");
        cardButton.setStyle("-fx-font-size: 12pt;\n" +
                "    -fx-background-radius: 0;\n" +
                "    -fx-background-color: #050c0c;\n" +
                "    -fx-text-fill: white;");
        chargeButton.setStyle("-fx-font-size: 12pt;\n" +
                "    -fx-background-radius: 0;\n" +
                "    -fx-background-color: #050c0c;\n" +
                "    -fx-text-fill: white;");
        chargeButton.setMinWidth(110);
        cardButton.setMinWidth(110);

        paymentMethod.setPadding(new Insets(5,0,0,15));
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(cardButton,chargeButton);
        hBox.setPadding(new Insets(0,0,0,10));


        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(paymentMethod,hBox);
        vBox.setStyle("-fx-background-color: #132227;");

        Scene scene = new Scene(vBox,265,65);
        stage.setScene(scene);

        stage.setTitle("Remove guest");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();


//USER PAY WITH CREDIT CARD******************************************************************************************
        String sqlUpdateBalance = "UPDATE Client SET CurrentBalance = ? WHERE Name = ? AND Surname = ?";
        String sqlGetPrice = "SELECT Price FROM TableTransaction WHERE ID = "+typeID;

        cardButton.setOnAction(event -> {
            //ARRAYLIST ONLY FOR PRICE OF TRANSACTION
            ObservableList<Integer> price;
            try {
                //initialize price list
                price = FXCollections.observableArrayList();
                //create connection
                Connection connection = dbConnection.getConnection();
                //RESULT SET CONTAINING ONLY PRICE
                ResultSet resultSetPrice = connection.createStatement().executeQuery(sqlGetPrice);
                while (resultSetPrice.next()){
                    //SAVING TO 'PRICE' WITH INDEX=0
                    price.add(resultSetPrice.getInt(1));
                }

                //updating balance
                //UPDATE Client SET CurrentBalance = ? WHERE Name = ? AND Surname = ?
                PreparedStatement updateBalanceStatement = connection.prepareStatement(sqlUpdateBalance);
                String updatedBalance = String.valueOf(Integer.parseInt(ControllerGuestInfo.balanceString) - price.get(0));

                updateBalanceStatement.setString(1,updatedBalance);
                updateBalanceStatement.setString(2,ControllerGuestInfo.nameString);
                updateBalanceStatement.setString(3,ControllerGuestInfo.surnameString);
                updateBalanceStatement.executeUpdate();


                //SAVING TRANSACTION TO HISTORY
                String sqlInsertTransaction = "INSERT INTO ListTransaction (Name, Surname, Time,Spent, SpentOn)  \n" +
                        "VALUES (?, ?, ?, ?,?);";
                PreparedStatement insertTransaction = connection.prepareStatement(sqlInsertTransaction);
                insertTransaction.setString(1,ControllerGuestInfo.nameString);
                insertTransaction.setString(2,ControllerGuestInfo.surnameString);
                insertTransaction.setString(3,currentTime());
                insertTransaction.setInt(4,price.get(0));
                insertTransaction.setString(5,nameOfTransaction);
                insertTransaction.executeUpdate();
                connection.close();
                stage.close();
            }catch (SQLException e){
                e.getCause();
            }
        });


//USER SAVES TO ROOM CHARGE******************************************************************************************
        String sqlCurrentRoomCharge = "SELECT RoomCharge FROM Room WHERE Number = "+ControllerGuestInfo.roomString;
        String sqlUpdateRoomCharge = "UPDATE Room SET RoomCharge = ? WHERE Number = ?";

        chargeButton.setOnAction(event -> {
            try {
                //LIST WHERE 0=CURRENT CHARGE AND 1=PRICE OF TRANSACTION
                ObservableList<Integer> charge0price1 = FXCollections.observableArrayList();;
                //create connection
                Connection connection = dbConnection.getConnection();
                //RESULT SET CONTAINING ONLY CURRENT CHARGE OF ROOM WHERE GUEST LIVES
                ResultSet resultRoomCharge = connection.createStatement().executeQuery(sqlCurrentRoomCharge);
                while (resultRoomCharge.next()) {
                    //SAVING THE CHARGE OF ROOM INDEX=0
                    charge0price1.add(resultRoomCharge.getInt(1));
                }

                //RESULT SET CONTAINING ONLY PRICE OF TRANSACTION
                ResultSet resultGetPrice = connection.createStatement().executeQuery(sqlGetPrice);
                while (resultGetPrice.next()){
                    //SAVING THE PRICE OF TRANSACTION TO INDEX=1
                    charge0price1.add(resultGetPrice.getInt(1));
                }
                //UPDATING ROOM CHARGE ([PRICE OF TRANSACTION] + [CURRENT ROOM CHARGE])
                String updatedRoomCharge = String.valueOf(charge0price1.get(1)+charge0price1.get(0));

                //updating room charge
                //UPDATE Room SET RoomCharge = ? WHERE Number = ?
                PreparedStatement updateRoomCharge = connection.prepareStatement(sqlUpdateRoomCharge);
                updateRoomCharge.setString(1,updatedRoomCharge);
                updateRoomCharge.setString(2,ControllerGuestInfo.roomString);
                updateRoomCharge.executeUpdate();

//SAVING TRANSACTION TO HISTORY***************************************************************************************
                String sqlInsertTransaction = "INSERT INTO ListTransaction (Name, Surname, Time,Spent, SpentOn)  \n" +
                        "VALUES (?, ?, ?, ?, ?);";
                PreparedStatement insertTransaction = connection.prepareStatement(sqlInsertTransaction);
                insertTransaction.setString(1,ControllerGuestInfo.nameString);
                insertTransaction.setString(2,ControllerGuestInfo.surnameString);
                insertTransaction.setString(3,currentTime());
                insertTransaction.setInt(4,charge0price1.get(1));
                insertTransaction.setString(5,nameOfTransaction);
                insertTransaction.executeUpdate();

                connection.close();
                //closing the stage
                stage.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        });

    }


    public String currentTime(){
        LocalDateTime now = LocalDateTime.now();
        String isoFormat = DateTimeFormatter.ISO_INSTANT.format(now.toInstant(ZoneOffset.UTC));
        String time = isoFormat.substring(11,16)+" "+isoFormat.substring(8,10)+"."+isoFormat.substring(5,7)+"."
                +isoFormat.substring(0,4);
        return time;
    }
}

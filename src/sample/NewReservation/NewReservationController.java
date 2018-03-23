package sample.NewReservation;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import sample.dbUtil.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class NewReservationController {
    public TextField nameField;
    public TextField surnameField;
    public TextField passportField;
    public DatePicker dateOfArrivalPicker;
    public TextField timeOfArrivalFiled;
    public TextField nightsToStay;
    public TextField creditCardField;
    public TextField cvvField;
    public TextField dateOfExpireField;
    public TextField contactNumberField;
    public TextField takeFromField;
    public Button submitButton;

    public Label nameStatus;
    public Label surnameStatus;
    public Label passportStatus;
    public Label dateStatus;
    public Label timeStatus;
    public Label nightsToStayStatus;
    public Label creditCardStatus;
    public Label cvvStatus;
    public Label dateOfExpireStatus;
    public Label contactNumberStatus;
    public Label takeFromStatus;
    public Label roomStatus;

    private String roomSelected = null;

    public void checkInputData(){
        if(nameField.getText().isEmpty())
            nameStatus.setText("Enter the name");
        if(surnameField.getText().isEmpty())
            surnameStatus.setText("Enter the surname");
        if(passportField.getText().isEmpty())
            passportStatus.setText("Enter the passport number");
        if(dateOfArrivalPicker.getEditor().getText().isEmpty())
            dateStatus.setText("Enter the date of arrival");
        if(timeOfArrivalFiled.getText().isEmpty())
            timeStatus.setText("Enter the time of arrival");
        if(nightsToStay.getText().isEmpty())
            nightsToStayStatus.setText("Enter how long guest will stay in hotel");
        if(creditCardField.getText().isEmpty())
            creditCardStatus.setText("Enter the number of credit card");
        if(cvvField.getText().isEmpty())
            cvvStatus.setText("Enter the CVV");
        if(contactNumberField.getText().isEmpty())
            contactNumberStatus.setText("Enter the contact number");
        if(roomSelected.isEmpty())
            roomStatus.setText("Select the room");

        if(Pattern.matches("[a-zA-Z]+",nameField.getText())) {
            nameStatus.setText("");
            nameStatus.setText("Letters only");
        }
        if(Pattern.matches("[a-zA-Z]+",surnameField.getText())) {
            surnameStatus.setText("");
            surnameStatus.setText("Letters only");
        }



    }
    public void newReservationDone() {
        //INSERTING NEW GUEST INTO THE TABLE
        String sqlInsert = "INSERT INTO Client(ID,Name, Surname,TimeOfArrival, NightsToStay," +
                "Passport,TakeFrom,ContactNumber,RoomID,CreditCardNumber,CVV,MonthOfExpire, YearOfExpire" +
                ",CurrentBalance,timeOfDeparture)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //SETTING STATUS THE ROOM TO NOT FREE
        String sqlUpdateRoom = "UPDATE Room SET Free = ? WHERE Number = ?";

        //COUNTING HOW MANY GUESTS ARE IN HOUSE
        String sqlGetID = "select count(*) as counter from Client";
        try {
            //connecting to database
            Connection connection = dbConnection.getConnection();

            PreparedStatement statementInsert = connection.prepareStatement(sqlInsert);
            PreparedStatement statementUpdate = connection.prepareStatement(sqlUpdateRoom);
            ResultSet resultSet = connection.createStatement().executeQuery(sqlGetID);
            String idCount = resultSet.getString(1);
            int idContInt = Integer.parseInt(idCount);
            idContInt++;
            //23.03.2018
            statementUpdate.setString(1,"no");
            statementUpdate.setString(2,roomSelected);
            statementUpdate.executeUpdate();

            statementInsert.setString(1, String.valueOf(idContInt));
            statementInsert.setString(2, nameField.getText());
            statementInsert.setString(3, surnameField.getText());
            statementInsert.setString(4, fixDate(dateOfArrivalPicker.getEditor().getText(),timeOfArrivalFiled.getText()));
            statementInsert.setString(5, nightsToStay.getText());
            statementInsert.setString(6, passportField.getText());
            statementInsert.setString(7, takeFromField.getText()+"");
            statementInsert.setString(8, contactNumberField.getText());
            statementInsert.setString(9, roomSelected);
            statementInsert.setString(10, creditCardField.getText());
            statementInsert.setString(11, cvvField.getText());
            statementInsert.setString(12, dateOfExpireField.getText().substring(0, 2));
            statementInsert.setString(13, dateOfExpireField.getText().substring(3, 5));
            statementInsert.setString(14, randomBalance());
            statementInsert.setString(15, "0");

            statementInsert.execute();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String fixDate(String date,String time){
        //23.03.2018 22:00
        //2018-03-23 22:00:00.000
        String[] dateArray = date.split("[.]");
        return dateArray[2]+"-"+dateArray[1]+"-"+dateArray[0]+" "+time+":00.000";
    }
    public String randomBalance(){
        return ((int)(Math.random()*13)+3)+"000";
    }

    public void selectedRoom(ActionEvent actionEvent){
        roomSelected = ((Button)actionEvent.getSource()).getText();
    }
}

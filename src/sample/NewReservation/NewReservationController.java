package sample.NewReservation;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.dbUtil.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewReservationController {
    public TextField nameField;
    public TextField surnameField;
    public TextField idNumberField;
    public DatePicker dateOfArrivalPicker;
    public TextField timeOfArrivalFiled;
    public TextField nightsToStay;
    public TextField creditCardField;
    public TextField cvvField;
    public TextField dateOfExpireField;
    public TextField companyField;
    public TextArea preferencesField;
    public TextField contactNumberField;
    public Button submitButton;

    public void newReservationDone() {
        String sqlInsert = "INSERT INTO Client(ID,Name, Surname,TimeOfArrival, NightsToStay," +
                "NeedsTransfer,TakeFrom,ContactNumber,RoomID,CreditCardNumber,CVV,MonthOfExpire, YearOfExpire" +
                ",CurrentBalance,timeOfDeparture)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String getID = "select count(*) as counter from Client";
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlInsert);
            ResultSet resultSet = connection.createStatement().executeQuery(getID);
            String idCount = resultSet.getString(1);
            int idContInt = Integer.parseInt(idCount);
            idContInt++;
            //23.03.2018

            statement.setString(1, String.valueOf(idContInt));
            statement.setString(2, nameField.getText());
            statement.setString(3, surnameField.getText());
            statement.setString(4, fixDate(dateOfArrivalPicker.getEditor().getText(),timeOfArrivalFiled.getText()));
            statement.setString(5, nightsToStay.getText());
            statement.setString(6, ""); //needs transfer
            statement.setString(7, ""); //take from
            statement.setString(8, contactNumberField.getText());
            statement.setString(9, "405");
            statement.setString(10, creditCardField.getText());
            statement.setString(11, cvvField.getText());
            statement.setString(12, dateOfExpireField.getText().substring(0, 2));
            statement.setString(13, dateOfExpireField.getText().substring(3, 5));
            statement.setString(14, "3000");
            statement.setString(15, "0");

            statement.execute();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //23.03.2018 22:00
    //2018-03-23 22:00:00.000
    public String fixDate(String date,String time){
        String[] dateArray = date.split("[.]");
        return dateArray[2]+"-"+dateArray[1]+"-"+dateArray[0]+" "+time+":00.000";
    }

}

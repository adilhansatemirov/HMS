/*Author's information
* Author: Adilkhan Satemirov
* Email: adilkhansatemirovv@gmail.com
* Phone number: 8(775)216-01-56
*/
package sample.NewReservation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.dbUtil.dbConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class NewReservationController implements Initializable {
    //FIELDS TO INPUT DATA
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
    public Button submitButton;

    //STATUSES TO CONTROL THE CORRECTNESS OF DATA INPUT
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
    public Label roomStatus;

    //LABEL WHEN EVERYTHING IS CORRECT
    public Label successfullyCompletedReservation;

    //ROOMS TO COLOR
    public Button room301;
    public Button room302;
    public Button room303;
    public Button room304;
    public Button room305;
    public Button room306;
    public Button room307;
    public Button room308;
    public Button room401;
    public Button room402;
    public Button room403;
    public Button room404;
    public Button room405;
    public Button room406;
    public Button room501;
    public Button room502;
    public Button room503;
    public Button room504;
    public Button room505;
    public Button room506;
    public Button room601;
    public Button room602;
    public Button room603;
    public Button room604;
    public Button room605;
    public Button room701;
    public Button room702;
    public Button room703;
    public Button room704;
    public Button room705;
    public Button room801;
    public Button room802;
    public Button room803;
    public Button room804;
    public Button room999;


    //SELECTED ROOM
    private String roomSelected = null;

    //CONDITION IS TRUE WHEN DATA INPUT IS CORRECT
    private boolean conditionToSubmit = true;

    //FUNCTION TO CHECK DATA
    public void checkData() {
        conditionToSubmit = true;
        checkInput();
        if (conditionToSubmit)
            newReservationDone();
    }

    //CHECKING
    private void checkInput() {
//******IF FIELD IS EMPTY*****************************************************

        //NAME FIELD
        if (nameField.getText().isEmpty()) {
            nameStatus.setText("Enter the name");
            conditionToSubmit = false;
        }else if (!Pattern.matches("[a-zA-Z]+", nameField.getText())) {
            nameStatus.setText("");
            nameStatus.setText("Letters only");
            conditionToSubmit = false;
        }

        //SURNAME FIELD
        if (surnameField.getText().isEmpty()) {
            surnameStatus.setText("Enter the surname");
            conditionToSubmit = false;
        } else if (!Pattern.matches("[a-zA-Z]+", surnameField.getText())) {
            surnameStatus.setText("");
            surnameStatus.setText("Letters only");
            conditionToSubmit = false;
        }

        //PASSPORT FIELD
        if (passportField.getText().isEmpty()) {
            passportStatus.setText("Enter the passport number");
            conditionToSubmit = false;
        } else if (!Pattern.matches("[0-9]+", passportField.getText())) {
            passportStatus.setText("");
            passportStatus.setText("Letters only");
            conditionToSubmit = false;
        }

        //DATE OF ARRIVAL FIELD
        //31.05.2018
        String dateOfArrival = dateOfArrivalPicker.getEditor().getText();
        if (dateOfArrivalPicker.getEditor().getText().isEmpty()) {
            dateStatus.setText("Enter the date of arrival");
            conditionToSubmit = false;
        } else if (!(dateOfArrival.length() == 10
                && ((Integer.parseInt(dateOfArrival.substring(0, 2)) >= 1 && Integer.parseInt(dateOfArrival.substring(0, 2)) <= 31))
                && dateOfArrival.charAt(2) == '.'
                && ((Integer.parseInt(dateOfArrival.substring(3, 5)) >= 1 && Integer.parseInt(dateOfArrival.substring(3, 5)) <= 12))
                && dateOfArrival.charAt(5) == '.'
                && ((Integer.parseInt(dateOfArrival.substring(6)) >= 2018 && Integer.parseInt(dateOfArrival.substring(6)) <= 2022))
                && dateOfArrival.substring(6).length() == 4)){
            dateStatus.setText("");
            dateStatus.setText("format: 22.05.2018");
            dateOfArrivalPicker.getEditor().clear();
            conditionToSubmit = false;
        }

        //20:00
        //TIME OF ARRIVAL FIELD
        if (timeOfArrivalFiled.getText().isEmpty()) {
            timeStatus.setText("Enter the time of arrival");
            conditionToSubmit = false;
        } else if (!(timeOfArrivalFiled.getText().length()==5 &&
                Pattern.matches("[0-9]+",timeOfArrivalFiled.getText().substring(0, 2)) &&
                (Pattern.matches("[0-9]+",timeOfArrivalFiled.getText().substring(3)) &&
                (Integer.parseInt(timeOfArrivalFiled.getText().substring(0, 2)) >= 0 &&
                Integer.parseInt(timeOfArrivalFiled.getText().substring(0, 2)) <= 23 &&
                Integer.parseInt(timeOfArrivalFiled.getText().substring(3)) >= 0 &&
                Integer.parseInt(timeOfArrivalFiled.getText().substring(3)) <= 59 &&
                timeOfArrivalFiled.getText().charAt(2) == ':')))) {
            timeStatus.setText("");
            timeStatus.setText("format: 00:00");
            conditionToSubmit = false;
        }

        //NIGHTS TO STAY FIELD
        if (nightsToStay.getText().isEmpty()) {
            nightsToStayStatus.setText("Enter how long guest will stay in hotel");
            conditionToSubmit = false;
        } else if (!Pattern.matches("[0-9]+", nightsToStay.getText())) {
            nightsToStayStatus.setText("");
            nightsToStayStatus.setText("Numbers only");
            conditionToSubmit = false;
        }

        //CREDIT CARD FIELD
        if (creditCardField.getText().isEmpty()) {
            creditCardStatus.setText("Enter the number of credit card");
            conditionToSubmit = false;
        } else if ((!Pattern.matches("[0-9]+", creditCardField.getText())) ||
                creditCardField.getText().length() != 16) {
            creditCardStatus.setText("");
            creditCardStatus.setText("16 numbers");
            conditionToSubmit = false;
        }

        //CVV FIELD
        if (cvvField.getText().isEmpty()) {
            cvvStatus.setText("");
            cvvStatus.setText("Enter CVV");
            conditionToSubmit = false;
        } else if ((!Pattern.matches("[0-9]+", cvvField.getText())) ||
                cvvField.getText().length() != 3) {
            cvvStatus.setText("");
            cvvStatus.setText("3 numbers");
            conditionToSubmit = false;
        }

        if (contactNumberField.getText().isEmpty()) {
            contactNumberStatus.setText("Enter the contact number");
            conditionToSubmit = false;
        } else if (!Pattern.matches("[0-9]+", contactNumberField.getText())) {
            contactNumberStatus.setText("");
            contactNumberStatus.setText("Numbers only");
            conditionToSubmit = false;
        }

        if (roomSelected == null) {
            conditionToSubmit = false;
            roomStatus.setText("Select the room");
        }

        if (dateOfExpireField.getText().isEmpty()) {
            conditionToSubmit = false;
            dateOfExpireStatus.setText("");
            dateOfExpireStatus.setText("Input date");
        } else if (dateOfExpireField.getText().length()!=5 ||
                (!Pattern.matches("[0-9]+", dateOfExpireField.getText().substring(0, 2))) ||
                (!Pattern.matches("[0-9]+", dateOfExpireField.getText().substring(3))) ||
                !(Integer.parseInt(dateOfExpireField.getText().substring(0, 2)) >= 1 && Integer.parseInt(dateOfExpireField.getText().substring(0, 2)) <= 12) ||
                (Integer.parseInt(dateOfExpireField.getText().substring(3)) <= 18) || (dateOfExpireField.getText().length() != 5) ||
                (dateOfExpireField.getText().charAt(2) != '/')) {

            dateOfExpireStatus.setText("");
            dateOfExpireStatus.setText("format: MM/YY");
            conditionToSubmit = false;
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
        String sqlGetID = "SELECT count(*) as counter FROM Client";
        try {
            //connecting to database
            Connection connection = dbConnection.getConnection();

            //UPDATING THE ROOM STATUS
            PreparedStatement statementUpdate = connection.prepareStatement(sqlUpdateRoom);
            //SETTING FREE COLUMN TO 'no'
            statementUpdate.setString(1, "no");
            statementUpdate.setString(2, roomSelected);
            statementUpdate.executeUpdate();

            //INSERTING NEW GUEST INTO DATABASE
            PreparedStatement statementInsert = connection.prepareStatement(sqlInsert);

            //WANT TO COUNT PEOPLE IN HOUSE
            ResultSet resultSet = connection.createStatement().executeQuery(sqlGetID);
            //GETTING IT FROM DATA BASE (THE ONLY COLUMN IS COUNTER)
            String idCount = resultSet.getString(1);
            int idContInt = Integer.parseInt(idCount);
            //INCREMENT IT, BECAUSE NEW GUEST ARRIVED
            idContInt++;

            //                                       OUR ID THAT WE COUNTED
            statementInsert.setString(1, String.valueOf(idContInt));
            statementInsert.setString(2, nameField.getText());
            statementInsert.setString(3, surnameField.getText());
            //                                       FIXES THE FORMAT OF THE DATE
            statementInsert.setString(4, fixDate(dateOfArrivalPicker.getEditor().getText(), timeOfArrivalFiled.getText()));
            statementInsert.setString(5, nightsToStay.getText());
            statementInsert.setString(6, passportField.getText());
            statementInsert.setString(7, "");
            statementInsert.setString(8, contactNumberField.getText());
            statementInsert.setString(9, roomSelected);
            statementInsert.setString(10, creditCardField.getText());
            statementInsert.setString(11, cvvField.getText());
            statementInsert.setString(12, dateOfExpireField.getText().substring(0, 2));
            statementInsert.setString(13, dateOfExpireField.getText().substring(3, 5));
            //                                       ACTUALLY WE ARE SUPPOSED TO GET INFO FROM
            //                                       THE CARD OF THE GUEST AND LEARN HOW MUCH MONEY HE HAS,
            //                                       BUT WE CAN'T, SO WE MAKE RANDOM BALANCE FROM (3 000 to 15 000)
            statementInsert.setString(14, randomBalance());

            statementInsert.setString(15, "0");

            statementInsert.execute();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //EVERYTHING TO EMPTY
        nameField.setText("");
        surnameField.setText("");
        passportField.setText("");
        dateOfArrivalPicker.getEditor().setText("");
        timeOfArrivalFiled.setText("");
        nightsToStay.setText("");
        creditCardField.setText("");
        cvvField.setText("");
        dateOfExpireField.setText("");
        contactNumberField.setText("");

        nameStatus.setText("");
        surnameStatus.setText("");
        passportStatus.setText("");
        dateStatus.setText("");
        timeStatus.setText("");
        nightsToStayStatus.setText("");
        creditCardStatus.setText("");
        cvvStatus.setText("");
        dateOfExpireStatus.setText("");
        contactNumberStatus.setText("");


        successfullyCompletedReservation.setText("Successfully saved");

    }

    public String fixDate(String date, String time) {
        //WE HAVE: 23.03.2018 22:00
        //WE NEED: 2018-03-23 22:00:00.000
        String[] dateArray = date.split("[.]");
        return dateArray[2] + "-" + dateArray[1] + "-" + dateArray[0] + " " + time + ":00.000";
    }

    //RANDOM BALANCE FROM 3 000 TO 15 000
    public String randomBalance() {
        return ((int) (Math.random() * 13) + 3) + "000";
    }

    private ObservableList<Integer> roomNumber = FXCollections.observableArrayList();
    private ObservableList<String> roomFree = FXCollections.observableArrayList();


    //WHEN CLICKED 'NEW RESERVATION'

    //LIST OF ALL ROOMS' NUMBERS
    private ArrayList<Integer> allRooms = new ArrayList<>();
    //LIST OF ALL ROOMS BUTTONS(ON THE SCREEN)
    private ArrayList<Button> allRoomsButtons = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = dbConnection.getConnection();

            //LISTING ALL THE ROOMS IN HOTEL
            addAllRoomsToList();

            //ADDING BUTTONS TO ARRAYLIST 'allRoomsButtons'
            addRoomsButtons();

            //GETTING NUMBER OF ROOM AND STATUS 'FREE' OR NOT
            String sqlGetFree = "SELECT Number, Free FROM Room";
            ResultSet resultSetOfRooms = connection.createStatement().executeQuery(sqlGetFree);

            //GO THROUGH ALL THE RESULTS
            while (resultSetOfRooms.next()) {
                //ADDING NUMBER OF ROOM TO OBSERVABLE ARRAYLIST 'roomNumber'
                roomNumber.add(resultSetOfRooms.getInt(1));
                //ADDING STATUS TO 'roomFree' ARRAYLIST
                roomFree.add(resultSetOfRooms.getString(2));
            }

            //GO THROUGH ALL INDEXES OF THE ROOMS
            for (int room = 0; room < roomFree.size(); room++) {
                //IF IT IS FREE WE GET THIS ROOM FROM ARRAYLIST AND CHANGE IT'S COLOR TO GREEN
                if (roomFree.get(room).equals("yes")) {
                    allRoomsButtons.get(room).setStyle("-fx-background-color: #0f9d58;");
                } else {
                    //IF NOT CHANGE COLOR TO RED
                    allRoomsButtons.get(room).setStyle("-fx-background-color: #ea4335;");
                }
            }

            //CLOSING THE CONNECTION
            connection.close();
            resultSetOfRooms.close();
        } catch (SQLException e) {
            e.getCause();
        }
    }

    public void selectedRoom(ActionEvent actionEvent) {
        //IF NO ROOM WAS SELECTED
        if (roomSelected == null) {
            //NUMBER OF ROOM WE GET BY GETTING THE TEXT ON IT
            roomSelected = ((Button) actionEvent.getSource()).getText();

            if (roomFree.get(allRooms.indexOf(Integer.parseInt(roomSelected))).equals("yes")) {
                //AND CHANGE IT TO BLUE
                ((Button) actionEvent.getSource()).setStyle("-fx-background-color: #4683eb;");
                roomStatus.setText("");
            } else {
                conditionToSubmit = false;
                roomStatus.setText("");
                roomStatus.setText("Room is busy");
                roomSelected = null;
            }
        } else {
            //IF SOME ROOM HAS BEEN SELECTED
            //WE GO THROUGH ALL THE ROOMS
            for (int index = 0; index < roomFree.size(); index++) {
                //IF FREE -> TO GREEN
                if (roomFree.get(index).equals("yes")) {
                    allRoomsButtons.get(index).setStyle("-fx-background-color: #0f9d58;");
                } else {
                    //ELSE -> TO RED
                    allRoomsButtons.get(index).setStyle("-fx-background-color: #ea4335;");
                }
            }
            //SELECTED -> TO BLUE
            roomSelected = ((Button) actionEvent.getSource()).getText();

            if (roomFree.get(allRooms.indexOf(Integer.parseInt(roomSelected))).equals("yes")) {
                ((Button) actionEvent.getSource()).setStyle("-fx-background-color: #4683eb;");
                roomStatus.setText("");
            } else {
                conditionToSubmit = false;
                roomStatus.setText("");
                roomStatus.setText("Room is busy");
                roomSelected = null;
            }
        }
    }

    private void addAllRoomsToList() {
        for (int index = 301; index <= 308; index++) {
            allRooms.add(index);
        }
        for (int index = 401; index <= 406; index++) {
            allRooms.add(index);
        }
        for (int index = 501; index <= 506; index++) {
            allRooms.add(index);
        }
        for (int index = 601; index <= 605; index++) {
            allRooms.add(index);
        }
        for (int index = 701; index <= 705; index++) {
            allRooms.add(index);
        }
        for (int index = 801; index <= 804; index++) {
            allRooms.add(index);
        }
        allRooms.add(999);
    }

    private void addRoomsButtons() {
        allRoomsButtons.add(room301);
        allRoomsButtons.add(room302);
        allRoomsButtons.add(room303);
        allRoomsButtons.add(room304);
        allRoomsButtons.add(room305);
        allRoomsButtons.add(room306);
        allRoomsButtons.add(room307);
        allRoomsButtons.add(room308);
        allRoomsButtons.add(room401);
        allRoomsButtons.add(room402);
        allRoomsButtons.add(room403);
        allRoomsButtons.add(room404);
        allRoomsButtons.add(room405);
        allRoomsButtons.add(room406);
        allRoomsButtons.add(room501);
        allRoomsButtons.add(room502);
        allRoomsButtons.add(room503);
        allRoomsButtons.add(room504);
        allRoomsButtons.add(room505);
        allRoomsButtons.add(room506);
        allRoomsButtons.add(room601);
        allRoomsButtons.add(room602);
        allRoomsButtons.add(room603);
        allRoomsButtons.add(room604);
        allRoomsButtons.add(room605);
        allRoomsButtons.add(room701);
        allRoomsButtons.add(room702);
        allRoomsButtons.add(room703);
        allRoomsButtons.add(room704);
        allRoomsButtons.add(room705);
        allRoomsButtons.add(room801);
        allRoomsButtons.add(room802);
        allRoomsButtons.add(room803);
        allRoomsButtons.add(room804);
        allRoomsButtons.add(room999);
    }
}
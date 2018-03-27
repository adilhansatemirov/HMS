package sample.MenuGuests;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.ModelGuest;
import sample.dbUtil.dbConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GuestController implements Initializable{
    public TableView<ModelGuest> tableGuests;
    public TableColumn<ModelGuest, String> nameColumn;
    public TableColumn<ModelGuest, String> surnameColumn;
    public TableColumn<ModelGuest, String> roomColumn;
    public TableColumn<ModelGuest, String> leavesOnColumn;
    public TableColumn<ModelGuest, String> numberColumn;
    public TableColumn<ModelGuest, String> balanceColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dataBaseConnection = new dbConnection();
        loadGuestData();
    }
    public void newReservation() {
        try {
            Stage stage = new Stage();
            stage.setWidth(701);
            Parent root = FXMLLoader.load(getClass().getResource("../NewReservation/NewReservation.fxml"));
            stage.setTitle("New reservation");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
    private dbConnection dataBaseConnection;
    private ObservableList<ModelGuest> dataOfGuest;
    private String sqlRequest = "SELECT * FROM Client;";

    public void loadGuestData(){
        try {
            Connection connection = dbConnection.getConnection();
            this.dataOfGuest = FXCollections.observableArrayList();

            ResultSet resultSet = connection.createStatement().executeQuery(sqlRequest);
            while (resultSet.next()){
                dataOfGuest.add(new ModelGuest(
                        resultSet.getString(1), //ID
                        resultSet.getString(2), //name
                        resultSet.getString(3), //surname
                        resultSet.getString(4), //timeOfArrival
                        resultSet.getString(5), //nightsToStay
                        resultSet.getString(6), //needsTransfer
                        resultSet.getString(7), //takeFrom
                        resultSet.getString(8), //contactNumber
                        resultSet.getString(9), //roomID
                        resultSet.getString(10),//creditCardNumber
                        resultSet.getString(11),//CVV
                        resultSet.getString(12),//monthOfExpire
                        resultSet.getString(13),//yearOfExpire
                        resultSet.getString(14),//currentBalance
                        resultSet.getString(15)));//timeOfDeparture
            }
        }catch (SQLException e){
            System.err.println("Error" + e);
        }

        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        this.roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        this.leavesOnColumn.setCellValueFactory(new PropertyValueFactory<>("timeOfDeparture"));
        this.numberColumn.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        this.balanceColumn.setCellValueFactory(new PropertyValueFactory<>("currentBalance"));

        this.tableGuests.setItems(null);
        this.tableGuests.setItems(dataOfGuest);
    }
}

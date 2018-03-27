package sample.MenuHome;

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

public class HomeController implements Initializable{
    public TableView<ModelGuest> tableGuests;
    public TableColumn<ModelGuest, String> nameColumn;
    public TableColumn<ModelGuest, String> surnameColumn;
    public TableColumn<ModelGuest, String> contactNumberColumn;

    public TableView<ModelGuest>  transactionTable;
    public TableColumn<ModelGuest, String> transactionColumn;
    public TableColumn<ModelGuest, String> guestColumn;
    public TableColumn<ModelGuest, String> spentColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dataBaseConnection = new dbConnection();
        loadGuestData();
        loadTransactionsData();
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
            dataOfGuest = FXCollections.observableArrayList();

            ResultSet resultSet = connection.createStatement().executeQuery(sqlRequest);
            while (resultSet.next()){
                dataOfGuest.add(new ModelGuest(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getString(14),
                        resultSet.getString(15)));
            }
        }catch (SQLException e){
            System.err.println("Error" + e);
        }
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        this.contactNumberColumn.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));

        this.tableGuests.setItems(null);
        this.tableGuests.setItems(dataOfGuest);
    }
    public void loadTransactionsData(){
        try {
            Connection connection = dbConnection.getConnection();
            this.dataOfGuest = FXCollections.observableArrayList();

            ResultSet resultSet = connection.createStatement().executeQuery(sqlRequest);
            while (resultSet.next()){
                dataOfGuest.add(new ModelGuest(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getString(14),
                        resultSet.getString(15)));
            }
        }catch (SQLException e){
            System.err.println("Error" + e);
        }
        this.transactionColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.guestColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        this.spentColumn.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));

        this.transactionTable.setItems(null);
        this.transactionTable.setItems(dataOfGuest);
    }
}

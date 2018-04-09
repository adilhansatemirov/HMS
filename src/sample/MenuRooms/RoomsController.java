package sample.MenuRooms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ModelRoom;
import sample.dbUtil.dbConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RoomsController implements Initializable{
    public TableView<ModelRoom> tableRooms;
    public TableColumn<ModelRoom,Integer> numberOfRoomColumn;
    public TableColumn<ModelRoom,String> nameColumn;
    public TableColumn<ModelRoom,String> surnameColumn;
    public TableColumn<ModelRoom,String> typeColumn;
    public TableColumn<ModelRoom,Integer> roomChargeColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataBaseConnection = new dbConnection();
        loadRoomsData();
    }

    private dbConnection dataBaseConnection;
    private ObservableList<ModelRoom> dataOfRoom;
    private String sqlRequest =
            "  SELECT RoomID, Name, Surname, spr_roomTypes.Type, Room.RoomCharge  \n" +
            "  FROM Client \n" +
            "  JOIN Room JOIN spr_roomTypes \n" +
            "  WHERE Name IS NOT NULL AND Surname IS NOT NULL " +
            "  AND RoomID = Room.Number AND spr_roomTypes.ID = Room.RoomTypeID \n" +
            "  ORDER BY RoomID;";


    public void loadRoomsData(){
        try {
            Connection connection = dbConnection.getConnection();
            dataOfRoom = FXCollections.observableArrayList();

            ResultSet resultSet = connection.createStatement().executeQuery(sqlRequest);
            while (resultSet.next()){
                dataOfRoom.add(new ModelRoom(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        numberOfRoomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        roomChargeColumn.setCellValueFactory(new PropertyValueFactory<>("roomCharge"));

        tableRooms.setItems(null);
        tableRooms.setItems(dataOfRoom);
    }
}

package sample.MenuTransactions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.MenuGuests.ControllerGuestInfo;
import sample.ModelTransaction;
import sample.dbUtil.dbConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class TransactionController implements Initializable {
    public TableView<ModelTransaction> tableTransaction;
    public TableColumn<ModelTransaction, String> nameColumn;
    public TableColumn<ModelTransaction, String> surnameColumn;
    public TableColumn<ModelTransaction, String> timeColumn;
    public TableColumn<ModelTransaction, Integer> spentColumn;
    public TableColumn<ModelTransaction, String> spentOnColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataBaseConnection = new dbConnection();
        loadTransaction();
    }

    private dbConnection dataBaseConnection;
    private ObservableList<ModelTransaction> dataTransaction;
    private String sqlTransaction = "SELECT * FROM ListTransaction";

    public void loadTransaction() {
        try {
            Connection connection = dbConnection.getConnection();
            dataTransaction = FXCollections.observableArrayList();

            ResultSet resultSetTransaction = connection.createStatement().executeQuery(sqlTransaction);
            while (resultSetTransaction.next()) {
                dataTransaction.add(new ModelTransaction(resultSetTransaction.getString(1),
                        resultSetTransaction.getString(2),
                        resultSetTransaction.getString(3),
                        resultSetTransaction.getInt(4),
                        resultSetTransaction.getString(5)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        spentColumn.setCellValueFactory(new PropertyValueFactory<>("spent"));
        spentOnColumn.setCellValueFactory(new PropertyValueFactory<>("spentOn"));

        tableTransaction.setItems(null);
        tableTransaction.setItems(dataTransaction);
    }

    public void confirmClearHistory(){
//STYLING AND SHOWING THE STAGE**************************************************************************************
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/HMS.png"));
        Label confirmDeleting = new Label("Clear history of transactions?");
        Button delete = new Button("Clear");
        Button cancel = new Button("Cancel");
        delete.setMinWidth(110);
        cancel.setMinWidth(110);
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(delete,cancel);
        confirmDeleting.setPadding(new Insets(5,0,0,15));
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

//IF CLICKED 'CLEAR' CLEAR*******************************************************************************************
        delete.setOnAction(event -> {
            clearHistoryOfTransactions();
            stage.close();
        });
        cancel.setOnAction(event -> stage.close());
    }

    public void clearHistoryOfTransactions(){
        String sqlDeleteHistory = "DELETE FROM ListTransaction;";
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement removeGuestStatement = connection.prepareStatement(sqlDeleteHistory);
            removeGuestStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

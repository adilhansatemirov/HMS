package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//IMPORT OF THE PACKAGE TO CONNECT TO DATABASE
import sample.dbUtil.dbConnection;

class ModelLogIn {
    //CONNECTION TO DATABASE
    private Connection connection;

    ModelLogIn() {
        try {
            this.connection = dbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (this.connection == null) {
            System.exit(1);
        }
    }


    //CHECKING THE CONNECTION TO BE NOT NULL
    public boolean databaseIsConnected() {
        return this.connection != null;
    }


    //METHOD THAT GOES TO DATABASE AND CHECKS WHETHER INPUT DATA IS CORRECT
    public boolean isLogin(String user, String password) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sqlRequest = "SELECT * from LogIn where Username = ? and Password = ?";

        try {
            statement = this.connection.prepareStatement(sqlRequest);
            statement.setString(1, user);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            {
                statement.close();
                resultSet.close();
            }
        }
        return false;
    }
}

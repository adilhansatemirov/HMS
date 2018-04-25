/*Author's information
* Author: Adilkhan Satemirov
* Email: adilkhansatemirovv@gmail.com
* Phone number: 8(775)216-01-56
*/
package sample.dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//CLASS FOR CONNECTION TO DATABASE
public class dbConnection {
    private static final String SQLCONNECTION = "jdbc:sqlite:Client.sqlite";

    public static Connection getConnection() throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQLCONNECTION);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}

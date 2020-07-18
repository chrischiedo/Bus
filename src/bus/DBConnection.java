package bus;

/**
 *
 * @author chiedo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getDBConnection() throws SQLException {
        Connection connection = null;

        try {

            String url = "jdbc:mysql://localhost/bus?useSSL=false";
            String user = "root";
            String password = "chiedo7saleh";

            connection = DriverManager.getConnection(url, user, password);

            return connection;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }//try catch closed
    }//getDBConnection() closed
}//class closed

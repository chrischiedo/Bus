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

        // Note that using a plaintext password like in this case isn't a good practice!
        // Did this for local testing ONLY! It is highly recommended that a hash of the
        // password is used instead, especially in production code.
        try {
            connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost/bus?useSSL=false",
                        "root",
                        "chiedo7saleh");
            return connection;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }//try catch closed
    }//getDBConnection() closed
}//class closed

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
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bus?useSSL=false", "root",
                    "chiedo7saleh"); // Note that using a plaintext password like this isn't a good practice!
                                              // Did this for local testing ONLY! Consider hashing the password in
                                              // production.
            return connection;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }//try catch closed
    }//getDBConnection() closed
}//class closed

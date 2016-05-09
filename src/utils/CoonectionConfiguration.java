package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CoonectionConfiguration {
    private final static String DBUSER = "root";
    private final static String DBPASS = "root";
    private final static String DBNAME = "esisba";


    public static Connection getConnection(){
        Connection connect = null;
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/"+DBNAME+"?"
                    + "user="+DBUSER+"&password="+DBPASS+"&useSSL=false");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Got an exception connecting to DB!");
            e.printStackTrace();
        }
        return connect;
    }

}

package beans;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    /** JDBC driver name. */
    public static Connection getDatabaseConnection()
    {
        Connection connection = null;
        
    	String connectionString =
            "jdbc:sqlserver://g1q9mbxvwk.database.windows.net:1433;" 
            + "database=tutors;"
            + "user=tutoradmin@g1q9mbxvwk;"
            + "password=Tutors@JMU26;"
            + "encrypt=true;"
            + "trustServerCertificate=false;"
            + "hostNameInCertificate=*.database.windows.net;"
            + "loginTimeout=30;"; 

        try {
            connection = DriverManager.getConnection(connectionString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
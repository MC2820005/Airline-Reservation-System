import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private static final String database_path = "jdbc:postgresql://localhost:5432/airline_db";
    private static final String user = "postgres";
    private static final String password = "Salah5244.";

    public static Connection getDatabaseConnection(){ // returns the database connection
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(database_path,user,password);
        }
        catch(SQLException e){
            // Couldnt connect to database 
        }
        return connection;
    } 
}

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        Connection connection = DatabaseConnection.getDatabaseConnection();
        if(connection != null){
            System.out.println("Connected to the Database");
        }
        else{
            System.out.println("Failed to connect to the Database");
        }
    }

}

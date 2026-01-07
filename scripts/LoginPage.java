import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginPage{

    public Scene getLoginPageScene(Stage stage){
        // Setting up the UI
        Label title = new Label("Airline Reservation System");
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: black;");
        title.setMaxWidth(Double.MAX_VALUE);      
        title.setAlignment(Pos.CENTER);  
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button loginButton = new Button("Login");
        Label messageLabel = new Label(); 

        // Logic to be run upon clicking the login button
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if(EnsureValidCredentials(username,password,messageLabel)){
                // add these entries into the database or find those entries in there
                Integer user_id = getUserId(username, password);
                if(NewUserLogin(username,password)){
                    InsertUser(username, password,messageLabel);
                    user_id = getUserId(username, password);
                    messageLabel.setText("Created New Account Successfully!");
                }
                else{
                    messageLabel.setText("Logged in Successfully.");
                }
                // Move to the next screen once user has logged in

                SearchFlightsPage flights_page = new SearchFlightsPage(user_id);
                stage.setScene(flights_page.getSearchFlightsPage(stage));
            }
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(title,usernameField,passwordField,loginButton,messageLabel);
        VBox.setMargin(usernameField, new Insets(60, 0, 0, 0)); // ensures some distance between title and the other elements
        // Ensuring that we display the UI 
        Scene scene = new Scene(layout,1000,750);
        stage.setTitle("Login Page");
        return scene;
    }

    public boolean EnsureValidCredentials(String username, String password,Label messageLabel){
            if(username.isEmpty() || password.isEmpty()){
                messageLabel.setText("Please enter username and password");
                return false;
            } else if(username.length() < 20 && password.length() < 20){
                return true;
            }
            messageLabel.setText("Note both username and password length must be less than 20 characters");
            return false;
    }

    public boolean NewUserLogin(String username, String password){
        String sql = "SELECT id FROM users_info WHERE username = ? AND password = ?"; // the ? can take any value
        try(Connection connection = DatabaseConnection.getDatabaseConnection(); 
            PreparedStatement statement =  connection.prepareStatement(sql);){

            statement.setString(1,username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            return !(result.next()); // result.next() returns true if there is a row present
            // in the result and false otherwise
        }
        catch(Exception e){
            e.printStackTrace();
            return false; // dont assume new user on error
        }
    }

    public void InsertUser(String username, String password, Label messageLabel){
        String sql = "INSERT INTO users_info (username,password) VALUES (?,?)"; // ? are placeholder values can take any value
        try(Connection connection = DatabaseConnection.getDatabaseConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){

            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
        }
        catch(Exception e){
            messageLabel.setText("Unable to create a new account");
        }
    }

    public Integer getUserId(String username, String password) { // so we can pass it between pages and use it when booking 
    String sql = "SELECT id FROM users_info WHERE username = ? AND password = ?"; // flights

    try (Connection connection = DatabaseConnection.getDatabaseConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return result.getInt("id");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
}

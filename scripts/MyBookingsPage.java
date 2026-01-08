import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MyBookingsPage {

    private int user_id; // (We always need the user_id in each page they are on!)

    public MyBookingsPage(int user_id) {
        this.user_id = user_id;
    }

    public Scene getMyBookingsPage(Stage stage){
        Label title = new Label("My Booked Flights:");
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: black;");
        title.setMaxWidth(Double.MAX_VALUE);      
        title.setAlignment(Pos.CENTER);
        Button back_button = new Button("Back");
        BorderPane root = new BorderPane();

        VBox bookingsBox = new VBox(10);
        bookingsBox.setPadding(new Insets(20));
        bookingsBox.setAlignment(Pos.TOP_CENTER); // putting it under the tableView
        // so user can read the flightnum and then type it in to book a flight

        // Logic for switching back to the SearchFlightsPage upon clicking the back button
        back_button.setOnAction(e -> {
            SearchFlightsPage flights_page = new SearchFlightsPage(user_id);
            stage.setScene(flights_page.getSearchFlightsPage(stage));
        });

        // Query the database for this user's bookings
        String sql = "SELECT flight_number, booking_date, adult_tickets, child_tickets FROM reservations"
        + " WHERE user_id = ? ORDER BY booking_date ASC";

        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, user_id); // for this particular user
            ResultSet results = statement.executeQuery();

            boolean hasBookings = false;
            while (results.next()) {
                hasBookings = true;
                String flight_num = results.getString("flight_number");
                String booking_date = results.getString("booking_date");
                int adult_tickets = results.getInt("adult_tickets");
                int child_tickets = results.getInt("child_tickets");

                Label bookingLabel = new Label("Flight Number: " + flight_num + " | Booked on: " + booking_date);
                bookingLabel.setStyle("-fx-text-fill: blue; -fx-underline: true; -fx-font-size: 16px;"); // clickable style

                // When the label is clicked then we display the flight details for the user to view
                bookingLabel.setOnMouseClicked(e -> showFlightDetails(flight_num, adult_tickets, child_tickets)); 
                // need flightnumber to query and get the flight information

                bookingsBox.getChildren().add(bookingLabel); // UI Setup
            }

            if (!hasBookings) {
                bookingsBox.getChildren().add(new Label("You have no bookings yet."));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            bookingsBox.getChildren().add(new Label("Failed to load bookings."));
        }

        HBox top_bar = new HBox(back_button); // For our back button
        top_bar.setPadding(new Insets(10));
        top_bar.setAlignment(Pos.TOP_LEFT);
        


        VBox layout = new VBox(15, title, bookingsBox);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        root.setTop(top_bar); // for the back button 
        root.setCenter(layout);

        return new Scene(root, 1000, 750);
    }

    private void showFlightDetails(String flightNum, int adult_tickets, int child_tickets) {
        String sql = "SELECT * FROM flights_info WHERE flightnum = ?";
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, flightNum);
            ResultSet result = statement.executeQuery();

            if (result.next()) { // /n means new line (getting the flight information from the database)
                String details = "Flight Number: " + result.getString("flightnum") + "\n" +
                        "Origin: " + result.getString("origin") + "\n" +
                        "Destination: " + result.getString("destination") + "\n" +
                        "Length of Flight: " + result.getDouble("lof") + "\n" +
                        "Departure Date: " + result.getString("departuredate") + "\n" +
                        "Departure Time: " + result.getString("departuretime") + "\n" +
                        "Seats Available: " + result.getInt("availableseats") + "\n" +
                        "Adult Price: £" + result.getInt("adultpriceticket") + "\n" +
                        "Child Price: £" + result.getInt("childpriceticket")+ "\n" +
                        "My Total Cost is: £" + (adult_tickets * result.getInt("adultpriceticket")
                        + (child_tickets * result.getInt("childpriceticket")) + "\n" +
                        "For " + child_tickets + " children and " + adult_tickets + " adults");
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION); // will display this popup of flight information
                alert.setTitle("Flight Details");
                alert.setHeaderText("Details for flight " + flightNum);
                alert.setContentText(details);
                alert.showAndWait();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to fetch flight details");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
}

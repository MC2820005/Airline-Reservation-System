import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.ResultSet;


public class SearchFlightsPage extends Application {

    @Override 
    public void start(Stage stage){
        // Setting up the UI
    
        Label title = new Label("Search Available Flights:");
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: black;");
        title.setMaxWidth(Double.MAX_VALUE);      
        title.setAlignment(Pos.CENTER);
        Label origin_label = new Label("Origin:");
        TextField origin_field = new TextField();
        Label destination_label = new Label("Destination:");
        TextField destination_field = new TextField();
        Label date_label = new Label("Earliest Departure Date:");
        DatePicker departure_date_picker = new DatePicker();
        Label book_label = new Label("Enter Flight Number to Book:");
        TextField flight_number_field = new TextField();
        Button book_button = new Button("Book Flight"); // for booking a particular flight

        VBox booking_box = new VBox(5.0, book_label, flight_number_field, book_button);
        booking_box.setAlignment(Pos.CENTER);
        booking_box.setPadding(new Insets(10)); 
        VBox origin_box = new VBox(5.0, origin_label,origin_field);
        VBox destination_box = new VBox(5.0, destination_label, destination_field);
        VBox date_box = new VBox(5.0, date_label,departure_date_picker);

        HBox filters_row = new HBox(20.0, origin_box, destination_box, date_box);
        filters_row.setAlignment(Pos.CENTER);

        TableView<Flight> tableView = new TableView<>(); // will be used to display the flights

        Button search_button = new Button("Search Flights");

        // Logic for when we want to connect to database to retieve query results (Flights)

        search_button.setOnAction(e -> {
                // getting rid of trailing empty spaces
                String origin = origin_field.getText().trim();
                String destination = destination_field.getText().trim();
                LocalDate selected_date = departure_date_picker.getValue();

                String sql = "SELECT * FROM flights_info WHERE (? = '' OR origin ILIKE '%' || ? || '%') "
                + "AND (? = '' OR destination ILIKE '%' || ? || '%') "
                + (selected_date != null ? "AND departuredate >= ? " : "") + "ORDER BY departuredate, departuretime";
                // Our sql logic for displaying flights,ILIKE is used so user does not need to type the whole name 
                // of the airport in the origin or destination fields
                try(Connection connection = DatabaseConnection.getDatabaseConnection(); 
                    PreparedStatement statement = connection.prepareStatement(sql);){

                        // Filling in the placeholders
                        statement.setString(1, origin);
                        statement.setString(2, origin);
                        statement.setString(3, destination);
                        statement.setString(4, destination);
                        if(selected_date == null){
                                statement.setNull(5, java.sql.Types.DATE);
                        }
                        else{
                                statement.setDate(5, java.sql.Date.valueOf(selected_date));
                        }

                        ResultSet results = statement.executeQuery();

                        tableView.getItems().clear(); // deletes previous results
                        
                        Flight flight = null;

                        while(results.next()){ // iterates through the results of the query
                                flight = new Flight(
                                results.getString("flightnum"),
                                results.getString("origin"),
                                results.getString("destination"),
                                results.getDouble("lof"),
                                results.getString("departuredate"),
                                results.getString("departuretime"),
                                results.getInt("availableseats"),
                                results.getInt("adultpriceticket"),
                                results.getInt("childpriceticket")
                                );

                                tableView.getItems().add(flight); // Add each flight to table for user to view
                        }

                }catch(Exception ex){
                        ex.printStackTrace();
                        // Unable to make connection to database
                }
        });

        Button reservations_button = new Button("My Bookings");

        //Logic for changing to the other page (My Bookings Page)

        reservations_button.setOnAction(e -> {


        });


        // For the search slider when scrolling through flights

        TableColumn<Flight, String> flight_num_col =
                new TableColumn<>("Flight Number");
        flight_num_col.setCellValueFactory(
                new PropertyValueFactory<>("flightNumber")
        );

        TableColumn<Flight, String> origin_col =
                new TableColumn<>("Origin");
        origin_col.setCellValueFactory(
                new PropertyValueFactory<>("origin")
        );

        TableColumn<Flight, String> destination_col =
                new TableColumn<>("Destination");
        destination_col.setCellValueFactory(
                new PropertyValueFactory<>("destination")
        );

        TableColumn<Flight, Double> lof_col =
                new TableColumn<>("Length of Flight");
        lof_col.setCellValueFactory(
                new PropertyValueFactory<>("lengthOfFlight")
        );

        TableColumn<Flight, String> date_col =
                new TableColumn<>("Departure Date");
        date_col.setCellValueFactory(
                new PropertyValueFactory<>("departureDate")
        );

        TableColumn<Flight, String> time_col =
                new TableColumn<>("Departure Time");
        time_col.setCellValueFactory(
                new PropertyValueFactory<>("departureTime")
        );

        TableColumn<Flight, Integer> seats_col =
                new TableColumn<>("Seats Available");
        seats_col.setCellValueFactory(
                new PropertyValueFactory<>("seatsAvailable")
        );

        TableColumn<Flight, Double> adult_price_col =
                new TableColumn<>("Adult Price Ticket");
        adult_price_col.setCellValueFactory(
                new PropertyValueFactory<>("adultPrice")
        );

        TableColumn<Flight, Double> child_price_col =
                new TableColumn<>("Child Price Ticket");
        child_price_col.setCellValueFactory(
                new PropertyValueFactory<>("childPrice")
        );

        tableView.getColumns().addAll(
                flight_num_col,
                origin_col,
                destination_col,
                lof_col,
                date_col,
                time_col,
                seats_col,
                adult_price_col,
                child_price_col
        );

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN); // makes sure all column 
        // names are shown (resized to fit in the space that they are allocated when displaying flights)

        tableView.setPrefHeight(350);

        VBox layout = new VBox(15.0);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().addAll(title,filters_row,search_button,tableView,booking_box);

        HBox top_bar = new HBox(reservations_button);
        top_bar.setAlignment(Pos.TOP_RIGHT);
        top_bar.setPadding(new Insets(10));


        BorderPane root = new BorderPane(); // use this for our "My Bookings" Button as it is top right of our screen
        root.setTop(top_bar);
        root.setCenter(layout);

        Scene scene = new Scene(root, 1000, 750);
        stage.setTitle("Search Flights");
        stage.setScene(scene);
        stage.show();
    }

     public static void main(String[] args) {
        launch(args);
    }
}

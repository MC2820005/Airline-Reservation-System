import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;


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
        Label destination_field = new Label("Destination:");
        TextField destination_field = new TextField();
        Label date_label = new Label("Departure Date:");
        DatePicker departure_date_picker = new DatePicker();
        
        VBox origin_box = new VBox(5, origin_label,origin_field);
        VBox destination_box = new VBox(5, destination_label, destination_field);
        VBox date_box = new VBox(5, date_label,departure_date_picker);

        HBox filters_row = new HBox(20, origin_box, destination_box, date_box);
        filters_row.setAlignment(Pos.CENTER);

        Button search_button = new Button("Search Flights");

        // For the search slider when scrolling through flights

        TableView<Flight> tableView = new TableView<>();

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
        childPriceCol.setCellValueFactory(
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

        tableView.setPrefHeight(350);
    }

     public static void main(String[] args) {
        launch(args);
    }
}

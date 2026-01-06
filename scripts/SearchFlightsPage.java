import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;


public class SearchFlightsPage {

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

        




    }

   






}

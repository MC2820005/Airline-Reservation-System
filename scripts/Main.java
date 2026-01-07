import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {


    @Override 
    public void start(Stage stage){
        LoginPage login_page = new LoginPage();
        stage.setScene(loginpage.getLoginPageScene(stage));
        stage.show();

        if(login_page.MoveToNextPage()){
            SearchFlightsPage search_flights_page = new SearchFlightsPage();
            stage.setScene(search_flights_page.getSearchFlightsPage(stage));
            stage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}

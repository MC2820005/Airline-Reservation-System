import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override 
    public void start(Stage stage){
        LoginPage login_page = new LoginPage(); //  program always starts from the login page
        stage.setScene(login_page.getLoginPageScene(stage));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

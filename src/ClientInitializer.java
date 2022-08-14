import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * @author _ Pathum_Kaleesha 2022-08-10 - 22.07
 * @since - v0.1.0
 **/

public class ClientInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
        
    }
    

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene
                (new Scene(FXMLLoader.load(getClass().getResource("view/ClientForm.fxml"))));
        primaryStage.show();
    }
}

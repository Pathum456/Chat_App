package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;


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
        Parent root = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chat Room Login");
        primaryStage.getIcons().add(new Image("/assets/messages.png"));
        primaryStage.initStyle(StageStyle.DECORATED);
        enableMove(scene, primaryStage);
        primaryStage.show();
    }
    private void enableMove(Scene scene, Stage primaryStage) {
        AtomicReference<Double> xOffset = new AtomicReference<>((double) 0);
        AtomicReference<Double> yOffset = new AtomicReference<>((double) 0);
        scene.setOnMousePressed(event -> {
            xOffset.set(primaryStage.getX() - event.getScreenX());
            yOffset.set(primaryStage.getY() - event.getScreenY());
        });
        //Lambda mouse event handler
        scene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() + xOffset.get());
            primaryStage.setY(event.getScreenY() + yOffset.get());
        });
    }
}

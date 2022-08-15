package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author _ Pathum_Kaleesha 2022-08-10 - 22.07
 * @since - v0.1.0
 **/

public class ServerInitializer{

    private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;
        try {
            serverSocket = new ServerSocket(5000);
            while (true){
                System.out.println("Waiting");
                socket = serverSocket.accept();
                System.out.println("Connected");
                ClientHandler thread = new ClientHandler(socket,clients);
                clients.add(thread);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //@Override
    /*public void start(Stage primaryStage) throws IOException {
       *//* primaryStage.setScene
                (new Scene(FXMLLoader.load(getClass().getResource("view/LoginForm.fxml"))));
        primaryStage.setTitle("User Login Form");
        primaryStage.setMinWidth(664);
       // primaryStage.setMinHeight(390);
        primaryStage.setMaxWidth(670);
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setMaximized(false);
        primaryStage.show();
*//*
    }
*/
}

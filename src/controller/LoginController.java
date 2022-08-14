package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginController {
    public JFXButton btnLogin;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane loginContext;

    Socket accept=null;


    public void initialize() {
        new Thread(() -> {
            try {

                ServerSocket serverSocket = new ServerSocket(5000);
                System.out.println("Server started!");
                accept = serverSocket.accept();
                System.out.println("Client Connected!");
                while (!serverSocket.isClosed()) {
                    InputStreamReader inputStreamReader =
                            new InputStreamReader(accept.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String record = bufferedReader.readLine();
                    /* while(true){if(!record.equals("exit")){*/
                    System.out.println(record);
                    PrintWriter printWriter = new PrintWriter(accept.getOutputStream());


                    printWriter.flush();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    public void LoginOnClickAction(MouseEvent mouseEvent) throws IOException {
        String userName="pathum";
        String password="123";
        Button button= (Button) mouseEvent.getSource();
        if (txtUserName.getText().equals(userName)&& txtPassword.getText().equals(password)){
            Stage stage = (Stage) loginContext.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ClientForm.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            stage = new Stage();
            stage.setScene(scene);
            stage.show();

        }

    }
}

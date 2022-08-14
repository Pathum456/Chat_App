package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;

import javax.xml.soap.Text;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author _ Pathum_Kaleesha 2022-08-10 - 22.15
 * @since - v0.1.0
 **/
public class ServerFormController {


    public ImageView ImgClientProfile;
    public Label clientNameLbl;
    public JFXButton BtnSelectClient;
    public TextArea txtServerPane;
    public JFXTextField txtServerMassege;
    public ImageView imgSendServerMessage;
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
                    txtServerPane.appendText(record);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void chatClientOnAction(MouseEvent mouseEvent) throws IOException {

    }
    public void sendMessage(MouseEvent mouseEvent) throws IOException {
     /*   while(true) {

            } if (!txtServerMassege.getText().equals("exit")) {*/

                PrintWriter printWriter = new PrintWriter(accept.getOutputStream());
                printWriter.println(txtServerMassege.getText());
                txtServerPane.appendText(txtServerMassege.getText());
                printWriter.flush();



    }
}
    

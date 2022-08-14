package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    

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
import java.net.Socket;

/**
 * @author _ Pathum_Kaleesha 2022-08-10 - 22.07
 * @since - v0.1.0
 **/
public class ClientFormController {
    public ImageView ImgClientProfile;
    public Label SeverNameLbl;
    public JFXButton BtnSelectServer;
    public ImageView imgSendChats;
    public JFXTextField txtClientMessage;
    public TextArea txtClientPane;

    Socket socket=null;
    public void initialize() throws IOException {
        new Thread(()->{
            try {
                socket= new Socket("localhost",5000);
                InputStreamReader inputStreamReader =
                        new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String record= bufferedReader.readLine();
                System.out.println(record);
                txtClientPane.appendText(record);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
    public void chatServerOnclick(MouseEvent mouseEvent) {
    }

    public void sendChatsOnClick(MouseEvent mouseEvent) throws IOException {
        while(true) {
            if (!txtClientMessage.getText().equals("exit")) {
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println(txtClientMessage.getText());
                txtClientPane.appendText(txtClientMessage.getText());
                printWriter.flush();
            }
        }
    }
}

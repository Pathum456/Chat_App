package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

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
    public HBox hboxMessage;
    public VBox vboxMessageFlow;

    Socket socket = null;

    public void initialize() throws IOException {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 5000);
                while (true) {
                    InputStreamReader inputStreamReader =
                            new InputStreamReader(socket.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String record = bufferedReader.readLine();
                    System.out.println(record);
                    txtClientPane.appendText(record);

                }
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }

    public void chatServerOnclick(MouseEvent mouseEvent) {
    }

    public void sendChatsOnClick(MouseEvent mouseEvent) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        String messageToSend = txtClientMessage.getText();
        printWriter.println(txtClientMessage.getText());
        if (!txtClientMessage.getText().equals("exit")&& !txtClientMessage.getText().isEmpty()){
            HBox hBox=new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5,5,5,10));
            Text text=new Text(messageToSend);
            TextFlow textFlow=new TextFlow(text);
            textFlow.setStyle("-fx-color:rgb(239,242,255);"
            +"-fx-background-color: rgb(15,125,242);"+
                    "-fx-background-radius: 20px");
            textFlow.setPadding(new Insets(5,10,5,10));
            text.setFill(Color.color(0.934,0.945,0.996));
            hBox.getChildren().add(textFlow);
            vboxMessageFlow.getChildren().add(hBox);
           // txtClientPane.appendText(String.valueOf(text));
            printWriter.flush();
        }


    }

}

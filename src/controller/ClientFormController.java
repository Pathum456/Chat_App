package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static controller.LoginController.username;

/**
 * @author _ Pathum_Kaleesha 2022-08-10 - 22.07
 * @since - v0.1.0
 **/
public class ClientFormController extends Thread {
    public ImageView ImgClientProfile;

    public JFXButton BtnSelectServer;
    public ImageView imgSendChats;
    public JFXTextField txtClientMessage;
    public TextArea txtClientPane;
    public HBox hboxMessage;
    public VBox vboxMessageFlow;
    public Label lblUsername;
    public ImageView imgLoadImage;
    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter printWriter;


    FileChooser fileChooser;
    File filePath;
    URL url;



    public void initialize() {
        connectSocket();
        lblUsername.setText(username);

    }

    private void connectSocket() {
        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Connect With Server");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            printWriter = new PrintWriter(socket.getOutputStream(), true);

            this.start();

        } catch (IOException e) {

        }
    }
    String cmd;
    public void run() {
        try {
            while (true) {
                String msg = bufferedReader.readLine();
                System.out.println("Message : " + msg);
                String[] tokens = msg.split(" ");
                cmd = tokens[0];
                System.out.println("cmd : " + cmd);
                StringBuilder fulmsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fulmsg.append(tokens[i]);
                }
                System.out.println("fullmsg : " + fulmsg);

                System.out.println();
                if (cmd.equalsIgnoreCase(LoginController.username + ":")) {
                    continue;
                } else if (fulmsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                            HBox hBox = new HBox();
                            hBox.setAlignment(Pos.CENTER_RIGHT);
                            hBox.setPadding(new Insets(5, 10, 5, 5));
                            Text text = new Text(msg);
                            TextFlow textFlow = new TextFlow(text);
                            textFlow.setStyle("-fx-color:rgb(239,242,255);"
                                    + "-fx-background-color: rgb(124,252,0);" +
                                    "-fx-background-radius: 10px");
                            textFlow.setPadding(new Insets(5, 0, 5, 5));
                            text.setFill(Color.color(0, 0, 0));
                            hBox.getChildren().add(textFlow);
                            vboxMessageFlow.getChildren().add(hBox);

                        //catch Image
                        //thaama iwr na habii
                        //Image ek allgen ui ekt load weno
                        //image ekth ekk image eke path ekth set weno eeka hda gnn oone


                         //HBox hBox=new HBox();
                         hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.setPadding(new Insets(5, 10, 5, 5));
                            ImageView imageView = new ImageView();
                            Image image = new Image(String.valueOf(fulmsg));
                            imageView.setImage(image);
                            imageView.setFitWidth(150);
                            imageView.setFitHeight(100);
                            VBox vBox = new VBox(imageView);
                            //vBox.setAlignment(Pos.CENTER_LEFT);
                            vBox.setPadding(new Insets(5, 10, 5, 5));
                            vboxMessageFlow.getChildren().add(vBox);


                    }
                });
                //txtClientPane.appendText(msg + "\n");
            }
            bufferedReader.close();
            printWriter.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } /*finally {
            try {
                bufferedReader.close();
                printWriter.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    public void chatServerOnclick(MouseEvent mouseEvent) {
    }

    public void sendChatsOnClick(MouseEvent mouseEvent) {
        String msg = txtClientMessage.getText();
        if (!url.equals("") || !url.equals(null)){
            printWriter.println(path + ": " + url);
            url=null;
        }
            printWriter.println(username+": "+msg);


        //txtClientPane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setPadding(new Insets(5, 5, 5, 10));
        Text text = new Text(msg);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-color:rgb(239,242,255);"
                + "-fx-background-color: rgb(15,125,242);" +
                "-fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        text.setFill(Color.color(0.934, 0.945, 0.996));
        hBox.getChildren().add(textFlow);
       //vboxMessageFlow.setAlignment(Pos.CENTER_RIGHT);
        vboxMessageFlow.getChildren().add(hBox);
        // txtClientPane.appendText(String.valueOf(text));
        printWriter.flush();
        if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
        txtClientMessage.setText("");
        url=null;
    }

    public void chooseImageOnAction(MouseEvent mouseEvent) throws MalformedURLException {
        setImages(mouseEvent);
    }
    public void sendImages(){

    }
    String path="path";

    public void setImages(MouseEvent mouseEvent) throws MalformedURLException {
        /*Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        txtClientMessage.setText(filePath.getPath());
       path=txtClientMessage.getText();
        txtClientMessage.setText(null);
        System.out.println(path);*/

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
       /**//* fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        txtClientMessage.setText(filePath.getPath());
*/
        System.out.println("Load Image Button Pressed");
        fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println("File Was Selected");
             url = file.toURI().toURL();
  /*          System.out.println(url);
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5, 10, 5, 5));
            ImageView imageView = new ImageView();
            Image image = new Image(String.valueOf(url));
            imageView.setImage(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(100);
            VBox vBox = new VBox(imageView);
            vBox.setAlignment(Pos.CENTER_RIGHT);
            vBox.setPadding(new Insets(5, 10, 5, 5));
            vboxMessageFlow.getChildren().add(vBox);

       */
        }
    }

    public void chooseEmojiesOnAction(MouseEvent mouseEvent) {
    }















   /* public void initialize() throws IOException {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 5000);
                while (true) {
                    InputStreamReader inputStreamReader =
                            new InputStreamReader(socket.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String record = bufferedReader.readLine();
                    System.out.println(record);
                 //   txtClientPane.appendText("\n");

                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            HBox hBox=new HBox();
                            hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.setPadding(new Insets(5,10,5,5));
                            Text text=new Text(record);
                            TextFlow textFlow=new TextFlow(text);
                            textFlow.setStyle("-fx-color:rgb(239,242,255);"
                                    +"-fx-background-color: rgb(15,125,242);"+
                                    "-fx-background-radius: 20px");
                            textFlow.setPadding(new Insets(5,0,5,5));
                            text.setFill(Color.color(0.934,0.945,0.996));
                            hBox.getChildren().add(textFlow);
                            vboxMessageFlow.getChildren().add(hBox);
                        }
                    });

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
*/
}


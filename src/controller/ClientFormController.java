package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
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
    public AnchorPane rootEmoji;
    public ImageView emoji1;
    public ImageView emoji11;
    public ImageView emoji2;
    public ImageView emoji12;
    public ImageView emoji3;
    public ImageView emoji13;
    public ImageView emoji4;
    public ImageView emoji14;
    public ImageView emoji5;
    public ImageView emoji15;
    public ImageView emoji6;
    public ImageView emoji16;
    public ImageView emoji7;
    public ImageView emoji17;
    public ImageView emoji8;
    public ImageView emoji18;
    public ImageView emoji9;
    public ImageView emoji19;
    public ImageView emoji10;
    public ImageView emoji20;
    public ImageView imgEmoji;
    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter printWriter;


    FileChooser fileChooser;
    File filePath;
    URL url;
    String []ePath=new String[20];

    public void initialize() {
        connectSocket();
        lblUsername.setText(username);


      //  ePath= {"assets/emojis/1.png"};
        {
            for (int i = 0; i < ePath.length; i++) {
                ePath[i] = "assets/emojis/" + (i + 1) + ".png";
                //System.out.println(ePath[i]);

            }
            System.out.println("Emojis path set to array");
        }
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

    public void run() {
        try {
            while (true) {
                String msg = bufferedReader.readLine();
                System.out.println("Message : " + msg);
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
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
                        /*if (!fulmsg.toString().endsWith(".png") || !fulmsg.toString().endsWith(".jpg") || !fulmsg.toString().endsWith(".jpeg") || !fulmsg.toString().endsWith(".gif")) {
                            System.out.println("text append");
                            hBox.setAlignment(Pos.CENTER_LEFT);
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
                            System.out.println("image Path ekth enoo");
                        }*/

                        if (fulmsg.toString().startsWith("assets/emojis/") ) {
                            System.out.println("Emoji path "+fulmsg);
                            hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.setPadding(new Insets(5, 10, 5, 5));
                            ImageView imageView = new ImageView();
                            Image image = new Image(String.valueOf(fulmsg));
                            imageView.setImage(image);
                            imageView.setFitWidth(50);
                            imageView.setFitHeight(50);
                            VBox vBox = new VBox(imageView);
                            //vBox.setAlignment(Pos.CENTER_LEFT);
                            vBox.setPadding(new Insets(5, 10, 5, 5));
                            vboxMessageFlow.getChildren().add(vBox);
                        }
                        //catch Image
                        //thaama iwr na habii
                        //Image ek allgen ui ekt load weno
                        //image ekth ekk image eke path ekth set weno eeka hda gnn oone
                        //HBox hBox=new HBox();
                        else if (fulmsg.toString().endsWith(".png") || fulmsg.toString().endsWith(".jpg") || fulmsg.toString().endsWith(".jpeg") || fulmsg.toString().endsWith(".gif")) {
                            System.out.println("load image");
                            hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.setPadding(new Insets(5, 10, 5, 5));
                            ImageView imageView = new ImageView();
                            Image image = new Image(String.valueOf(fulmsg));
                            imageView.setImage(image);
                            imageView.setFitWidth(100);
                            imageView.setFitHeight(100);
                            VBox vBox = new VBox(imageView);
                            //vBox.setAlignment(Pos.CENTER_LEFT);
                            vBox.setPadding(new Insets(5, 10, 5, 5));
                            vboxMessageFlow.getChildren().add(vBox);

                        } else {
                            System.out.println("text append");
                            hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.setPadding(new Insets(5, 10, 5, 5));
                            Text text = new Text(msg);
                            TextFlow textFlow = new TextFlow(text);
                            textFlow.setStyle("-fx-color:rgb(239,242,255);"
                                    + "-fx-background-color: rgb(18,203,196);" +
                                    "-fx-background-radius: 10px");
                            textFlow.setPadding(new Insets(5, 0, 5, 5));
                            text.setFill(Color.color(0.934, 0.945, 0.996));
                            text.setFont(Font.font(20));
                            hBox.getChildren().add(textFlow);
                            vboxMessageFlow.getChildren().add(hBox);
                        }


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
    public void sendChatsOnClick(MouseEvent mouseEvent) {
        String msg = txtClientMessage.getText();
        printWriter.println(LoginController.username + ": " + msg);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setPadding(new Insets(5, 5, 5, 10));
        Text text = new Text(msg);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-color:rgb(239,242,255);"
                + "-fx-background-color: rgb(6,82,221);" +
                "-fx-background-radius: 20px;");
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        text.setFill(Color.color(0.934, 0.945, 0.996));
        //text.setStyle("-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%)");
        text.setFont(Font.font("Verdana", FontPosture.ITALIC, 14));
        text.setFont(Font.font(20));
        hBox.getChildren().add(textFlow);
        vboxMessageFlow.setAlignment(Pos.CENTER_RIGHT);
        vboxMessageFlow.getChildren().add(hBox);
        printWriter.flush();
        txtClientMessage.setText("");
        if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
        rootEmoji.setVisible(false);
        imgEmoji.setVisible(true);
        // url=null;
    }

    public void chooseImageOnAction(MouseEvent mouseEvent) throws MalformedURLException {
        setImages(mouseEvent);
    }


    public void setImages(MouseEvent mouseEvent) throws MalformedURLException {


        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Image");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            printWriter.println(username + ": " + file.toURI().toURL());
        }
        /*printWriter.println(username + ": " + file.getPath());*/
        /*txtClientMessage.setText(filePath.getPath());*/
        if (file != null) {
            System.out.println("File Was Selected");
            url = file.toURI().toURL();
            System.out.println(url);
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5, 10, 5, 5));
            ImageView imageView = new ImageView();
            Image image = new Image(String.valueOf(url));
            imageView.setImage(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            VBox vBox = new VBox(imageView);
            vBox.setAlignment(Pos.CENTER_RIGHT);
            vBox.setPadding(new Insets(5, 10, 5, 5));
            vboxMessageFlow.getChildren().add(vBox);
        }
    }

    public void chooseEmojiesOnAction(MouseEvent mouseEvent) {
        // txtClientMessage.setText("U+1F600");
       /* CharSequence problemString="U+1F600";
        String fixedString = Normalizer.normalize(problemString, Normalizer.Form.NFC);
        txtClientMessage.setText(fixedString);
*/
 /*       String s = "\uD83D\uDC7A";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isSurrogate(s.charAt(i))) {
                Integer res = Character.codePointAt(s, i);
                i++;
                sb.append("U+" + Integer.toHexString(res).toUpperCase());
            } else {
                sb.append(s.charAt(i));
            }
            System.out.println(sb.toString());
            System.out.println(sb);
            System.out.println(s);
            txtClientMessage.appendText(s);*/
        // }
        rootEmoji.setVisible(true);
       imgEmoji.setVisible(false);
    }

    public void sendEmojiOnMouseClick(MouseEvent mouseEvent) throws UnsupportedEncodingException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            /*for (int i = 0; i < ePath.length; i++) {
                ePath[i]="assets/emojis/"+(i+1)+".png";
                System.out.println(ePath[i]);

            }*/
            switch (icon.getId()) {
                case "emoji1":
                    byte[] emojiBytes1 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x81};
                    String emojiAsString1 = new String(emojiBytes1, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[0]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[0]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString1);

                    }
                   /* byte[] emojiBytes1 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x91, (byte) 0xba};
                    System.out.println(emojiBytes1);
                    String emojiAsString1= new String(emojiBytes1, StandardCharsets.US_ASCII);
                    txtClientMessage.appendText(emojiAsString1);
                    System.out.println(emojiAsString1);
                    String cp = "\uD83D\uDC7A";
                    byte b[] =cp.getBytes(StandardCharsets.UTF_8);
                    for (int k=0; k<b.length; k++)
                        System.out.printf(" %x ", b[k]);
                    System.out.println();
*/
                    break;
                case "emoji2":
                    byte[] emojiBytes2 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x82};
                    String emojiAsString2 = new String(emojiBytes2, StandardCharsets.UTF_8);

                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[1]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[1]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString2);
                    }
                    break;
                case "emoji3":
                    byte[] emojiBytes3 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x83};
                    String emojiAsString3 = new String(emojiBytes3, StandardCharsets.UTF_8);

                    String ste3="assets/emojis/3.png";
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[2]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[2]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString3);
                    }
                    break;
                case "emoji4":
                    byte[] emojiBytes4 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x84};
                    String emojiAsString4 = new String(emojiBytes4, StandardCharsets.UTF_8);

                    String ste4="assets/emojis/2.png";
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[3]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[3]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString4);
                    }
                    break;
                case "emoji5":
                    byte[] emojiBytes5 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x85};
                    String emojiAsString5 = new String(emojiBytes5, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[4]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[4]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString5);
                    }

                    break;
                case "emoji6":
                    byte[] emojiBytes6 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x86};
                    String emojiAsString6 = new String(emojiBytes6, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[5]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[5]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString6);
                    }
                    break;
                case "emoji7":
                    byte[] emojiBytes7 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x89};
                    String emojiAsString7 = new String(emojiBytes7, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[6]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[6]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);

                    } else {
                        txtClientMessage.appendText(emojiAsString7);
                    }

                    break;
                case "emoji8":
                    byte[] emojiBytes8 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x8A};
                    String emojiAsString8 = new String(emojiBytes8, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[7]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[7]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString8);
                    }
                    break;
                case "emoji9":
                    byte[] emojiBytes9 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x8B};
                    String emojiAsString9 = new String(emojiBytes9, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[8]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[8]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString9);
                    }
                    break;
                case "emoji10":
                    byte[] emojiBytes10 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x8C};
                    String emojiAsString10 = new String(emojiBytes10, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[9]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[9]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString10);}
                    break;
                case "emoji11":
                    byte[] emojiBytes11 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0xAD};
                    String emojiAsString11 = new String(emojiBytes11, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[10]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[10]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString11);}
                    break;
                case "emoji12":
                    byte[] emojiBytes12 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x92};
                    String emojiAsString12 = new String(emojiBytes12, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[11]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[11]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString12);}
                    break;
                case "emoji13":
                    byte[] emojiBytes13 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x93};
                    String emojiAsString13 = new String(emojiBytes13, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[12]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[12]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString13);}
                    break;
                case "emoji14":
                    byte[] emojiBytes14 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x94};
                    String emojiAsString14 = new String(emojiBytes14, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[13]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[13]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString14);}
                    break;
                case "emoji15":
                    byte[] emojiBytes15 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x96};
                    String emojiAsString15 = new String(emojiBytes15, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[14]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[14]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString15);
                    }
                    break;
                case "emoji16":
                    byte[] emojiBytes16 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x98};
                    String emojiAsString16 = new String(emojiBytes16, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[15]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[15]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString16);}
                    break;
                case "emoji17":
                    byte[] emojiBytes17 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x94};
                    String emojiAsString17 = new String(emojiBytes17, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[16]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[16]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString17);}
                    break;
                case "emoji18":
                    byte[] emojiBytes18 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x9A};
                    String emojiAsString18 = new String(emojiBytes18, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[17]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[17]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString18);}
                    break;
                case "emoji19":
                    byte[] emojiBytes19 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x9C};
                    String emojiAsString19 = new String(emojiBytes19, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[18]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[18]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString19);}
                    break;
                case "emoji20":
                    byte[] emojiBytes20 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x9D};
                    String emojiAsString20 = new String(emojiBytes20, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[19]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxMessageFlow.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[19]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString20);}
                    break;
            }
        }



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


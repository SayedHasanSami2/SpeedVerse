package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

import java.net.URL;
import java.util.ResourceBundle;

public class Register implements Initializable{

    @FXML
    ToggleButton register;
    @FXML
    ToggleButton login;
    @FXML
    Button save;
    @FXML
    Button go;
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    Button quit;

    public static ArrayList<String> userList=new ArrayList<String>();
    public static ArrayList<String >passwordList=new ArrayList<String>();
    private static final String filename1="usernames.txt";
    private static final String filename2="passwords.txt";
    static String entered="DefaultName";
    static String usernameRegistered=null;

    public void initialize(URL url, ResourceBundle resourceBundle){


        login.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                username.setDisable(false);
                password.setDisable(false);
                save.setDisable(true);
                go.setDisable(false);
            }
        });

        register.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                go.setDisable(true);
                password.setDisable(false);
                username.setDisable(false);
                save.setDisable(false);
            }
        });

        go.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

                userList.clear();
                passwordList.clear();

                try {
                    String line;
                    BufferedReader br = new BufferedReader(new FileReader(filename1));
                    while (true) {
                        line = br.readLine();
                        if (line == null) break;
                        userList.add(line);
                    }
                    br.close();
                } catch (Exception exception) {
                    System.out.println(exception);
                }

                try {
                    String line;
                    BufferedReader br = new BufferedReader(new FileReader(filename2));
                    while (true) {
                        line = br.readLine();
                        if (line == null) break;
                        passwordList.add(line);
                    }
                    br.close();
                } catch (Exception exception) {
                    System.out.println(exception);
                }

                String user = new String("Admin");
                String pass = "123456789";

                if (username.getText().isEmpty() || password.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incorrect Credentials");
                    alert.setHeaderText("Incorrect Input");
                    alert.setContentText("Input is incomplete.");
                    alert.showAndWait();
                } else {
                    user = username.getText();
                    String entered=null;

                    try {
                        BufferedReader br = new BufferedReader(new FileReader("Entered.txt"));
                        entered=br.readLine();
                        br.close();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                    if(entered!=null&&entered.equals(user)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Incorrect Credentials");
                        alert.setHeaderText("Incorrect Input");
                        alert.setContentText("Invalid UserName");
                        alert.showAndWait();
                    } else {

                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter("Entered.txt"));
                            bw.write(user);
                            bw.close();
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                        Controller.user = user;
                        pass = password.getText();
                        int i = 0;
                        for (i = 0; i < userList.size(); i++) {
                            if (user.equals(userList.get(i)) && pass.equals(passwordList.get(i))) {

                                Controller controller = new Controller();
                                Stage stage = new Stage();

                                try {
                                    controller.start(stage);
                                    stage.centerOnScreen();
                                    FirstWindow.stage.close();
                                } catch (Exception e) {
                                    System.out.println(e);
                                }

                                break;
                            }
                        }
                        if (i == userList.size()) {

                            try {
                                BufferedWriter bw = new BufferedWriter(new FileWriter("Entered.txt"));
                                bw.close();
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }

                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Incorrect Credentials");
                            alert.setHeaderText("Incorrect Input");
                            alert.setContentText("The username and password you provided is not correct.");
                            alert.showAndWait();
                        }

                    }
                }
            }
        });

        save.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

                userList.clear();
                passwordList.clear();

                try{
                    String line;
                    BufferedReader br=new BufferedReader(new FileReader(filename1));
                    while(true){
                        line=br.readLine();
                        if(line==null)break;
                        userList.add(line);
                    }
                    br.close();
                }
                catch(Exception exception){
                    System.out.println(exception);
                }

                try{
                    String line;
                    BufferedReader br=new BufferedReader(new FileReader(filename2));
                    while(true){
                        line=br.readLine();
                        if(line==null)break;
                        passwordList.add(line);
                    }
                    br.close();
                }
                catch(Exception exception){
                    System.out.println(exception);
                }

                String user=new String("Admin");
                String pass="123456789";

                if (username.getText().isEmpty() || password.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incorrect Credentials");
                    alert.setHeaderText("Incorrect Input");
                    alert.setContentText("Input is incomplete.");
                    alert.showAndWait();
                }

                else {

                    user = username.getText();
                    pass = password.getText();
                    int i = 0;
                    for (i = 0; i < userList.size(); i++) {
                        if (user.equals(userList.get(i))) {

                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Incorrect Credentials");
                            alert.setHeaderText("Ooops! Incorrect Input");
                            alert.setContentText("The Username Already Exists");
                            alert.showAndWait();

                            break;
                        }
                    }
                    if (i == userList.size()) {
                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(filename1));
                            BufferedWriter bw1 = new BufferedWriter(new FileWriter(filename2));

                            userList.add(user);
                            passwordList.add(pass);

                            for (int i1 = 0; i1 < userList.size(); i1++) {
                                bw.write(userList.get(i1) + "\n");
                            }

                            for (int i2 = 0; i2 < userList.size(); i2++) {
                                bw1.write(passwordList.get(i2) + "\n");
                            }
                            bw1.close();
                            bw.close();
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Done!!!");
                        alert.setHeaderText("Username is Saved!!!");
                        alert.setContentText("Thank you for registering!!!");
                        alert.showAndWait();

                    }



                }

            }
        });
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FirstWindow.stage.close();
            }
        });
    }


}





package sample;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.WindowEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FirstWindow extends Application {

    public static void main(String[] args)throws Exception {
        launch(args);
    }

    static Stage stage;

    public void start(Stage primaryStage) throws Exception{
        stage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));

        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Register and Login To Play");
        primaryStage.getIcons().add(new Image(FirstWindow.class.getResourceAsStream("logo.png")));
        stage.show();
        stage.setResizable(false);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {



            @Override
            public void handle(WindowEvent event) {

                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("Created.txt"));
                    bw.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("Entered.txt"));
                    bw.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                System.exit(1);
            }
        });

    }
}

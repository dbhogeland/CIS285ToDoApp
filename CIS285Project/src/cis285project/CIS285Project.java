/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cis285project;

/**
 *
 * @author dbh00
 * @autho JCampy
 */

import javafx.application.Application;
// import javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class CIS285Project extends Application {

    static ProjUIController myControllerHandle; // Static global variable myControllerHandler
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("ProjUI.fxml")); // Create a new FXMLLoader Loader and get the JavaFX File
        Parent root = Loader.load();
       
        myControllerHandle = (ProjUIController)Loader.getController();  // Set myConrollerHandle equal to the current instance of the Controller class
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("To do Task Application");
        primaryStage.show();
       
        
    }
    
    public static void main(String[] args) {
        launch(args);
        
        
    }
}

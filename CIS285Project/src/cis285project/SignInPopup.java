/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cis285project;

/**
 *
 * @author Daniel
 * 
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class SignInPopup {
    
    /*
     * Method that when called displays a popup window with text and password fields for the user to sign in
     */
    public void display() {
        Stage popupWindow = new Stage();
        
        popupWindow.initModality(Modality.APPLICATION_MODAL); // Makes it so that when the popup window is visible, the user cannot interact with the main application
        popupWindow.setTitle("Sign In");
        
        Label userIDLbl = new Label("User ID:");
        Label passwordLbl = new Label("Password:");
        TextField userIDTxt = new TextField();
        PasswordField passwordFld = new PasswordField(); // Special text field that "stars out" the entered text
        Button submitBtn = new Button("Submit");
        Button cancelBtn = new Button("Cancel");
        
        
        cancelBtn.setOnAction(e-> popupWindow.close()); // Cancel button action that closes the window and discards any entered data
        
        //Containers for the controls
        HBox hbox1 = new HBox(10);
        HBox hbox2 = new HBox(10);
        HBox hbox3 = new HBox(10);
        VBox vbox = new VBox(8);
        
        hbox1.getChildren().addAll(userIDLbl, userIDTxt);
        hbox2.getChildren().addAll(passwordLbl, passwordFld);
        hbox3.getChildren().addAll(submitBtn, cancelBtn);
        vbox.getChildren().addAll(hbox1, hbox2, hbox3);
        
        hbox1.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);
        hbox3.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        
        Scene popupScene = new Scene(vbox, 275, 175);
        
        popupWindow.setScene(popupScene);
        popupWindow.showAndWait();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cis285project;

/**
 *
 * @author Daniel
 */

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;

public class UserPermissionsPopup {
    
    /*
     * Method that displays a popup window that describes the permissions for the various roles
     */
    public void displayPermissions() {
        Stage permissionsWindow = new Stage();
        
        permissionsWindow.initModality(Modality.APPLICATION_MODAL); // Makes it so that when the popup window is visible, the user cannot interact with the main application
        permissionsWindow.setTitle("User Permissions");
        
        //Text area to display all text
        //Done with multiple appends for readability in IDE, not necessary for functionality
        TextArea permArea = new TextArea();
        permArea.setEditable(false);
        permArea.setWrapText(true);
        permArea.setText("Read - User can only view their tasks.");
        permArea.appendText("\n\nEdit - User can mark their tasks as complete.");
        permArea.appendText("\n\nUpdate - User can update their own contact information.");
        permArea.appendText("\n\nManage - User can create tasks and categories.");
        permArea.appendText("\n\nAdministrator - User can access all features of the application.");
        permArea.appendText("\n\nAll users also have the permissions of the roles above them.");
        
        Scene permScene = new Scene(permArea, 300, 300);
        
        permissionsWindow.setScene(permScene);
        permissionsWindow.showAndWait();
    }    
}

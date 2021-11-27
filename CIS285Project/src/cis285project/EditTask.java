/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cis285project;

import java.time.LocalDate;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author dbh00
 */
public class EditTask {
    
    private Label titleLbl = new Label("Title:");
    private Label shortDescLbl = new Label("Short Description:");
    private Label longDescLbl = new Label("Description:");
    private Label startLbl = new Label("Start:");
    private Label dueLbl = new Label("Due:");
    private Label assignLbl = new Label("Assign to:");
    private Label categoryLbl = new Label("Category:");
    private TextField titleTxt = new TextField();
    private TextField shortTxt = new TextField();
    private TextArea longArea = new TextArea();
    private DatePicker startDp = new DatePicker();
    private DatePicker dueDp = new DatePicker();
    private TextField assignTxt = new TextField();
    public ChoiceBox<String> categoryCh = new ChoiceBox<String>();
    private Button editBtn = new Button("Edit");
    
    
    /*
     * Method to display that edit task window
     */
    public void display(){
        
        Stage editWindow = new Stage();
        
        editWindow.initModality(Modality.APPLICATION_MODAL); // Makes it so that when the popup window is visible, the user cannot interact with the main application
        editWindow.setTitle("Edit Task");
        
        longArea.setPrefWidth(200);
        longArea.setWrapText(true);
        
        editBtn.setOnAction(e-> editInfo());
        
        HBox hbox1 = new HBox(4);
        HBox hbox2 = new HBox(4);
        HBox hbox3 = new HBox(4);
        HBox hbox4 = new HBox(4);
        HBox hbox5 = new HBox(4);
        HBox hbox6 = new HBox(4);
        HBox hbox7 = new HBox(4);
        HBox hbox8 = new HBox(30);
        VBox vbox1 = new VBox(15);
        VBox vbox2 = new VBox(15);
        
        hbox1.getChildren().addAll(titleLbl, titleTxt);
        hbox2.getChildren().addAll(shortDescLbl, shortTxt);
        hbox3.getChildren().addAll(longDescLbl, longArea);
        hbox4.getChildren().addAll(startLbl, startDp);
        hbox5.getChildren().addAll(dueLbl, dueDp);
        hbox6.getChildren().addAll(assignLbl, assignTxt);
        hbox7.getChildren().addAll(categoryLbl, categoryCh);
        vbox1.getChildren().addAll(hbox1, hbox2, hbox3);
        vbox2.getChildren().addAll(hbox4, hbox5, hbox6, hbox7, editBtn);
        hbox8.getChildren().addAll(vbox1, vbox2);
        
        vbox1.setAlignment(Pos.CENTER);
        vbox2.setAlignment(Pos.CENTER);
        hbox8.setAlignment(Pos.CENTER);
        
        Scene editScene = new Scene(hbox8, 600, 300);
        
        editWindow.setScene(editScene);
        editWindow.showAndWait();
    }
    
    /*
     * Method to edit the details that have been entered.
     * This method checks each field to see if there is input. If there is not new information entered,
     * the data will not be updated in the database.
     */
    public void editInfo(){
        // Checks title field
        if(titleTxt.getText() != null && !titleTxt.getText().trim().isEmpty()){
            String newTitle = titleTxt.getText();
            System.out.println("title test");
            // Needs database code to update data
        }
        
        // Checks short description field
        if(shortTxt.getText() != null && !shortTxt.getText().trim().isEmpty()){
            String newShort = shortTxt.getText();
            System.out.println("short test");
            // Needs database code to update data
        }
        
        // Checks long description field
        if(longArea.getText().trim() != ""){
            String newLong = longArea.getText();
            System.out.println("long test");
            // Needs database code to update data
        }
        
        // Checks due date picker
        LocalDate dueDate = dueDp.getValue();
        if(dueDate != null){
            System.out.println("due test");
            // Needs database code to update data
        }
        
        // Checks start date picker
        LocalDate startDate = startDp.getValue();
        if(startDate != null){
            System.out.println("start test");
            // Needs database code to update data
        }
        
        //Checks assigned to field
        if(assignTxt.getText() != null && !assignTxt.getText().trim().isEmpty()){
            String newAssign = assignTxt.getText();
            System.out.println("assign test");
            // Needs database code to update data
        }
        
        // Checks the choicebox selection
        if(categoryCh.getValue() != null){
            String newCat = categoryCh.getValue();
            // Needs database code to update data
        }
        
    }
}

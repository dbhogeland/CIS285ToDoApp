/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cis285project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    private ObservableList<String> categoriesList = FXCollections.observableArrayList();
    
    public void display(){
        
        Stage editWindow = new Stage();
        
        editWindow.initModality(Modality.APPLICATION_MODAL); // Makes it so that when the popup window is visible, the user cannot interact with the main application
        editWindow.setTitle("Edit Task");
        
        Label titleLbl = new Label("Title:");
        Label shortDescLbl = new Label("Short Description:");
        Label longDescLbl = new Label("Description:");
        Label startLbl = new Label("Start:");
        Label dueLbl = new Label("Due:");
        Label assignLbl = new Label("Assign to:");
        Label categoryLbl = new Label("Category:");
        TextField titleTxt = new TextField();
        TextField shortTxt = new TextField();
        TextArea longArea = new TextArea("Long Description");
        DatePicker startDp = new DatePicker();
        DatePicker dueDp = new DatePicker();
        TextField assignTxt = new TextField();
        ChoiceBox<String> categoryCh = new ChoiceBox<String>();
        Button editBtn = new Button("Edit");
        
        longArea.setPrefWidth(200);
        longArea.setWrapText(true);
        categoryCh.setItems(categoriesList);
        
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
    
    public void addToCatList(String cat){
        categoriesList.add(cat);
    }
    
    public void editInfo(){
        
    }
}

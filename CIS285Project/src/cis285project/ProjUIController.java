/*
 * GUI controller class for assigning code to the 
 * ProjUI.fxml program
 * 
 */
package cis285project;

/**
 *
 * @author Jason
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.time.LocalDate;


public class ProjUIController {

    @FXML
    private TextField assignTxtBox; // Text field for assigning a task to a specific user

    @FXML
    private TextField catNameTxtBox; // Text field for creating a new category

    @FXML
    private ChoiceBox<?> categorySelect; // Selecting a category when creating a task

    @FXML
    private Button creatTaskButton; // Button that takes users input and creates a task

    @FXML
    private Button createCatButton; // Button for creating a category

    @FXML
    private DatePicker dueDatePicker; // Task due date picker 

    @FXML
    private TextArea longDescTxtBox; // Text field for the long description of a task

    @FXML
    private TextField shortDescTxtBox; // Text field for the short description of a task

    @FXML
    private DatePicker startDatePicker; // Set the start date of a task

    @FXML
    private TextField titleTxtBox; // Text field for the title of a task

    @FXML
    private Button updateOther; 

    @FXML
    private Button updateUser;
    
    
    String startD;
    String dueD;
    
    public void setStartDate(ActionEvent event) {
        LocalDate startDate = startDatePicker.getValue();
        startD = startDate.toString();
        //System.out.println(startDate);
    }
    
    public void setDueDate(ActionEvent event) {
        LocalDate dueDate = dueDatePicker.getValue();
        dueD = dueDate.toString();
        //System.out.println(dueDate);
    }
    
    
    public void createTask(ActionEvent event) {
        
        //LocalDate startD = startDatePicker.getValue();
        //LocalDate dueD = dueDatePicker.getValue();
       
        Task taskObj = new Task(titleTxtBox.getText(),shortDescTxtBox.getText(),longDescTxtBox.getText(),
                startD,dueD);
        System.out.println(taskObj.getTaskName() + " " + taskObj.getTaskLongDesc() + " " + taskObj.getDueDate() + " " + taskObj.getStartDate());
        
     
}

}

    

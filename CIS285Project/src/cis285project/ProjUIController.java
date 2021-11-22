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
import java.util.HashSet;


public class ProjUIController {

    @FXML
    private TextField assignTxtBox; // Text field for assigning a task to a specific user

    @FXML
    private TextField catNameTxtBox; // Text field for creating a new category

    @FXML
    private ChoiceBox<String> categorySelect; // Selecting a category when creating a task

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
    
    
    private String startD; // String variable for storing start date value
    private String dueD; // String variable for storing due date value
    
    /*
     * Void method which creates a LocalDate object for start date and gets 
     * the value from UI DatePicker and converts it into a string startD
     */
    
    public void setStartDate(ActionEvent event) {
        LocalDate startDate = startDatePicker.getValue();
        startD = startDate.toString();
        //System.out.println(startDate);
    }
    
    /*
     * Void method which creates a LocalDate object for due date and gets 
     * the value from UI DatePicker and converts it into a string dueD
     */
    
    public void setDueDate(ActionEvent event) {
        LocalDate dueDate = dueDatePicker.getValue();
        dueD = dueDate.toString();
        //System.out.println(dueDate);
    }
    
    /*
     * Void method that creates a new object of Task with the parameters 
     * in the constructor under Task.java when the create task button is pressed
     */
    public void createTaskButtonClick(ActionEvent event) {
        
        Task taskObj = new Task(titleTxtBox.getText(),shortDescTxtBox.getText(),longDescTxtBox.getText(),
                startD,dueD);
        
        taskObj.setCategoryTag(categorySelect.getValue()); // Sets the value of variable categoryTag to choicebox selection
        
        // Temporary output statements to make sure input is doing what it needs to
        System.out.println(taskObj.getTaskName() + " " + taskObj.getTaskLongDesc() + " " + taskObj.getDueDate() + " " + taskObj.getStartDate());
        System.out.println(taskObj.getCategoryTag());
        
        clearCreateTaskInfo(); // Calls method that clears textfields and datepickers value when createTaskButton is Clicked
        
    }
    
    /*
     * Void method that creates a category Object with the parameter categoryName
     * when the create category button is pressed
     */
    public void createCatButtonClick(ActionEvent event) {
        Category catObj = new Category(catNameTxtBox.getText());
        System.out.println(catObj.getCategoryName()); // Temporary output statement to verify input
        
        clearCategoryInfo(); // Calls void method clearCategoryInfo and clears the category name text box
        
        categorySelect.getItems().add(catObj.getCategoryName()); // returns the observablelist and adds category objects into it
        
    }
    
    /*
     * Voud method that clears the input values of creatTask textfields and DatePickers
     */
    public void clearCreateTaskInfo() {
        titleTxtBox.clear();
        shortDescTxtBox.clear();
        longDescTxtBox.clear();
        startDatePicker.getEditor().clear();
        dueDatePicker.getEditor().clear();
    }
    
    /*
     * Void method that clears category textfield
     */
    public void clearCategoryInfo() {
        
        catNameTxtBox.clear();
        
    }

}

    

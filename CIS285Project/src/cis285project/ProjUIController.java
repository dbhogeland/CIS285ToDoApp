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

    // The following fields are located on the Create tab and allow Users with appropriate
    // permissions to create new tasks and categories
    @FXML
    private TextField assignTxtBox; // Text field for assigning a task to a specific user

    @FXML
    private TextField catNameTxtBox; // Text field for creating a new category
  
    @FXML
    private Button createTaskButton; // Button that takes users input and creates a task

    @FXML
    private Button createCatButton; // Button for creating a category
    
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

    // The following fields are located on the Account Management tab and are for
    // Users to change their own information
    @FXML
    private TextField oldPassTxtBox; // Text Field for Old Password
    
    @FXML
    private TextField newPassTxtBox; // Text field for new password

    @FXML
    private TextField streetTxtBox; // Text Field for User Street

    @FXML
    private TextField cityTxtBox; // Text field for User City

    @FXML
    private TextField stateTxtBox; // Text field for User state

    @FXML
    private TextField phoneTxtBox; // Text field for user Phone

    @FXML
    private Button updateUser; // Button to update User information

    // The following fields are located on the Account Management tab and are for
    // Admins to change user information
    @FXML
    private TextField adminUserTxtBox; // Text Field for User ID

    @FXML
    private TextField adminOldPassTxtBox; // Text field for Old Password

    @FXML
    private TextField adminNewPassTxtBox; // Text field for new password

    @FXML
    private TextField adminStreetTxtBox; // Text field for User Street
    
    @FXML
    private TextField adminCityTxtBox; // Text field for User City

    @FXML
    private TextField adminStateTxtBox; // Text field for User State

    @FXML
    private Button updateOther; // Button for Admin to update another User's info

    @FXML
    private TextField adminPhoneTxtBox; // Text field for User Phone number

    // The following fields are located on the User Creation tab
    @FXML
    private TextField createIDTxtBox; // Text field for User ID

    @FXML
    private TextField createPassTxtBox; // Text field for User Password

    @FXML
    private TextField createConfirmPassTxtBox; // Text field for Password Confirmation

    @FXML
    private TextField createStreetTxtBox; // Text field for User Street

    @FXML
    private TextField createCityTxtBox; // Text field for User City

    @FXML
    private TextField createStateTxtBox; // Text field for User State

    @FXML
    private TextField createPhoneTxtBox; // Text field for User Phone

    @FXML
    private Button createUserBtn; // Button to create a new user profile

    // The following fields are located on the Active Tasks Tab
    @FXML
    private Label activeShortDescLbl; // Label that displays the short description of the task

    @FXML
    private TextArea activeLongDescTextArea; // Text area that displays the long description of the task

    @FXML
    private Label activeDueLbl; // Label that displays the due date of the task

    @FXML
    private Label activeStartLbl; // Label that displays the start date of the task

    @FXML
    private Label activeCreatedLbl; // Label that displays the created date of the task

    @FXML
    private Label activeUpdatedLbl; // Label that displays the last updated date of the task

    @FXML
    private Label activeAssignedLbl; // Label that displays who assigned the task

    @FXML
    private CheckBox activeCompletedCheck; // Check box to mark for completion

    @FXML
    private CheckBox activeSelectedCheck; // Check Box to select the task

    // The following fields are located on the Completed Tasks tab
    @FXML
    private Label completeShortDescLbl; // Label to display the short desscription of the task

    @FXML
    private TextArea completeLongDescTextArea; // Text area to display the long description of the task

    @FXML
    private Label completeDueLbl; // Label to display the due date of the task

    @FXML
    private Label completeStartLbl; // Label to display the start date of the task

    @FXML
    private Label completeCreatedLbl; // Label to display the created date of the task

    @FXML
    private Label completeUpdatedLbl; // Label to display the Updated date of the task

    @FXML
    private Label completeAssignedLbl; // Label to display who assigned the task

    @FXML
    private CheckBox completeCompleteCheck; //Label to check for completion

    @FXML
    private CheckBox completeSelectedCheck; // Check box to select the task

    // The following fields are on the bottom of the window and are persistent for all tabs
    @FXML
    private Label userRoleLbl; // Label to display the User ID and role of the active user

    @FXML
    private Button completeBtn; // Button that updates the completed value for the task

    @FXML
    private Button editBtn; // Button that edits the selected task if the user has permission

    @FXML
    private Button deleteBtn; // Button that deletes the selected task if the user has permission
    
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
                startD, dueD);

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

    

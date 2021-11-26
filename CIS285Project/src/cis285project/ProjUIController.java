/*
 * GUI controller class for assigning code to the 
 * ProjUI.fxml program
 * 
 */
package cis285project;

/**
 *
 * @author Jason
 * assisted by Daniel
 * 
 */


// MySQL imports
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// JavaFX imports
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;


public class ProjUIController {

    // Create tab controls
    @FXML private TextField assignTxtBox; // Text field for assigning a task to a specific user
    @FXML private TextField catNameTxtBox; // Text field for creating a new category
    @FXML private Button createTaskButton; // Button that takes users input and creates a task
    @FXML private Button createCatButton; // Button for creating a category
    @FXML private ChoiceBox<String> categorySelect; // Selecting a category when creating a task
    @FXML private DatePicker dueDatePicker; // Task due date picker 
    @FXML private TextArea longDescTxtBox; // Text field for the long description of a task
    @FXML private TextField shortDescTxtBox; // Text field for the short description of a task
    @FXML private DatePicker startDatePicker; // Set the start date of a task
    @FXML private TextField titleTxtBox; // Text field for the title of a task

    
    
    // Account Management tab controls
    // Users to change their own information
    @FXML private TextField oldPassTxtBox; // Text Field for Old Password
    @FXML private TextField newPassTxtBox; // Text field for new password
    @FXML private TextField streetTxtBox; // Text Field for User Street
    @FXML private TextField cityTxtBox; // Text field for User City
    @FXML private TextField stateTxtBox; // Text field for User state
    @FXML private TextField phoneTxtBox; // Text field for user Phone
    @FXML private Button updateUser; // Button to update User information

    
    
    // Account Management tab controls
    // Admin only controls
    @FXML private TextField adminUserTxtBox; // Text Field for User ID
    @FXML private TextField adminOldPassTxtBox; // Text field for Old Password
    @FXML private TextField adminNewPassTxtBox; // Text field for new password
    @FXML private TextField adminStreetTxtBox; // Text field for User Street
    @FXML private TextField adminCityTxtBox; // Text field for User City
    @FXML private TextField adminStateTxtBox; // Text field for User State
    @FXML private Button updateOther; // Button for Admin to update another User's info
    @FXML private TextField adminPhoneTxtBox; // Text field for User Phone number

    
    
    // User Creation tab controls
    @FXML private TextField createIDTxtBox; // Text field for User ID
    @FXML private TextField createPassTxtBox; // Text field for User Password
    @FXML private TextField createConfirmPassTxtBox; // Text field for Password Confirmation
    @FXML private TextField createStreetTxtBox; // Text field for User Street
    @FXML private TextField createCityTxtBox; // Text field for User City
    @FXML private TextField createStateTxtBox; // Text field for User State
    @FXML private TextField createPhoneTxtBox; // Text field for User Phone
    @FXML private Button createUserBtn; // Button to create a new user profile
    @FXML private ChoiceBox<String> userRoleChoiceBox; // Choice box to select a role for the user

    
    
    // Active Tasks tab controls
    @FXML private ChoiceBox<String> activeTaskChoiceBox; // Choice box to select an active task
    @FXML private Label activeTaskNamelbl; // Label that displays the name of the task
    @FXML private Label activeShortDescLbl; // Label that displays the short description of the task
    @FXML private TextArea activeLongDescTextArea; // Text area that displays the long description of the task
    @FXML private Label activeDueLbl; // Label that displays the due date of the task
    @FXML private Label activeStartLbl; // Label that displays the start date of the task
    @FXML private Label activeCreatedLbl; // Label that displays the created date of the task
    @FXML private Label activeUpdatedLbl; // Label that displays the last updated date of the task
    @FXML private Label activeAssignedLbl; // Label that displays who assigned the task
    @FXML private CheckBox activeCompletedCheck; // Check box to mark for completion
    @FXML private CheckBox activeSelectedCheck; // Check Box to select the task
    @FXML private ListView<String> activeCategoryListView; // List view to display categories

    
    
    // Completed Tasks tab controls
    @FXML public ListView<String> completeCategoryListView; // List view to display categories
    @FXML private ChoiceBox<String> completedTaskChoiceBox; // Choice box to select a completed task
    @FXML private Label completeTaskNameLbl; // Label that displays the task name
    @FXML private Label completeShortDescLbl; // Label that displays the short description
    @FXML private TextArea completeLongDescArea; // Text area that displays the long description
    @FXML private Label completeDueLbl; // Label that displays the due date
    @FXML private Label completeStartLbl; // Label that displays the start date
    @FXML private Label completeCreatedLbl; // Label that displays the created date
    @FXML private Label completeAssignedLbl; // Label that displays who assigned the task
    @FXML private CheckBox completeSelectedCheck; // Check box for selecting a task to delete

    
    
    // Buttons at bottom of window and the ID/role label
    @FXML private Label userRoleLbl; // Label to display the User ID and role of the active user
    @FXML private Button completeBtn; // Button that updates the completed value for the task
    @FXML private Button editBtn; // Button that edits the selected task if the user has permission
    @FXML private Button deleteBtn; // Button that deletes the selected task if the user has permission
    
    // Variales for LocalDate 
    private String startD; // String variable for storing start date value
    private String dueD; // String variable for storing due date value
   
    // Variables for JDBC and MySQL database
    private Connection con1; // Creates a variable for connection to MySQL database
    private PreparedStatement insert; // Creates a PreparedStatement variable insert for adding data to the MySQL database
    private Statement st; // Creates a Statement for recieing data from the MySQL database

    ObservableList<String> userRoleList = FXCollections.observableArrayList("Read", "Edit", "Update", "Manage", "Administrator"); // List to store user roles for the choice box
    
    /*
    *
    *         IMPORTANT!
    * This initialize method will run when the app launches. If you need anything to specifically run at least once
    * when the app loads, place it in this method
    *
    * -Daniel
    *
    */
    @FXML
    private void initialize() {
        userRoleChoiceBox.setItems(userRoleList); // Adds options to the user role choice box on the user creation tab
        completeCategoryListView.getItems().add("All Tasks"); // Adds an all tasks option to the completed category list
        activeCategoryListView.getItems().add("All Tasks"); // Adds an all tasks option to the active category list
        updateCatChoiceBox(); // update the choice box under task create with categories stored in database
    }

    
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
     * It also updates the MySQL database in a table called task adding the info
     * passed into the task Object parameters.
     */
    public void createTaskButtonClick(ActionEvent event) {
        
        Task taskObj = new Task(titleTxtBox.getText(),shortDescTxtBox.getText(),longDescTxtBox.getText(), 
                startD, dueD);
        
        taskObj.setCategoryTag(categorySelect.getValue()); // Sets the value of variable categoryTag to choicebox selection
        
        try {          
            Class.forName("com.sun.jdi.connect.spi.Connection");
            
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/cis285db", "root", "CIS285DB!!"); // Creates connection to the MySQL database using host / username / datbase name
            insert = con1.prepareStatement("INSERT INTO task(task_name,task_short_desc,task_long_desc," // Executes precompiled SQL statement
                    + "task_start_date,task_due_date,task_category)VALUES(?,?,?,?,?,?)"); 
            
            /*
             * Uses PreparedStatement equal to insert and adds the values in the corresponding columns for one row at a time 
             * Int - Column number , String - data to enter
            */
            insert.setString(1, taskObj.getTaskName()); 
            insert.setString(2, taskObj.getTaskShortDesc());
            insert.setString(3, taskObj.getTaskLongDesc());
            insert.setString(4, taskObj.getStartDate());
            insert.setString(5, taskObj.getDueDate());
            insert.setString(6, taskObj.getCategoryTag());
            // insert.setString(7, taskObj.getTags()); For when we implement tags
            
            insert.executeUpdate();
            insert.close();
            
            System.out.println("Successfully updated MySql server!");
            
        } catch (ClassNotFoundException ex) {         
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) { 
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
        
        // Temporary output statements to make sure input is doing what it needs to

        System.out.println(taskObj.getTaskName() + " " + taskObj.getTaskLongDesc() + " " + taskObj.getDueDate() + " " + taskObj.getStartDate());
        System.out.println(taskObj.getCategoryTag());
        
        clearCreateTaskInfo(); // Calls method that clears textfields and datepickers value when createTaskButton is Clicked
        
    }
    
    /*
     * Void method that creates a category Object with the parameter categoryName
     * when the create category button is pressed
     * Also, adds category name to Observable list on Category Class
     *
     */
    public void createCatButtonClick(ActionEvent event) {
        
        Category catObj = new Category(catNameTxtBox.getText());
        System.out.println(catObj.getCategoryName()); // Temporary output statement to verify input
        
        completeCategoryListView.getItems().add(catObj.getCategoryName()); // Adds the created category to the completed list view
        activeCategoryListView.getItems().add(catObj.getCategoryName()); // Adds the created category to the active list view
        
        clearCategoryInfo(); // Calls void method clearCategoryInfo and clears the category name text box
        
        // categorySelect.getItems().add(catObj.getCategoryName()); // returns the observablelist and adds category objects into it
                                                                    // ****Remove comment if not using MySQL driver****
        
                
        try {            
            Class.forName("com.sun.jdi.connect.spi.Connection"); // Loads the driver at runtime
            
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/cis285db", "root", "CIS285DB!!"); // Creates connection to the MySQL database using host / username / datbase name
            insert = con1.prepareStatement("INSERT INTO category(category)VALUES(?)"); // Executes precompiled SQL statement
            
            /*
             * Uses PreparedStatement equal to insert and adds the values in the corresponding columns for one row at a time 
             * Int - Column number , String - data to enter
            */
            insert.setString(1, catObj.getCategoryName()); 
            
            insert.executeUpdate(); 
            updateCatChoiceBox(); // Creates a Statement for recieing data from the MySQL database
            
            insert.close();
            
            System.out.println("Successfully updated MySql server!");
            
        } catch (ClassNotFoundException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        
         
    }
    
    /*
     * Void method that clears the input values of creatTask textfields and DatePickers
     */
    public void clearCreateTaskInfo() {
        
        titleTxtBox.clear();
        shortDescTxtBox.clear();
        longDescTxtBox.clear();
        startDatePicker.getEditor().clear();
        dueDatePicker.getEditor().clear();
        // categorySelect.getItems().clear();
        
    }
    
    /*
     * Void method that clears category textfield
     */
    public void clearCategoryInfo() {
        
        catNameTxtBox.clear();
        
    }
    
    /*
     * Void method that updates the category choice box select by getting the data 
     * from the MySQL database in the category table.
    */
    public void updateCatChoiceBox() {
        
        try {
            
            Class.forName("com.sun.jdi.connect.spi.Connection"); // Loads the driver at runtime
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/cis285db", "root", "CIS285DB!!"); // Creates connection to the MySQL database using host / username / datbase name
           
            st = con1.createStatement(); // Creates SQL basic statement in java for providing methods to execute queries in the database
            ResultSet rs = st.executeQuery("SELECT * FROM category"); // Execute the query and get the java resultset
            
            // While loop to iterate through the java resultset
            while (rs.next()) {
                if (categorySelect.getItems().contains(rs.getString("category"))) { // If statement checks if categorySelect contains ResultSet rs to avoid duplicates
                    
                }
                else { 
                    String categoryName = rs.getString("category"); // Adds ResultSet rs to string categoryName
                    categorySelect.getItems().add(categoryName); // Adds String categoryName to categorySelect choice box
                }
            }
            
            st.close();
            rs.close();
            
            System.out.println("Successfully pulled from MySql server!");
            
        } catch (ClassNotFoundException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
        
    
    
    
    
}

    

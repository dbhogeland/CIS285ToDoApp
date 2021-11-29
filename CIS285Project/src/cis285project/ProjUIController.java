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
import static cis285project.CIS285Project.myControllerHandle;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// JavaFX imports
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.time.LocalDate;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonBase;
//import javafx.scene.control.Labeled;
//import java.util.HashSet;



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
    @FXML private TextField tagsTxtBox; // Text field for the task's tags
    @FXML private Tab createTab;
    

    
    
    // Account Management tab controls
    // Users to change their own information
    @FXML private TextField oldPassTxtBox; // Text Field for Old Password
    @FXML private TextField newPassTxtBox; // Text field for new password
    @FXML private TextField streetTxtBox; // Text Field for User Street
    @FXML private TextField cityTxtBox; // Text field for User City
    @FXML private TextField stateTxtBox; // Text field for User state
    @FXML private TextField phoneTxtBox; // Text field for user Phone
    @FXML private Button updateUser; // Button to update User information
    @FXML private Tab accountManageTab;

    
    
    // Account Management tab controls
    // Admin only controls
    @FXML private TextField adminUserTxtBox; // Text Field for User ID
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
    @FXML private Tab userCreateTab;

    
    
    // Active Tasks tab controls
    @FXML private ChoiceBox<String> activeTaskChoiceBox; // Choice box to select an active task
    @FXML private Label activeTaskNameLbl; // Label that displays the name of the task
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
    @FXML private TextArea completeLongDescTextArea; // Text area that displays the long description
    @FXML private Label completeDueLbl; // Label that displays the due date
    @FXML private Label completeStartLbl; // Label that displays the start date
    @FXML private Label completeCreatedLbl; // Label that displays the created date
    @FXML private Label completeUpdatedLbl; // Label that displays the last updated date for the task
    @FXML private Label completeAssignedLbl; // Label that displays who assigned the task
    @FXML private CheckBox completeSelectedCheck; // Check box for selecting a task to delete  
    
  
    // Buttons at bottom of window and the ID/role label
    @FXML private Label userRoleLbl; // Label to display the User ID and role of the active user
    @FXML private Button completeBtn; // Button that updates the completed value for the task
    @FXML private Button editBtn; // Button that edits the selected task if the user has permission
    @FXML private Button deleteBtn; // Button that deletes the selected task if the user has permission
    @FXML private Alert alert;
    
  
    // Menu items located on the top menu bar
    @FXML private MenuItem signInMenuItem; // Menu Item to call the sign in popup window
    @FXML private MenuItem signOutMenuItem; // Menu Item to sign out the user
    @FXML private MenuItem exitMenuItem; // Menu Item that will close the application
    @FXML private MenuItem userPermissionsMenuItem; // MenuItem that will show a popup that says what each role can do
  
    // Variables for User 
    private String userID; //String variable to store the currently logged in User ID
    private String userRole; // String variable to store the currently logged in User Role
    private String currentUser; // String Variable to store the current user
    
  
    // Variables for LocalDate 
    private String startD; // String variable for storing start date value
    private String dueD; // String variable for storing due date value
   
    // Variables for JDBC and MySQL database
    private String host = "jdbc:mysql://remotemysql.com:3306/7ToOqkvNTD";
    private String user = "7ToOqkvNTD";
    private String pass = "sMXZGFhH0z";
    private Connection con1; // Creates a variable for connection to MySQL database
    private PreparedStatement insert; // Creates a PreparedStatement variable insert for adding data to the MySQL database
    private Statement st; // Creates a Statement for recieing data from the MySQL database

    // List to store user roles for the choice box
    private final ObservableList<String> userRoleList = FXCollections.observableArrayList("Read", "Edit", "Update", "Manage", "Administrator"); 
    
    
    /*
    *
    *         IMPORTANT!
    * This initialize method will run when the app launches. If you need anything to specifically run at least once
    * when the app loads, place it in this method
    *
    * -Daniel
    * All methods / others might need to be removed since there is no use for them because of database. MAYBE - jason
    */
    @FXML
    private void initialize() {
        userRoleChoiceBox.setItems(userRoleList); // Adds options to the user role choice box on the user creation tab
        //completeCategoryListView.getItems().add("All Tasks"); // Adds an all tasks option to the completed category list
        //activeCategoryListView.getItems().add("All Tasks"); // Adds an all tasks option to the active category list ** MIGHT NEED TO REMOVE ALL RELATED CODE**
        updateActiveCategoryLV();
        updateCompletedCategoryLV();
        updateUserRoleChoiceBox();
        //userRoleLbl.setText("Please Sign In"); // Sets the ID/Role label to a Sign In reminder
        //activeTaskChoiceBox.setOnAction(e-> setActiveLabels());
        //completedTaskChoiceBox.setOnAction(e-> setCompleteLabels());

        updateCatChoiceBox(); // update the choice box under task create with categories stored in database

    }
    //public user userObj=new user (oldPassTxtBox.getText(),newPassTxtBox.getText(),adminUserTxtBox.getText(),adminOldPassTxtBox.getText(),adminNewPassTxtBox.getText());
    //task taskObj=new task (cityTxtBox.getText(), stateTxtBox.getText(), stateTxtBox.getText(),phoneTxtBox.getText(),adminCityTxtBox.getText(),adminStateTxtBox.getText(),adminStreetTxtBox.getText(),adminPhoneTxtBox.getText());


    /*
     * Void Method for setting the current application user
     */
    public void setCurrentUser(String u) {
        myControllerHandle.currentUser = u;
    }
    /*
     * Void method which creates a LocalDate object for start date and gets 
     * the value from UI DatePicker and converts it into a string startD
     */
    
    public void userCreateBtnClick(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Password ERROR!");
        alert.setContentText("The passwords do not match!");
        
        User userObj = new User(createIDTxtBox.getText(), createPassTxtBox.getText(), createConfirmPassTxtBox.getText(), createStreetTxtBox.getText(), createCityTxtBox.getText(),
            createStateTxtBox.getText(), createPhoneTxtBox.getText());
        String temp = createPassTxtBox.getText().toString();
        String temp2 = createConfirmPassTxtBox.getText().toString();
        System.out.println(temp);
        System.out.println(temp2);
        if (temp.equals(temp2)) {
            try {          
            Class.forName("com.sun.jdi.connect.spi.Connection");
            
            con1 = DriverManager.getConnection(host, user, pass); // Creates connection to the MySQL database using host-datbase name/ username / password
            insert = con1.prepareStatement("INSERT INTO user(username, password, street, city, state, phone)VALUES(?,?,?,?,?,?)"); 
            
            /*
             * Uses PreparedStatement equal to insert and adds the values in the corresponding columns for one row at a time 
             * Int - Column number , String - data to enter
            */
            insert.setString(1, userObj.getUserID());
            insert.setString(2, userObj.getPass()); 
            insert.setString(3, userObj.getStreet());
            insert.setString(4, userObj.getCity());
            insert.setString(5, userObj.getState());
            insert.setString(6, userObj.getPhoneNumber());
            
            
            insert.executeUpdate();
            insert.close();
            
            System.out.println("Successfully updated MySql server!");
            
            } catch (ClassNotFoundException ex) {         
                Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
            } catch (SQLException ex) { 
                Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);            
            }
        }
        else {
            alert.showAndWait();
            clearUserInfo();
        }
        
        
        
        
        clearUserInfo(); // Calls method that clears textfields 
        
    }
    
    
    public void clearUserInfo() {
        createIDTxtBox.clear();
        createPassTxtBox.clear();
        createConfirmPassTxtBox.clear();
        createStreetTxtBox.clear();
        createCityTxtBox.clear();
        createStateTxtBox.clear();
        createPhoneTxtBox.clear();
    }
    
    public void updateUserRoleChoiceBox() {
        
        try {
            
            Class.forName("com.sun.jdi.connect.spi.Connection"); // Loads the driver at runtime
            con1 = DriverManager.getConnection(host, user, pass); // Creates connection to the MySQL database using host-datbase name/ username / password
           
            st = con1.createStatement(); // Creates SQL basic statement in java for providing methods to execute queries in the database
            ResultSet rs = st.executeQuery("SELECT * FROM userroles"); // Execute the query and get the java resultset
            
            // While loop to iterate through the java resultset
            while (rs.next()) {
                if (userRoleChoiceBox.getItems().contains(rs.getString("userrole"))) { // If statement checks if categorySelect contains ResultSet rs to avoid duplicates
                    
                }
                else { 
                    String userRole = rs.getString("userrole"); // Adds ResultSet rs to string 
                    userRoleChoiceBox.getItems().add(userRole); // Adds String to userRole
                }
            }
            
            st.close();
            rs.close();
            
            
        } catch (ClassNotFoundException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
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
        
        SignIn signInObj = new SignIn();
        Task taskObj = new Task(titleTxtBox.getText(),shortDescTxtBox.getText(),longDescTxtBox.getText(),
                startD, dueD, tagsTxtBox.getText());
        
        taskObj.setUserNameTask(assignTxtBox.getText()); // Get text from assignTxtBox for assigning a user to a task
        taskObj.setAssignedBy(currentUser); // Takes the current user and stores it into task class assignedBy variable
       
        taskObj.setCategoryTag(categorySelect.getValue()); // Sets the value of variable categoryTag to choicebox selection

        
        try {          
            Class.forName("com.sun.jdi.connect.spi.Connection");
            
            con1 = DriverManager.getConnection(host, user, pass); // Creates connection to the MySQL database using host-datbase name/ username / password
            insert = con1.prepareStatement("INSERT INTO task(username,task_name,task_short_desc,task_long_desc," // Executes precompiled SQL statement
                    + "task_start_date,task_due_date,task_category,task_tag,assigned_by)VALUES(?,?,?,?,?,?,?,?,?)"); 
            
            /*
             * Uses PreparedStatement equal to insert and adds the values in the corresponding columns for one row at a time 
             * Int - Column number , String - data to enter
            */
            insert.setString(1, taskObj.getUserNameTask());
            insert.setString(2, taskObj.getTaskName()); 
            insert.setString(3, taskObj.getTaskShortDesc());
            insert.setString(4, taskObj.getTaskLongDesc());
            insert.setString(5, taskObj.getStartDate());
            insert.setString(6, taskObj.getDueDate());
            insert.setString(7, taskObj.getCategoryTag());
            insert.setString(8, taskObj.getTaskTags()); 
            insert.setString(9, taskObj.getAssignedBy());
            
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
     * 
     * Also, I added the ability for this method to add the created to the observable list for the active and completed category list views - Daniel
     */
    public void createCatButtonClick(ActionEvent event) {
      
        Category catObj = new Category(catNameTxtBox.getText());
        EditTask editObj = new EditTask();
        System.out.println(catObj.getCategoryName()); // Temporary output statement to verify input
        
        completeCategoryListView.getItems().add(catObj.getCategoryName()); // Adds the created category to the completed list view
        activeCategoryListView.getItems().add(catObj.getCategoryName()); // Adds the created category to the active list view

        categorySelect.getItems().add(catObj.getCategoryName()); // returns the observablelist and adds category objects into it
        editObj.categoryCh.getItems().add(catObj.getCategoryName()); // Adds items to the category choice box on the edit page

        clearCategoryInfo(); // Calls void method clearCategoryInfo and clears the category name text box
        
        // categorySelect.getItems().add(catObj.getCategoryName()); // returns the observablelist and adds category objects into it
                                                                    // ****Remove comment if not using MySQL driver****
        
                
        try {            
            Class.forName("com.sun.jdi.connect.spi.Connection"); // Loads the driver at runtime
            
            con1 = DriverManager.getConnection(host, user, pass); // Creates connection to the MySQL database using host-datbase name/ username / password
            insert = con1.prepareStatement("INSERT INTO category(category)VALUES(?)"); // Executes precompiled SQL statement
            
            /*
             * Uses PreparedStatement equal to insert and adds the values in the corresponding columns for one row at a time 
             * Int - Column number , String - data to enter
            */
            insert.setString(1, catObj.getCategoryName()); 
            
            insert.executeUpdate(); 
            updateCatChoiceBox(); // Creates a Statement for recieing data from the MySQL database
            updateActiveCategoryLV(); // Updates the category listview in the active task tab
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
        assignTxtBox.clear();
        tagsTxtBox.clear();
        
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
            con1 = DriverManager.getConnection(host, user, pass); // Creates connection to the MySQL database using host-datbase name/ username / password
           
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
    
  
    /*
     * Void method to call the sign in popup when the sign in menu item is clicked - Daniel
     */
    public void callSignInWindow(ActionEvent event) {
        SignIn signInObj = new SignIn();
        signInObj.display();
    }
    
    /*
     * Void method to sign out the user and clear the lists of active and completed tasks - Daniel
     */
    public void signOut(ActionEvent event) {
        activeTaskChoiceBox.getItems().clear();
        completeCategoryListView.getItems().clear();
        userID = "";
        userRole = "";
        userRoleLbl.setText("Please Sign In");
    }
    
    /*
     * Void method that closes the application - Daniel
     */
    public void closeApp(ActionEvent event) {
        Platform.exit();
    }
    
    /*
     * Void method to show a pop up that will show what each role is allowed to do - Daniel
     */
    public void showUserPermissions(ActionEvent event) {
        UserPermissionsPopup permObj = new UserPermissionsPopup();
        permObj.displayPermissions();
    }
    
    /*
     * Method to update the completed value on the database for the selected task
     */
    
    public void completeTask(){
        if(activeCompletedCheck.isSelected()){
            String completedTask;
            int index = activeTaskChoiceBox.getSelectionModel().getSelectedIndex();
            completedTask = activeTaskChoiceBox.getValue();
            //ToDo
            //Need to add completed column to task database with a boolean data type
            //Needs database code to set task as completed
            
            activeTaskChoiceBox.getItems().remove(index);
        }
    }
    
    
    public void editTask(){
        EditTask edit = new EditTask();
        
        if(activeSelectedCheck.isSelected()){
            edit.setTaskToEdit(activeTaskChoiceBox.getValue());
            edit.display();
            //Will include a call to the display() method of the EditTask class so that the user can make changes
            //Needs database code to edit the selected task
        }
        
        if(completeSelectedCheck.isSelected()){
            edit.setTaskToEdit(completedTaskChoiceBox.getValue());
            edit.display();
            //Will include a call to the display() method of the EditTask class so that the user can make changes
            //Needs database code to edit the selected task
        }
    }
    
    public void deleteTask(){
        String task;
        if(activeSelectedCheck.isSelected()){
            int index = activeTaskChoiceBox.getSelectionModel().getSelectedIndex();
            task = activeTaskChoiceBox.getValue();
            
            //Needs database code to delete selected task
            
            activeTaskChoiceBox.getItems().remove(index);
        }
        
        if(completeSelectedCheck.isSelected()){
            int index = completedTaskChoiceBox.getSelectionModel().getSelectedIndex();
            task = completedTaskChoiceBox.getValue();

            //Needs database code to delete selected task
            
            completedTaskChoiceBox.getItems().remove(index);
        }
    }
    
    /*
     * Method for changing the UserID - Role label
     * Doesn't work for some reason
     */
    public void setRoleLbl(String output){
        myControllerHandle.userRoleLbl.setText(output);
    }
    
    /*                  *** Active Task Tab Start here ****
     * Void method for retrieving list of categorys from the database and updating
     * the active task tab category listview
     */
    public void updateActiveCategoryLV() {
               
        
        try {
            
            Class.forName("com.sun.jdi.connect.spi.Connection"); // Loads the driver at runtime
            con1 = DriverManager.getConnection(host, user, pass); // Creates connection to the MySQL database using host-datbase name/ username / password
           
            st = con1.createStatement(); // Creates SQL basic statement in java for providing methods to execute queries in the database
            ResultSet rs = st.executeQuery("SELECT * FROM category"); // Execute the query and get the java resultset
            
            // While loop to iterate through the java resultset
            while (rs.next()) {
                if (activeCategoryListView.getItems().contains(rs.getString("category"))) { // Checks for repeats of a category
                    
                }
                else { 
                    String activeCatLV = rs.getString("category"); // Adds ResultSet rs to string activeCatLV
                    activeCategoryListView.getItems().add(activeCatLV);  // Adds the category to the listview
                }
            }
         
            
            st.close();
            rs.close();
            
            System.out.println("Successfully pulled ActiveCategory from MySql server!");
            
        } catch (ClassNotFoundException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        /*
         * Gets the active category listview and sets the selected item into a variable and updates the choicebox based on the selection
         */
        activeCategoryListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> { 
            String selectedItem = activeCategoryListView.getSelectionModel().getSelectedItem(); 
            String prevSelectedItem = selectedItem;
            
            // If statment to determine whether the current selected listview item is the same and either leaves or deletes current choicebox options
            if (!prevSelectedItem.equals(selectedItem)) {
                System.out.println(activeTaskChoiceBox.getItems());
                updateActiveTaskChoiceBox(selectedItem, currentUser, true); // Updates choicebox
            }
            else {
                
                activeTaskChoiceBox.getItems().clear(); // clears choicebox
                System.out.println(activeTaskChoiceBox.getItems());
                updateActiveTaskChoiceBox(selectedItem, currentUser, true); // Updates choicebox
            }
            
        });
        
    }
    
    /*
     * Void method for updateding active task choicebox from database based on criteria such as user signed in,
     * task category, task is active 
    */

    public void updateActiveTaskChoiceBox(String c, String u, boolean t) {

        try {
            
            Class.forName("com.sun.jdi.connect.spi.Connection"); // Loads the driver at runtime
            con1 = DriverManager.getConnection(host, user, pass); // Creates connection to the MySQL database using host-datbase name/ username / password
           
            st = con1.createStatement(); // Creates SQL basic statement in java for providing methods to execute queries in the database
            ResultSet rs = st.executeQuery("SELECT * FROM task"); // Execute the query and get the java resultset
            
            // While loop for iterating through ResultSet
            while (rs.next()) {
                
                // If statement checks to see if database table contains the following: category, username, active status
                if (rs.getString("task_category").equalsIgnoreCase(c) && rs.getString("username").equalsIgnoreCase(u) &&
                        rs.getBoolean("is_active") == t) {

                    // If statement checks to see if active task choice box already contains task name to avoid adding duplicates
                    if (activeTaskChoiceBox.getItems().contains(rs.getString("task_name"))) {
                        
                    }
                    else {
                        String activeTask = rs.getString("task_name"); // Adds ResultSet rs to string activeCatLV
                        activeTaskChoiceBox.getItems().add(activeTask);  // Adds the task to the choicebox
                    }
                    
                }
                
            }
            st.close();
            rs.close();
            
            System.out.println("Successfully pulled ActiveCategory from MySql server!");
            
        } catch (ClassNotFoundException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        }

        
        setActiveLabels(); // Calls setActiveLabels method
    }
    
   
    /*
     * Method that updates the labels for the selected active task automatically through the database 
     */
    public void setActiveLabels(){
        String title = "Task Name";
        String shortDesc = "Short Description";
        String longDesc = "Long Description";
        String dueDate = "Unassigned";
        String startDate = "Unassigned";
        String createdD = "Unassigned";
        String updated = "Unassigned";
        String assignedBy = "Unassigned";
        
        activeTaskNameLbl.setText(title);
        activeShortDescLbl.setText(shortDesc);
        activeLongDescTextArea.setText(longDesc);
        activeDueLbl.setText(dueDate);
        activeStartLbl.setText(startDate);
        activeCreatedLbl.setText(createdD);
        activeUpdatedLbl.setText(updated);
        activeAssignedLbl.setText(assignedBy);
        
        /*
         * Gets the choicebox options and sets the selected one as a string variable. Passess variable into Query search 
         * and pulls specified task then displays labels for that task
         */
        activeTaskChoiceBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> { 
           
            String selectedItem = activeTaskChoiceBox.getSelectionModel().getSelectedItem(); 
            
             try {
            
            Class.forName("com.sun.jdi.connect.spi.Connection"); // Loads the driver at runtime
            con1 = DriverManager.getConnection(host, user, pass); // Creates connection to the MySQL database using host-datbase name/ username / password
           
            st = con1.createStatement(); // Creates SQL basic statement in java for providing methods to execute queries in the database
            ResultSet rs = st.executeQuery("SELECT * FROM task WHERE task_name='" + selectedItem + "'"); // Execute the query and get the java resultset
            
            // While loop to iterate through the java resultset
            while (rs.next()) {

                    String taskName = rs.getString("task_name"); // Adds ResultSet rs to String variable from the specified database column name
                    String taskShortDesc = rs.getString("task_short_desc"); // Adds ResultSet rs to String variable from the specified database column name
                    String taskLongDesc = rs.getString("task_long_desc"); // Adds ResultSet rs to String variable from the specified database column name
                    String taskDueDate = rs.getString("task_due_date"); // Adds ResultSet rs to String variable from the specified database column name
                    String taskStartDate = rs.getString("task_start_date"); // Adds ResultSet rs to String variable from the specified database column name
                    String taskAssignedBy = rs.getString("assigned_by"); // Adds ResultSet rs to String variable from the specified database column name
                     
                    activeTaskNameLbl.setText(taskName); // Set activeTaskNameLbl to variable taskName
                    activeShortDescLbl.setText(taskShortDesc); // Set activeShortDescLbl to varabile taskShortDesc
                    activeLongDescTextArea.setText(taskLongDesc); // Set activeLongDescTextArea to taskLongDesc
                    activeDueLbl.setText(taskDueDate); // Set activeDueLbl to varaible taskDueDate
                    activeStartLbl.setText(taskStartDate); // Set activeStartLbl to variable taskStartDate
                    //activeCreatedLbl.setText(createdD); need to be implemented in 
                    //activeUpdatedLbl.setText(updated); need to be implemented in
                    activeAssignedLbl.setText(taskAssignedBy); // Set activeAssignLbl to variable taskAssignedBy
                
            }
            
            st.close();
            rs.close();
            
            System.out.println("Successfully pulled from MySql server!");
            
        } catch (ClassNotFoundException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    
        });
        
    } 
    /*
     *                       **** Active Task Tab End Here ****
     */
    
    /*                     **** Completed Task Tab Start Here ****
     * Void method for retrieving list of categorys from the database and updating
     * the completed task tab category listview
     */
    public void updateCompletedCategoryLV() {
               
        
        try {
            
            Class.forName("com.sun.jdi.connect.spi.Connection"); // Loads the driver at runtime
            con1 = DriverManager.getConnection(host, user, pass); // Creates connection to the MySQL database using host-datbase name/ username / password
           
            st = con1.createStatement(); // Creates SQL basic statement in java for providing methods to execute queries in the database
            ResultSet rs = st.executeQuery("SELECT * FROM category"); // Execute the query and get the java resultset
            
            // While loop to iterate through the java resultset
            while (rs.next()) {
                if (completeCategoryListView.getItems().contains(rs.getString("category"))) { // Checks for repeats of a category
                    
                }
                else { 
                    String activeCompCatLV = rs.getString("category"); // Adds ResultSet rs to string activeCompCatLV
                    completeCategoryListView.getItems().add(activeCompCatLV);  // Adds the category to the listview
                }
            }
         
            
            st.close();
            rs.close();
            
            System.out.println("Successfully pulled ActiveCategory from MySql server!");
            
        } catch (ClassNotFoundException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        
        
        /*
         * Gets the active completed category listview and sets the selected item into a variable and updates the choicebox based on the selection
         */
        completeCategoryListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> { 
            String selectedItem = completeCategoryListView.getSelectionModel().getSelectedItem(); 
            String prevSelectedItem = selectedItem;
            
            // If statment to determine whether the current selected listview item is the same and either leaves or deletes current choicebox options
            if (!prevSelectedItem.equals(selectedItem)) {
                System.out.println(completeCategoryListView.getItems());
                updateCompletedTaskChoiceBox(selectedItem, currentUser, false); // Updates choicebox
            }
            else {
                
                completedTaskChoiceBox.getItems().clear(); // clears choicebox
                System.out.println(activeTaskChoiceBox.getItems());
                updateCompletedTaskChoiceBox(selectedItem, currentUser, false); // Updates choicebox
            }
            
        });
        
    }
    
    /*
     * Void method for updateding completed task choicebox from database based on criteria such as user signed in,
     * task category, task is not active 
    */
    public void updateCompletedTaskChoiceBox(String c, String u, boolean t) {
   
        try {
            
            Class.forName("com.sun.jdi.connect.spi.Connection"); // Loads the driver at runtime
            con1 = DriverManager.getConnection(host, user, pass); // Creates connection to the MySQL database using host-datbase name/ username / password
           
            st = con1.createStatement(); // Creates SQL basic statement in java for providing methods to execute queries in the database
            ResultSet rs = st.executeQuery("SELECT * FROM task"); // Execute the query and get the java resultset
            
            // While loop for iterating through ResultSet
            while (rs.next()) {
                
                // If statement checks to see if database table contains the following: category, username, active status
                if (rs.getString("task_category").equalsIgnoreCase(c) && rs.getString("username").equalsIgnoreCase(u) &&
                        rs.getBoolean("is_active") == t) { 
                    
                    // If statement checks to see if completed task choice box already contains task name to avoid adding duplicates
                    if (completedTaskChoiceBox.getItems().contains(rs.getString("task_name"))) {
                        
                    }
                    else {
                        String completedTask = rs.getString("task_name"); // Adds ResultSet rs to string activeCatLV
                        completedTaskChoiceBox.getItems().add(completedTask);  // Adds the task to the choicebox
                    }
                    
                }
                
            }
            st.close();
            rs.close();
            
            System.out.println("Successfully pulled ActiveCategory from MySql server!");
            
        } catch (ClassNotFoundException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        setCompletedLabels(); // Calls setCompletedLabels method
    }
    
    /*
     * Method that updates the labels for the selected completed task when called by the completedTaskChoiceBox listener
     */
    public void setCompletedLabels(){
        String title = "Task Name";
        String shortDesc = "Short Description";
        String longDesc = "Long Description";
        String dueDate = "Unassigned";
        String startDate = "Unassigned";
        String createdD = "Unassigned";
        String updated = "Unassigned";
        String assignedBy = "Unassigned";
        
        completeTaskNameLbl.setText(title);
        completeShortDescLbl.setText(shortDesc);
        completeLongDescTextArea.setText(longDesc);
        completeDueLbl.setText(dueDate);
        completeStartLbl.setText(startDate);
        completeCreatedLbl.setText(createdD);
        completeUpdatedLbl.setText(updated);
        completeAssignedLbl.setText(assignedBy);
        
        /*
         * Gets the choicebox options and sets the selected one as a string variable. Passess variable into Query search 
         * and pulls specified task then displays labels for that task
         */
        completedTaskChoiceBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> { 
           
            String selectedItem = completedTaskChoiceBox.getSelectionModel().getSelectedItem(); 
            
             try {
            
            Class.forName("com.sun.jdi.connect.spi.Connection"); // Loads the driver at runtime
            con1 = DriverManager.getConnection(host, user, pass); // Creates connection to the MySQL database using host-datbase name/ username / password
           
            st = con1.createStatement(); // Creates SQL basic statement in java for providing methods to execute queries in the database
            ResultSet rs = st.executeQuery("SELECT * FROM task WHERE task_name='" + selectedItem + "'"); // Execute the query and get the java resultset
            
            // While loop to iterate through the java resultset
            while (rs.next()) {

                    String taskName = rs.getString("task_name"); // Adds ResultSet rs to String variable from the specified database column name
                    String taskShortDesc = rs.getString("task_short_desc"); // Adds ResultSet rs to String variable from the specified database column name
                    String taskLongDesc = rs.getString("task_long_desc"); // Adds ResultSet rs to String variable from the specified database column name
                    String taskDueDate = rs.getString("task_due_date"); // Adds ResultSet rs to String variable from the specified database column name
                    String taskStartDate = rs.getString("task_start_date"); // Adds ResultSet rs to String variable from the specified database column name
                    String taskAssignedBy = rs.getString("assigned_by"); // Adds ResultSet rs to String variable from the specified database column name
                     
                    completeTaskNameLbl.setText(taskName); // Set completeTaskNameLbl to variable taskName
                    completeShortDescLbl.setText(taskShortDesc); // Set completeShortDescLbl to varabile taskShortDesc
                    completeLongDescTextArea.setText(taskLongDesc); // Set completeLongDescTextArea to taskLongDesc
                    completeDueLbl.setText(taskDueDate); // Set completeDueLbl to varaible taskDueDate
                    completeStartLbl.setText(taskStartDate); // Set completeStartLbl to variable taskStartDate
                    //activeCreatedLbl.setText(createdD); need to be implemented in 
                    //activeUpdatedLbl.setText(updated); need to be implemented in
                    completeAssignedLbl.setText(taskAssignedBy); // Set completeAssignLbl to variable taskAssignedBy
                
            }
            
            st.close();
            rs.close();
            
            System.out.println("Successfully pulled from MySql server!");
            
        } catch (ClassNotFoundException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {           
            Logger.getLogger(ProjUIController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    
        });
        
    }
    
    /*
     * Method that enables UI features based on the active user's role
     * Application launches with features disabled based on a user with the role of Read
     * When the user signs in the if statements will test against the user's role and unlock features accordingly
     */
    public void setPermissions(){
       /*
        Code is currently commented out until full sign in functionality is implemented
        
        if (userRole.equals("Admin")){
            activeCompletedCheck.setDisable(false);
            activeSelectedCheck.setDisable(false);
            completeSelectedCheck.setDisable(false);
            createTab.setDisable(false);
            accountManageTab.setDisable(false);
            userCreateTab.setDisable(false); 
            adminUserTxtBox.setDisable(false);
            adminNewPassTxtBox.setDisable(false);
            adminStreetTxtBox.setDisable(false);
            adminCityTxtBox.setDisable(false);
            adminStateTxtBox.setDisable(false);
            adminPhoneTxtBox.setDisable(false);
            completeBtn.setDisable(false);
            editBtn.setDisable(false);
            deleteBtn.setDisable(false);
       }
        
        if (userRole.equals("Manage")){
            activeCompletedCheck.setDisable(false);
            activeSelectedCheck.setDisable(false);
            completeSelectedCheck.setDisable(false);
            createTab.setDisable(false);
            accountManageTab.setDisable(false);
            userCreateTab.setDisable(false);
            completeBtn.setDisable(false);
            editBtn.setDisable(false);
            deleteBtn.setDisable(false);
        }
        
        if (userRole.equals("Update")){
            activeCompletedCheck.setDisable(false);
            accountManageTab.setDisable(false);
            userCreateTab.setDisable(false);
            completeBtn.setDisable(false);
        }
        
        if (userRole.equals("Edit")){
            activeCompletedCheck.setDisable(false);
            userCreateTab.setDisable(false);
            completeBtn.setDisable(false);
        }
        */
    }

    
    /*
     * Method to test for input on the fields of the Account Management tab and send input to the Manager Class
     */
    public void updateAccountInfo(){
        Manager managerObj = new Manager();
        
        if(newPassTxtBox.getText() != null && !newPassTxtBox.getText().trim().isEmpty()){
            if(oldPassTxtBox.getText().equals(newPassTxtBox.getText())){
                managerObj.setNewPassword(newPassTxtBox.getText());
            }
        }
        
        if(streetTxtBox.getText() != null && !streetTxtBox.getText().trim().isEmpty()){
            managerObj.setNewStreet(streetTxtBox.getText());
        }
        
        if(cityTxtBox.getText() != null && !cityTxtBox.getText().trim().isEmpty()){
            managerObj.setNewCity(cityTxtBox.getText());
        }
        
        if(stateTxtBox.getText() != null && !stateTxtBox.getText().trim().isEmpty()){
            managerObj.setNewState(stateTxtBox.getText());
        }
        
        if(phoneTxtBox.getText() != null && !phoneTxtBox.getText().trim().isEmpty()){
            managerObj.setNewPhone(phoneTxtBox.getText());
        }
    }
    
    /*
     * Method to test for input on the fields of the Account Management tab Admin section and send input to the Manager Class
     */
    public void updateAccountInfoAdmin(){
        Manager managerObj = new Manager();
        
        if(adminNewPassTxtBox.getText() != null && !adminNewPassTxtBox.getText().trim().isEmpty()){
            managerObj.setNewPassAdmin(adminNewPassTxtBox.getText());
            
            if(adminStreetTxtBox.getText() != null && !adminStreetTxtBox.getText().trim().isEmpty()){
                managerObj.setNewStreetAdmin(adminStreetTxtBox.getText());
            }
            
            if(adminStreetTxtBox.getText() != null && !adminStreetTxtBox.getText().trim().isEmpty()){
                managerObj.setNewStreetAdmin(adminStreetTxtBox.getText());
            }
        
            if(adminCityTxtBox.getText() != null && !adminCityTxtBox.getText().trim().isEmpty()){
                managerObj.setNewCityAdmin(adminCityTxtBox.getText());
            }
        
            if(adminStateTxtBox.getText() != null && !adminStateTxtBox.getText().trim().isEmpty()){
             managerObj.setNewStateAdmin(adminStateTxtBox.getText());
            }
        
            if(adminPhoneTxtBox.getText() != null && !adminPhoneTxtBox.getText().trim().isEmpty()){
                managerObj.setNewPhoneAdmin(adminPhoneTxtBox.getText());
            }
        }
        
    }

}

    

/*
 * This task class is to create an easier connection between the create page on the 
 * proJUI.fxml by adding setters and getters for each element of a task and displaying on 
 * other aspects.
 */
package cis285project;

/**
 *
 * @author Jason
 */

public class Task {
    
    private String taskName; //String variable for task name
    private String taskShortDesc; // String variable for short description of a task
    private String taskLongDesc; // Strig variable for long description of a task
    private String startDate; // String variable for start date of a task. Can be converted to int if needed.
    private String dueDate; // String Variable for due date of a task. Can be converted to int if needed.
    
    /*
     * Constructor for basic task
     * information that the user will provide. 
     */
    public Task(String tN, String tSD, String tLD, String sD, String, dD){
        
        taskName = tN;
        taskShortDesc = tSD;
        taskLongDesc = tLD;
        startDate = sD;
        dueDate = dD;
    
    }
    
    public void setTaskName(String tN){ // Method for setting task name/title
        taskName = tN;
    }
    public String getTaskName(){ // Method for retrieving task name/title
        return taskName;
    }
    
    public void setTaskShortDesc(String tSD){ // Method for setting task short description
        taskShortDesc = tSD;
    }
    public String getTaskShortDesc(){ // Method for retrieving task short description
        return taskShortDesc;
    }
    
    public void setTaskLongDesc(String tLD){ // Method for setting task long description
        taskLongDesc = tLD;
    }
    public String getTaskLongDesc(){ // Method for retrieving task long description
        return taskLongDesc;
    }
    
    public void setStartDate(String sD){ // Method for setting task start date
        startDate = sD;
    }
    public String getStartDate(){ // Method for retrieving task start date
        return startDate;
    }
    
    public void setDueDate(String dD){ // Method for setting task due date
        dueDate = dD;
    }
    public String getDueDate(){ // method for retrieving task due date 
        return dueDate;
    }
    
}

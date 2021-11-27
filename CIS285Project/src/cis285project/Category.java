/*
 * Category class is created to make categorys and set them in the
 * create section of the proJUI.fxml file and other aspects
 * 
 */
package cis285project;

/**
 *
 * @author Jason
 */

public class Category {
    
    private String categoryName; // String to hold the name of a Category
    
    public Category(String cN){ // Constructor that takes cN as a parameter and sets it equal to categoryName
        categoryName = cN;
    }
    
    public void setCategoryName(String cN){ // Method for setting a category's name
        categoryName = cN;
    }
    public String getCategoryName(){ // Method for retrieving the name of a category
        return categoryName;
    }   
}

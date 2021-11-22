/*
 * Class for assigning a task to a 
 * category using HashMap
 * 
 * UPDATE 11/22/2021 6:50 A.M. - Debating on removing this if there is another way to group Categorys / Tags
 */
package cis285project;

/**
 *
 * @author Jason
 */

import java.util.HashMap;

public class TaskCategoryMap {
    
    /*
     *Creats new HashMap that is two strings the key being the category and the
     * second being the task. Allowing us to know what task a category has inside of it.
     */
    
    HashMap<String, String> taskCat = new HashMap<>(); 
    
    private String k1;
    private String k2;
    private String name;
    
    public void setTaskCategoryMap(String k1, String n){ 
        
        taskCat.put(k1, n);
        
    }
    
  
}  


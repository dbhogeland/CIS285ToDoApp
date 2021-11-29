package cis285project;

import javax.swing.JTextField;

//author @William 
//Edited by Daniel

public class Manager {
    
    /*
     * Class fields to store updated account information
     */
    private String newPassword;
    private String newStreet;
    private String newCity;
    private String newState;
    private String newPhone;
    private String userID;
    private String newPassAdmin;
    private String newStreetAdmin;
    private String newCityAdmin;
    private String newStateAdmin;
    private String newPhoneAdmin;
    
    /*
     * Setters and getters for class fields
     */
    public void setNewPassword(String nP){
        newPassword = nP;
    }
    
    public String getNewPassword(){
        return newPassword;
    }
    
    public void setNewStreet(String nS){
        newStreet = nS;
    }
    
    public String getNewStreet(){
        return newStreet;
    }
    
    public void setNewCity(String nC){
        newCity = nC;
    }
    
    public String getNewCity(){
        return newCity;
    }
    
    public void setNewState(String nS){
        newState = nS;
    }
    
    public String getNewState(){
        return newState;
    }
    
    public void setNewPhone(String nP){
        newPhone = nP;
    }
    
    public String getNewPhone(){
        return newPhone;
    }
    
    public void setUserIDAdmin(String uID){
        userID = uID; 
    }
    
    public String getUserIDAdmin(){
        return userID;
    }
    
    public void setNewPassAdmin(String nPA){
        newPassAdmin = nPA;
    }
    
    public String getNewPassAdmin(){
        return newPassAdmin;
    }
    
    public void setNewStreetAdmin(String nSA){
        newStreetAdmin = nSA;
    }
    
    public String getNewStreetAdmin(){
        return newStreetAdmin;
    }
    
    public void setNewCityAdmin(String nCA){
        newCityAdmin = nCA;
    }
    
    public String getNewCityAdmin(){
        return newCityAdmin;
    }
    
    public void setNewStateAdmin(String nSA){
        newStateAdmin = nSA;
    }
    
    public String getNewStateAdmin(){
        return newStateAdmin;
    }
    
    public void setNewPhoneAdmin(String nPA){
        newPhoneAdmin = nPA;
    }
    
    public String getNewPhoneAdmin(){
        return newPhoneAdmin;
    }
}

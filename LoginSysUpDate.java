package cis285project.LoginSystem.CISProject;

import javax.swing.JTextField;

//author @William 
public class LoginSys {
    public static String username;// Name of Account In Use
    public int user_id = 1; // Possible Users Able to Login
    private String password;// Password of Account
    public static int count = 1;// Possible Number of Passwords Account has in Use
    public static String input;
    public string Street;// Area of User
    public string Number;// Number of User
    public string State;//
    public string City;//

    /*
     * Constructor Variables for creating account User will provide this imformation
     */

 public users(String Ruser, String Rpassword) {

        this.user_id = count++;
        this.user_name = Ruser;
        this.password = Rpassword;
        count++;
        System.out.printf("User %s has been crated \n", Ruser);

    public Task(string St,String No, String Sa, String Cy){
            this.City=Cy
            this.Number=No
            this.State=Sa
            this.Street=St
        }

    public void setCity(String Cy){//Method to Set City Location
            City=Cy
        }

    public String getCity(){//Retrieve City
            return City
        }

    public void setNumber(String No){//Method to Set Number
            Number=No
        }

    public String getNumber(){//Retrieve Number
            return Number
        }

    public void setState(String Sa){//Method to Set State Location
            State=Sa
        }

    public String getState(){//Retrieve State
            return State
        }

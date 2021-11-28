package cis285project;

import javax.swing.JTextField;

//author @William 
public class Manager {
    public String user;// Name of Account In Use
    public int user_id = 1; // Possible Users Able to Login
    private String password;// Password of Account
   public static int count = 1;// Possible Number of Passwords Account has in Use
    public static String input;
    public String Street;// Area of User
    public String Phone;// Number of User
    public String State;//
    public String City;//

    /*
     * Constructor Variables for creating account User will provide this imformation
     */

    public int Users (String Ruser, String Rpassword) {

        this.user_id = count++;
        this.user = Ruser;
        this.password = Rpassword;
        count++;
        System.out.printf("User %s has been updated \n", Ruser);
    }

    boolean changePassword(){            
            String pass = ""; //get old password from user
            int n = 1;
            while (n-- > 0) {
              if(pass.equals(password)) {
                  String newPass = ""; // get new password from user
                  String conNewPass = ""; // confirm new password from user
                  if (newPass.equals(conNewPass)) {
                      password = newPass;
                      // password changed
                      return true;
                  } else {
                      // wrong confirmation.. password not changed
                      return false;
                  }
              }
              else {
                  // tell user to enter the correct old password
                  pass = ""; // ask user for old password again
              }
            }

    public static String Sr; String Ph; String Sa;String Cy;{
            this.City=Cy;
            this.Phone=Ph;
            this.State=Sa;
            this.Street=Sr;}
        }

    public void setCity(String Cy) {// Method to Set City Location
        City = Cy;
    }

    public String getCity() {// Retrieve City
        return City;
    }

    public void setNumber(String No) {// Method to Set Number
        Number = No;
    }

    public String getNumber() {// Retrieve Number
        return Number;
    }

    public void setState(String Sa) {// Method to Set State Location
        State = Sa;
    }

    public String getState() {// Retrieve State
        return State;
    }
}

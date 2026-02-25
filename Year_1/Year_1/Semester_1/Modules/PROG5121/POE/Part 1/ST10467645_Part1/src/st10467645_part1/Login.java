/**
 * This class handles everything for registration and login.
 * It checks if the username and password meet the requirements,
 * stores the user's login details, which allows the user to log in.
 * It also validates if a South African phone number is valid.
 * 
 * Reference for citation:
 * DeepSeek. (2025). Regex for validating cell phone numbers. https://www.deepseek.com/
 */
package st10467645_part1;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author ST10467645
 */
public class Login {

    //Declaring instance variables to store the registered username and password of the registered user.
    private String registeredUsername;
    private String registeredPassword;


    /*Method to check if username is valid: 
    Ensures that entered username contains an underscore
    and is no more than 5 characters.
     */
    public boolean checkUserName(String username) {

        return username.length() <= 5 && username.contains("_");
    }

    /*Method to check if password is valid:
    Ensures that entered password is at least eight 
    characters,contains a capital letter, contains
    a number,and contains a special character
     */
    public boolean checkPasswordComplexity(String password) {

        //Checks if entered password lenght is less than eight characters.
        if (password.length() < 8) {

            return false;

        }

        //Initialsing boolean values to check if each password requirement is valid.
        boolean containsCapitalLetter = false;
        boolean containsNumber = false;
        boolean containsSpecialChar = false;

        //Using a for loop to go through each character in the password to check all the conditions.
        for (int i = 0; i < password.length(); i++) {

            //Converting the password string into individual characters to validate all characters in the entered password.
            char p = password.charAt(i);

            //Checks if the entered password contains a Capital letter.
            if (Character.isUpperCase(p)) {

                containsCapitalLetter = true;

            }

            //Checks if the entered password contains a digit.
            if (Character.isDigit(p)) {

                containsNumber = true;

            }

            //Checks if the entered password contains a special character.
            if ((!Character.isLetter(p) && !Character.isDigit(p) && !Character.isWhitespace(p))) {

                containsSpecialChar = true;

            }
        }

        //Returns true if all the password requirements are met, otherwise returns false.
        return containsCapitalLetter && containsNumber && containsSpecialChar;

    }

    /*Method to check if cell phone number is valid:
    Ensures that entered cell phone number is the correct length
    and contains the international country code.
    
    Code attribution:
    Code idea adapted from DeepSeek (2025), which demonstrates using regex to validate cell phone numbers.
    Retrieved from: https://www.deepseek.com/
     */
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {

        //Removes any white spaces from the entered cell phone number.
        String cleanedCellNumber = cellPhoneNumber.replaceAll("\\s+", "");

        //Defines a regex pattern to match any South African numbers starting with +27 followed by exactly 9 digits.
        Pattern pattern = Pattern.compile("^\\+27[0-9]{9}$");

        //Matches the cleaned cell phone number against the pattern.
        Matcher matcher = pattern.matcher(cleanedCellNumber);

        //Returns true if the entered number matches the pattern, otherwise returns false.
        return matcher.matches();

    }

    /* Method to register a user:
    Checks if the entered username is correctly formatted 
    and if the password meets complexity requirements.
    If both the username and password pass,
    registration is successful.
     */
    public String registerUser(String username, String password) {

        //Checks if the entered username is formatted correctly.
        if (!checkUserName(username)) {

            //Returns an error message if entered username format is incorrect.
            return "Username is incorrectly formatted.";

        }

        //Checks if the entered password meets complexity requirements.
        if (!checkPasswordComplexity(password)) {

            //Returns an error message if entered password does not meet complexity requirements.
            return "Password does not meet complexity requirements.";

        }

        //Stores the registered username.
        this.registeredUsername = username;

        //Stores the registered password.
        this.registeredPassword = password;

        //If the entered username is correctly formatted and entered password meets complexity requirements, returns a success message.
        return "User registered successfully.";
    }

    /* Method to log in a user:
    Verifies that the entered username and password
    match the stored registered username and password.
     */
    public boolean loginUser(String username, String password) {

        //Compares the entered username and password with the stored registered username and password.
        //If both match then returns true, otherwise returns false.
        return this.registeredUsername.equals(username) && this.registeredPassword.equals(password);
    }
    
    /* Method to return the login status:
   Checks if the entered username and password are valid.
   If the login is successful, a personalized welcome message is returned.
   If the login fails, an error message is returned.
    */
    public String returnLoginStatus(String username, String password, String userFirstName, String userLastName ){
        
        //Checks if the login credentials are valid using the loginUser method.
        if (loginUser(username, password)) {
            
            //Returns a welcome message if login is successful.
           return "Successful login: \nWelcome, "  + userFirstName + " " +  userLastName + ", it is great to see you again.";
            
        } else {
            
            //Returns an error message if login fails due to incorrect username or password.
            return "Failed login: \nUsername or password incorrect, please try again.";
        }
    
    
    } 
}

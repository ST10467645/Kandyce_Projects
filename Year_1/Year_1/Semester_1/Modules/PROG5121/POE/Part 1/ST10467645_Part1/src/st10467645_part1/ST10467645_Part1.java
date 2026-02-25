/**
 * POE Part 1: Creating a registration and login feature.
 * 
 * This class handles the main program for user registration and login.
 * It prompts the user for their first name, last name, username, password, 
 * and cell phone number, validates the inputs, and then registers the user.
 * Lastly, it prompts for login credentials and validates them for access.
 * 
 */
package st10467645_part1;

import javax.swing.JOptionPane;

/**
 *
 * @author ST10467645
 */
public class ST10467645_Part1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Instantiates the login class.
        Login ln = new Login();

        //Prompts the user for their first name and stores the entered first name.
        String userFirstName = JOptionPane.showInputDialog("Enter your first name.");

        //Prompts the user for their last name and stores the entered last name.
        String userLastName = JOptionPane.showInputDialog("Enter your last name.");

        //Prompts the user for a username with requirements and stores the entered username.
        String userName = JOptionPane.showInputDialog("Create a Username that: \n*Contains an underscore. \n*Is no more than five characters in length.");

        //Validates if the entered username meets the requirements using the checkUserName method.
        if (ln.checkUserName(userName)) {

            //Displays a success message if the entered username meets the requirements.
            JOptionPane.showMessageDialog(null, "Username successfully captured.");

        } else {

            //Displays an error message if the entered username does not meet requirements.
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");

            //Exits the program if entered username is incorrectly formatted.
            return;
        }

        //Prompts the user for a password with requirements and stores the entered password.
        String password = JOptionPane.showInputDialog("Create a password that: \n*Is at least eight characters long. \n*Contains a capital letter. \n*Contains a number. \n*Contains a special character.");

        //Validates if the entered password meets the requirements using the checkPasswordComplexity method.
        if (ln.checkPasswordComplexity(password)) {

            //Displays a success message if the entered password meets requirements.
            JOptionPane.showMessageDialog(null, "Password successfully captured.");

        } else {

            //Displays an error message if the entered password does not meet the requirements.
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");

            //Exits the program if entered password is incorrectly formatted.
            return;
        }

        //Prompts the user for a cell Phone number with requirements and stores the entered cell phone number.
        String cellPhoneNumber = JOptionPane.showInputDialog("Enter your cell phone number that : \n*Contains your international country code. \n*Is followed by your number that is no more than ten characters long.");

        //Validates if the entered cell phone number meets the requirements using the checkCellPhoneNumber method.
        if (ln.checkCellPhoneNumber(cellPhoneNumber)) {

            //Displays a success message if the entered cell phone number meets requirements.
            JOptionPane.showMessageDialog(null, "Cell phone number successfully added.");

        } else {

            //Displays an error message if the entered cell phone number does not meet the requirements.
            JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or does not contain international code.");

            //Exits the program if entered cell phone number is incorrectly formatted.
            return;
        }

        //Validates and displays the result of the entered username and password using the registerUser method.
        JOptionPane.showMessageDialog(null, ln.registerUser(userName, password));

        //Displays a message to prompt the user to enter login details.
        JOptionPane.showMessageDialog(null, "To login to your account, use the same username and password you registered with.  ");

        //Prompts the user for their registered username and stores it.
        String registeredUsername = JOptionPane.showInputDialog("Enter your username");

        //Prompts the user for their registered password and stores it.
        String registeredPassword = JOptionPane.showInputDialog("Enter your password.");

         //Validates whether the entered username and password match the registered credentials using the returnLoginStatus method, and displays a success or error message.
        JOptionPane.showMessageDialog(null, ln.returnLoginStatus(registeredUsername, registeredPassword, userFirstName, userLastName));

    }

}

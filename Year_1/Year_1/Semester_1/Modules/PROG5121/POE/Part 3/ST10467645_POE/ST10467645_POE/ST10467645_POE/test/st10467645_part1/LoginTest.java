/**
 * POE Part 1: Testing the registration and login feature.
 * 
 * This class tests all the methods in the Login class. It checks if the username, 
 * password, and phone number validation work as expected, and also tests if users can 
 * successfully register, log in, and get the correct login status. It covers both 
 * the successful cases and the failed ones for each method.
 * 
 */
package st10467645_part1;

import st10467645_POE.Login;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ST10467645
 */
public class LoginTest {

    public LoginTest() {
    }

    /**
     * Test of checkUserName method, of class Login with assertEquals.
     */
    //Test for correctly formatted username with assertEquals.
    @Test
    public void testCheckUserNamePass_CorrectFormat() {
        System.out.println("Welcome <user first name>, <user last name> it is great to see you.");
        String username = "kyl_1";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
    }

    // Test for incorrectly formatted username with assertEquals.
    @Test
    public void testCheckUserNameFail_IncorrectFormat() {
        System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
        String username = "kyle!!!!!!!";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkPasswordComplexity method, of class Login with assertEquals.
     */
    //Test for password meeting complexity requirements with assertEquals.
    @Test
    public void testCheckPasswordComplexityPass_CorrectFormat() {
        System.out.println("Password successfully captured.");
        String password = "Ch&&sec@ke99";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);
    }

    //Test for password not meeting complexity requirements with assertEquals.
    @Test
    public void testCheckPasswordComplexityFail_IncorrectFormat() {
        System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        String password = "password";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkCellPhoneNumber method, of class Login with assertEquals.
     */
    //Test for correctly formatted cellphone number with assertEquals.
    @Test
    public void testCheckCellPhoneNumberPass_CorrectFormat() {
        System.out.println("Cell number successfully captured.");
        String cellPhoneNumber = "+27838968976";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkCellPhoneNumber(cellPhoneNumber);
        assertEquals(expResult, result);

    }

    //Test for incorrectly formatted cellphone number with assertEquals.
    @Test
    public void testCheckCellPhoneNumberFail_IncorrectFormat() {
        System.out.println("Cell phone number incorrectly formatted or does not contain international code, please correct the number and try again.");
        String cellPhoneNumber = "08966553";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber(cellPhoneNumber);
        assertEquals(expResult, result);

    }

    /**
     * Test of registerUser method, of class Login with assertEquals.
     */
    //Test for successful registration with assertEquals.
    @Test
    public void testRegisterUserPass() {
        System.out.println("User registered successfully.");
        String username = "kyl_1";
        String password = "Ch&&sec@ke99";
        Login instance = new Login();
        String expResult = "User registered successfully.";
        String result = instance.registerUser(username, password);
        assertEquals(expResult, result);

    }

    /**
     * Test of returnLoginStatus method, of class Login with assertEquals.
     */
    //Test for successful login status with assertEquals.
    @Test
    public void testReturnLoginStatusPass_Successful() {
        System.out.println("Login Status: Successful");
        String username = "kyl_1";
        String password = "Ch&&sec@ke99";
        String userFirstName = "Kyle";
        String userLastName = "Ches";
        Login instance = new Login();
        instance.registerUser(username, password);
        String expResult = "Successful login: \nWelcome, " + userFirstName + " " + userLastName + ", it is great to see you again.";
        String result = instance.returnLoginStatus(username, password, userFirstName, userLastName);
        assertEquals(expResult, result);

    }

    //Test for failed login status with assertEquals with assertEquals.
    @Test
    public void testReturnLoginStatus_Failed() {
        System.out.println("Login Status: Failed");
        String correctUsername = "kyl_1";
        String correctPassword = "Ch&&sec@ke99";
        String wrongUsername = "kyle!!!!!!!";
        String wrongPassword = "password";
        String userFirstName = "Kyle";
        String userLastName = "Ches";
        Login instance = new Login();
        instance.registerUser(correctUsername, correctPassword);
        String expResult = "Failed login: \nUsername or password incorrect, please try again.";
        String result = instance.returnLoginStatus(wrongUsername, wrongPassword, userFirstName, userLastName);
        assertEquals(expResult, result);

    }

    /**
     * Test of loginUser method, of class Login with assertTrue/False.
     */
    //Test for successful login with assertTrue.
    @Test
    public void testLoginUserPass_Successful() {
        System.out.println("True - login successful");
        String username = "kyl_1";
        String password = "Ch&&sec@ke99";
        Login instance = new Login();
        instance.registerUser(username, password);
        boolean result = instance.loginUser(username, password);
        assertTrue(result);

    }

    //Test for failed login with assertFalse.
    @Test
    public void testLoginUser_Fail() {
        System.out.println("False - login Failed");
        String correctUsername = "kyl_1";
        String correctPassword = "Ch&&sec@ke99";
        String wrongUsername = "kyle!!!!!!!";
        String wrongPassword = "password";
        Login instance = new Login();
        instance.registerUser(correctUsername, correctPassword);
        boolean result = instance.loginUser(wrongUsername, wrongPassword);
        assertFalse(result);
    }

    /**
     * Test of checkUserName method, of class Login with assertTrue/False.
     */
    //Test for correctly formatted username with assertTrue.
    @Test
    public void testCheckUserNamePass_AssertTrue() {
        System.out.println("True - Username correctly formatted.");
        String username = "kyl_1";
        Login instance = new Login();
        assertTrue(instance.checkUserName(username));
    }

    //Test for correctly formatted username with assertFalse.
    @Test
    public void testCheckUserNameFail_AssertFalse() {
        System.out.println("False - Username incorrectly formatted.");
        String username = "kyle!!!!!!!";
        Login instance = new Login();
        assertFalse(instance.checkUserName(username));
    }

    /**
     * Test of checkPasswordComplexity method, of class Login with
     * assertTrue/False.
     */
    //Test for password meeting complexity requirements with assertEquals.
    @Test
    public void testCheckPasswordComplexityPass_AssertTrue() {
        System.out.println("True - Password meets complexity requirements.");
        String password = "Ch&&sec@ke99";
        Login instance = new Login();
        assertTrue(instance.checkPasswordComplexity(password));
    }

    //Test for password not meeting complexity requirements with assertEquals.
    @Test
    public void testCheckPasswordComplexityFail_AssertFalse() {
        System.out.println("False - Password does not meet complexity requirements.");
        String password = "password";
        Login instance = new Login();
        assertFalse(instance.checkPasswordComplexity(password));
    }

    /**
     * Test of checkCellPhoneNumber method, of class Login with
     * assertTrue/False.
     */
    //Test for correctly formatted cellphone number with assertEquals.
    @Test
    public void testCheckCellPhoneNumberPass_AssertTrue() {
        System.out.println("True - Cell number correctly formatted.");
        String cellPhoneNumber = "+27838968976";
        Login instance = new Login();
        assertTrue(instance.checkCellPhoneNumber(cellPhoneNumber));

    }

    //Test for incorrectly formatted cellphone number with assertEquals.
    @Test
    public void testCheckCellPhoneNumberFail_AssertFalse() {
        System.out.println("False - Cell number incorrectly formatted.");
        String cellPhoneNumber = "08966553";
        Login instance = new Login();
        assertFalse(instance.checkCellPhoneNumber(cellPhoneNumber));

    }

}

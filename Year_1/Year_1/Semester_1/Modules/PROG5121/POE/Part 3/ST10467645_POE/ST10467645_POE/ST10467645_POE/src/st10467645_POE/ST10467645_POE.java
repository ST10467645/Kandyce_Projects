/**
 * Main class for the QuickChat Messaging Program (Part 1, 2, and 3 - POE)
 *
 * This class handles all user interaction for the QuickChat system using dialog boxes (JOptionPane).
 * It guides the user from registration to login and provides full messaging functionality, including
 * sending, storing, discarding, viewing, deleting, and reporting on messages.
 *
 * Part 1:
 * - Allows the user to register with their first name, last name, username, password, and phone number.
 * - Validates the username to make sure it contains an underscore and is no more than 5 characters.
 * - Validates the password to make sure it is at least 8 characters, has a capital letter, number, and special character.
 * - Validates the phone number to make sure it is in international format and contains 10 digits or fewer.
 * - If registration is successful, the user can log in with their details.
 * - Login is only successful if the username and password match the saved values.
 *
 * Part 2:
 * - Once logged in, a menu is shown where the user can send messages, view messages, or exit.
 * - If the user chooses to send messages, they must enter the number of messages they want to send.
 * - Each message must have a valid international phone number and be 250 characters or less.
 * - A unique 10-digit message ID is generated for each message.
 * - A message hash is created using string manipulation and loop counters, based on the message ID.
 *
 * Part 3(POE):
 * - Adds a second menu for viewing sent/stored messages and reports.
 * - Users can:
 *   | View sender & recipient info
 *   | See the longest message sent and/or stored
 *   | Search for a message by its ID
 *   | View all messages sent to a specific recipient
 *   | Delete a message using its hash
 *   | View a full report of all sent messages (with ID, hash, recipient, content)
 *
 * - Includes a method to clear all session messages before exiting.
 *
 *️ All methods in this class rely on the Login and Message classes to perform validations,
 * message storage, ID/hash generation, and reporting logic.
 */
package st10467645_POE;

import javax.swing.JOptionPane;

/**
 *
 * @author ST10467645
 */
public class ST10467645_POE {

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

        //Checks if the login credentials entered by the user match the registered credentials. If they are correct, they are allowed to proceed.
        if (ln.loginUser(registeredUsername, registeredPassword)) {

            //Continuous loop to keep displaying the menu until the user chooses to exit.
            while (true) {

                Message ms = new Message();

                //A multi-line string to represent the main menu for the user where the menu prompts the user three options to choose from.
                String menu = """
                      Welcome to quick chat.
                              
                      To select a feature please enter only the NUMBER 
                      for the corresponding option you want to select :
                      
                      Option 1 - Enter 1 to send messages.
                      Option 2 - Enter 2 to show recently sent messages.
                      Option 3 - Enter 3 to quit
                      """;

                //Stores the user's chosen option number.
                int enteredNum = 0;

                //Used to check whether the user entered a valid option.
                boolean isValidNumber = false;

                //Keeps asking the user for input until a valid number is entered.
                while (!isValidNumber) {

                    //Displays the menu and prompts user for option.
                    String input = JOptionPane.showInputDialog(menu);

                    //Checks if the input is only one character and is either 1, 2, or 3.
                    if (input.length() == 1 && "123".contains(input)) {

                        //Converts the valid input into an integer and stores it.
                        enteredNum = Integer.parseInt(input);

                        //Once input is valid, loop stops.
                        isValidNumber = true;

                    } else {

                        //An error message is shown, if the user enters anything other than 1, 2, or 3.
                        JOptionPane.showMessageDialog(null, "Invalid input! Enter ONLY 1, 2, or 3.");

                    }
                }

                //Switch statement used to determine which option the user selected from the menu.
                switch (enteredNum) {
                    case 1:

                        // Calls the sendMessage() method to begin the message sending process.
                        oppSendMessage();

                        break;

                    //If the user selects 2, the second menu opens to show options for recently sent messages.
                    case 2:
                        oppShowRecentlySentMessages(cellPhoneNumber);
                        break;

                    //If the user selects 3, a messageis displayed that tells the user that it will exit, then exits the application.
                    case 3:

                        Message.clearSessionMessages();

                        //Clears session messages before exiting using the method from message class.
                        JOptionPane.showMessageDialog(null, "Exiting menu.");

                        System.exit(0);
                        break;

                }
            }

        } else {

            //Ends the program if the login attempt fails.
            System.exit(0);
        }

    }

    /**
     * Handles the process of sending messages when user selects the send
     * message option. This method prompts the user for the number of messages
     * to send and validates the recipient's phone number. It loops through each
     * message, ensures the message is within the 250-character limit, and uses
     * the Message class to process the user's action for each message (send,
     * store, or discard). It provides feedback for invalid inputs and allows
     * retrying when needed.
     *
     *
     */
    public static void oppSendMessage() {

        //Instantiates the Message class.
        Message ms = new Message();

        //Variables to store the user’s input for how many messages they want to send.
        String sentMessNumStr = "";
        int sentMessNum = 0;

        while (true) {

            // Prompt the user for the number of messages they want to send.
            sentMessNumStr = JOptionPane.showInputDialog("Enter the numerical value of how many messages you wish to send.");

            //Checks if the input is a valid number.
            boolean isValidNumber = true;

            for (int i = 0; i < sentMessNumStr.length(); i++) {

                //If any character is not a numerical value, it's not a valid number.
                if (!Character.isDigit(sentMessNumStr.charAt(i))) {

                    isValidNumber = false;

                    break;
                }
            }

            //If everything typed in was digits and it's not empty
            if (isValidNumber && sentMessNumStr.length() > 0) {

                //Converts the string to a int so it can be used for looping
                sentMessNum = Integer.parseInt(sentMessNumStr);

                //The loop exits if a valid number is entered.
                break;

            } else {

                // If the input is not a valid number, an error message is displayed.
                JOptionPane.showMessageDialog(null, "Enter a valid number.");

            }
        }

        //Variables to store the recipient's phone number and the result of the number check.
        String recipientNum;
        int checkNum;

        // Continuously prompts the user to enter a valid recipient cell phone number until the input passes validation.
        while (true) {

            //Prompts the user to enter the recipient's cell phone number.
            recipientNum = JOptionPane.showInputDialog("Enter the cell phone number of who you wish to send a messages/messages to \nEnsure that the cell phone number: \n*Has no more than ten characters. \n*Contains an international code.");

            //Uses the checkRecipientCell method from the message class to validate the recipient's cell phone number.
            checkNum = ms.checkRecipientCell(recipientNum);

            //Checks if the recipient's number is valid.
            if (checkNum == 1) {

                //Displays confirmation message that the number is valid.
                JOptionPane.showMessageDialog(null, "Cell phone number successfully captured.");

                //Instantiate a counter to keep track of how many messages have been sent.
                int messagesSent = 0;

                //Loops for the number of messages the user wants to send.
                for (int i = 0; i < sentMessNum; i++) {

                    //Prompts the user to input each message one by one.
                    String message = JOptionPane.showInputDialog("Enter message number " + (i + 1) + ": ");

                    String lengthCheck = ms.messageLength(message);

                    //Checks if the entered message exceeds the 250-character limit.
                    if (lengthCheck.contains("exceeds")) {

                        //Displays an error message if message exceeds character limit.
                        JOptionPane.showMessageDialog(null, lengthCheck);

                        //Decrements the loop index to allow the user to re-enter the current message.
                        i--;

                    } else {

                        //Displays a success message if message is within character limit.
                        JOptionPane.showMessageDialog(null, lengthCheck);

                        //Increments the count of messages sent by 1.
                        messagesSent++;

                        //Variable to store the generated message ID.
                        String messageID;

                        //Used to check if the generated ID is valid.
                        boolean isValidID = false;

                        //Keeps generating a new message ID until a valid one is found.
                        do {

                            //Generates a random message ID using generateMessageID method from the Message class.
                            messageID = Message.generateMessageID();

                            //Checks if the generated ID is valid (exactly 10 digits) using the checkMessageID method from the message class.
                            isValidID = ms.checkMessageID(messageID);

                            if (!isValidID) {

                                //An error message is displayed if the ID is invalid.
                                System.out.println("Invalid ID generated: " + messageID + "\nNot exactly 10 digits.");

                            }

                            //Loop continues until a valid (10-digit) ID is created
                        } while (!isValidID);

                        //Creates a message hash for each message using the createMessageHash from the message class.
                        String messageHash = ms.createMessageHash(messageID, i, message);

                        //Checks if the maximum message capacity has been reached by comparing totalMessages to MAX_MESSAGES.
                        if (Message.totalMessages >= Message.MAX_MESSAGES) {

                            //Notifies the user that no more messages can be sent/stored due to reaching the maximum limit.
                            JOptionPane.showMessageDialog(null, "Maximum messages reached: " + Message.MAX_MESSAGES);

                            //Exits the message-sending loop immediately since no more space is available.
                            break;

                        }

                        //Uses the sentMessage method to display options for user to selct and stores the response.
                        String messageOpt = ms.sentMessage(recipientNum, message);

                        //Checks if the user chose to send the message by comparing the result of messageOpt.
                        if (messageOpt.equals("Message successfully sent.")) {

                            //Stores the current message in the sentMessages array at the index of totalMessages to track every message the user has sent during the session.
                            Message.sentMessages[Message.totalMessages] = message;
                        }

                        //Stores the generated hash for the current message at the same index so that a message can be deleted using the message hash. 
                        Message.messageHashes[Message.totalMessages] = messageHash;

                        //Stores the unique 10-digit message ID for this message to uniquely identify each message during each session.
                        Message.messageIDs[Message.totalMessages] = messageID;

                        //Stores the recipient's phone number linked to each message to track who each message was sent to.
                        Message.messageRecipients[Message.totalMessages] = recipientNum;

                        //For if the user chose to discard the message.
                        if (messageOpt.equals("Message discarded.")) {

                            //Stores the discarded message in the disregardedMessages array at the current index to keep a record of messages that were discarded by the user.
                            Message.disregardedMessages[Message.totalMessages] = message;

                        } else if (messageOpt.equals("Message successfully stored")) {

                            //Stores the message in the storedMessages array at the current index to keep track of messages the user chose to save for later.
                            Message.storedMessages[Message.totalMessages] = message;
                        }

                        //Once the message is saved, the total message count is increased so each message is stored separately.
                        Message.totalMessages++;

                        //Displays the result of the chosen message action: sent, stored, or discarded.
                        JOptionPane.showMessageDialog(null, messageOpt);

                        //Displays a message summary including the message ID, message hash, recipient and the message.
                        JOptionPane.showMessageDialog(null, "Message Summary \n\nMessage ID: " + messageID + "\nMessage Hash: " + messageHash + "\nRecipient Number: " + recipientNum + "\nMessage: " + message);

                    }

                }

                //Calls the returnTotalMessages method from the Message class to calculate how many valid messages were sent.
                int totalMessages = ms.returnTotalMessages(messagesSent);

                //Displays the total number of messages sent in a message dialog to the user.
                JOptionPane.showMessageDialog(null, "Total Messgaes Sent: " + totalMessages + "\n\n" + ms.printMessages());

                break;

            } else {

                //Displays an error message if the cell number is invalid.
                JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted or does not contain an international code. \nPlease correct the number and try again.");
            }
        }
    }

    /**
     * This method displays a menu for recently sent message options where the
     * user can view senders and recipients, find the longest message, search
     * messages by ID or recipient, delete messages by hash, view a full report,
     * or return to the main menu.
     *
     * This method also runs in a loop, showing the menu repeatedly until the
     * user chooses to return to the main menu.
     *
     * @param senderCell The phone number of the current sender to show message
     * info for.
     */
    public static void oppShowRecentlySentMessages(String senderCell) {

        //An infinite loop to keep showing the menu until the user chooses to exit.
        while (true) {

            //A multi-line string menu that lists all the options available for recently sent messages.
            String menu2 = """
                           Options for recently sent messages.
                           
                           To select an option please enter only the NUMBER 
                           for the corresponding option you want to select :
                           
                           Option 1: View sender and recipient of all sent messages.
                           Option 2: View the longest sent and/or stored message.
                           Option 3: Search for message recipient and message with message ID.
                           Option 4: Search for all messages sent to a recipient.
                           Option 5: Delete a message using the message hash.
                           Option 6: View full report of all sent messages.
                           Option 7: Return to main menu.
                           """;

            //Displays the menu and prompts user for option.
            String choice2 = JOptionPane.showInputDialog(menu2);

            //If the user selects cancel or closes the input dialog, a message is displayed and the loop ends. 
            if (choice2 == null) {

                JOptionPane.showMessageDialog(null, "Returning to main menu.");
                break;

            }

            //Switch statement used to determine which option the user selected from the menu.
            switch (choice2) {

                //If the user selects 1, a list of all senders and recipients of messages are displayed.
                case "1":
                    JOptionPane.showMessageDialog(null, Message.a_SendersAndRecipients(senderCell));
                    break;

                //If the user selects 2, the longest message that has been sent in the current session is displayed.
                case "2":
                    JOptionPane.showMessageDialog(null, Message.b_GetLongestSentMessage());
                    break;

                //If the user selects 3, the recipient and message, if found, that matches the message ID is then displayed.
                case "3":

                    //Prompts the user to enter a message ID to use for searching.
                    String msgID = JOptionPane.showInputDialog("Enter the 10 digit Message ID to \nview the corresponding recipient and message saved to that message ID.");
                    JOptionPane.showMessageDialog(null, Message.c_FindByMessageID(msgID));
                    break;

                //If the user selects 4, all messages sent to the entered recipient are displayed.
                case "4":

                    //Prompts the user to enter the recipients number to search for all messages sent to that number.
                    String recipientCell = JOptionPane.showInputDialog("Enter the recipients phone number to view all messages sent to that recipient.");
                    JOptionPane.showMessageDialog(null, Message.d_FindByRecipient(recipientCell));
                    break;

                //If the user selects 5, the message corresponding to a message hash is deleted and a success or error message is displayed.
                case "5":

                    //Prompts the user for a message hash to delete the corresponding message.
                    String messHash = JOptionPane.showInputDialog("Enter the message hash that corresponds to the message you wish to delete.");

                    //Calls the method from the Message class to delete the message and store whether it was successful.
                    boolean deleted = Message.e_DeleteByHash(messHash);

                    //Declares a message to let the user know if the message was deleted or not.
                    String updateMsg;

                    if (deleted) {

                        //Displays the success message.
                        updateMsg = "Successful: Message with message hash " + messHash + " was successfully deleted.";

                    } else {

                        //Displays an error message.
                        updateMsg = "Error: No message could be found for the following message hash: " + messHash;
                    }

                    //The message result is displayed to the user.
                    JOptionPane.showMessageDialog(null, updateMsg);
                    break;

                //If the user selects 6, a full report of all sent messages is displayed.
                case "6":
                    JOptionPane.showMessageDialog(null, Message.f_FullMessageReport());
                    break;

                //If the user selects 7, a message is displayed that the user will be taken back to the main menu and will return to the main menu.
                case "7":
                    JOptionPane.showMessageDialog(null, "Returning to main menu.");
                    return;

                //An error message is displayed if the user enters anything other than: 1,2,3,4,5,6 or 7.
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please select only a NUMBER from 1 to 7.");
                    break;
            }

        }

    }
}

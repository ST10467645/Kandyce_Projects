/**
 * This class handles all the message functionality for Part 2 and Part 3 of the POE.
 * 
 * Part 2:
 * This class handles everything related to messages.
 * It generates unique message IDs, validates message content,
 * creates message hashes, and stores message details.
 * It also validates phone numbers to ensure they are South African.
 *
 * Part 3(POE):
 * This class was expanded to include message storage, tracking, searching, deleting, and reporting.
 * It now uses parallel arrays to store all sent, stored, and discarded messages along with their
 * recipients, IDs, and hashes. The class also includes methods to:
 * - Store messages in a JSON file
 * - Reset session messages
 * - Display full message reports
 * - Find messages by ID or recipient
 * - Delete messages by their hash
 * - Track the longest message sent or stored
 *
 * The arrays are cleared using the clearSessionMessages() method, and the maximum number of
 * messages allowed per session is set using a constant. Everything is structured to make the
 * data easy to manage and display clearly to the user.
 *
 * References for citation:
 * DeepSeek. (2025). Message retrieval techniques. Retrieved from https://www.deepseek.com/message-retrieval
 * ChatGPT. (OpenAI, 2025). Message storage and management. Retrieved from https://www.chatplatform.com/storage
 * DeepSeek. (2025). Regex for validating cell phone numbers. Retrieved from https://www.deepseek.com/regex-cellphone-validation
 */
package st10467645_POE;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;

/**
 *
 * @author ST10467645
 */
public class Message {

    //Instantiates the login class to use checkCellPhoneNumber method from Login class.
    Login ln = new Login();

    //Path where all messages are saved permanently.
    private static final String FILE_PATH = "messages.json";

    //This is the maximum number of messages that can be stored.
    public static final int MAX_MESSAGES = 100;

    /**
     * Code Attribution:
     *
     * ChatGPT (OpenAI, 2025), retrieved from: https://chat.openai.com/ was used
     * to explain and guide me on how to use parallel arrays and helped use the
     * following parallel arrays:
     */
    //Stores the message text that was sent by the user.
    public static String[] sentMessages = new String[MAX_MESSAGES];

    //Stores messages that the user chose to save instead of send.
    public static String[] storedMessages = new String[MAX_MESSAGES];

    //Stores messages that the user chose to discard.
    public static String[] disregardedMessages = new String[MAX_MESSAGES];

    //Stores the message hash that was created for each message.
    public static String[] messageHashes = new String[MAX_MESSAGES];

    //Stores the unique 10-digit message ID for each message.
    public static String[] messageIDs = new String[MAX_MESSAGES];

    //Stores the recipient cell phone number for each message.
    public static String[] messageRecipients = new String[MAX_MESSAGES];

    //This counter keeps track of how many messages have been processed and makes sure each index in the arrays stays in sync.
    public static int totalMessages = 0;


    /* Method to check if the recipient's cell phone number is valid:
     * This method uses the existing checkCellPhoneNumber method from the Login class
     * to validate that the recipient's number follows the South African format (+27 and 9 digits).
     * 
     * If the number is valid, it returns 1.
     * If the number is invalid, it returns 0.
     */
    public int checkRecipientCell(String cellPhoneNumber) {

        //Checks if the cell phone number is valid using checkCellPhoneNumber method from the Login class.
        if (ln.checkCellPhoneNumber(cellPhoneNumber)) {

            //Returns 1 if the number is valid.
            return 1;

        } else {

            //Returns 0 if the number is invalid.
            return 0;
        }

    }

    /**
     * This method stores a message in a JSON-like format in a file.It takes the
     * message content as an argument and formats it into a JSON structure.The
     * message is then appended to a file called "messages.json".
     *
     * Each message is saved as a separate JSON object, with only the "message"
     * key and its corresponding value being stored.
     *
     * Code attribution: Code idea adapted from ChatGPT (OpenAI, 2025), which
     * assisted with creating a method to store user messages in a JSON-like
     * string format using loops and string manipulation. Retrieved from:
     * https://chat.openai.com/
     *
     * @param recipientNumber
     * @param messageContent
     */
    public static void storeMessage(String recipientNumber, String messageContent) {
        JSONParser parser = new JSONParser();
        JSONArray messagesArray = new JSONArray();

        // Load existing messages if file exists
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Object obj = parser.parse(reader);

            if (obj instanceof JSONArray) {
                messagesArray = (JSONArray) obj;
            } else if (obj instanceof JSONObject) {
                messagesArray.add(obj);
            }
        } catch (IOException | ParseException e) {
            // File not found or empty - start new array
        }

        // Create a new message object
        JSONObject messageObject = new JSONObject();
        messageObject.put("recipient", recipientNumber);
        messageObject.put("message", messageContent);

        // Add the new message to the array
        messagesArray.add(messageObject);

        // Save the updated array back to the JSON file
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(messagesArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            System.out.println("Error saving message: " + e.getMessage());
        }
    }

    /**
     * This method clears all messages that were saved during the current
     * session. This so that when the program ends or restarts, it does not keep
     * old messages. It resets all parallel arrays and sets the total message
     * counter to 0.
     */
    public static void clearSessionMessages() {
        for (int i = 0; i < MAX_MESSAGES; i++) {

            sentMessages[i] = null;
            storedMessages[i] = null;
            disregardedMessages[i] = null;
            messageHashes[i] = null;
            messageIDs[i] = null;
            messageRecipients[i] = null;

        }

        //Resets message counter to clear session so that Max_Messages has space to store 100 messages each session.
        totalMessages = 0;
    }

    /**
     * Method that checks if the message is too long to send.
     *
     * If the message is more than 250 characters, it calculates how many
     * characters over the limit it is and tells the user to shorten it.
     *
     * If the message is within the limit, it confirms that it’s ready to be
     * sent.
     *
     * @param message The message the user typed in
     * @return A response saying whether the message is too long or okay to send
     */
    public String messageLength(String message) {

        //Checks if the entered message exceeds the 250-character limit.
        if (message.length() > 250) {

            int excess = message.length() - 250;

            //Displays an error message if message exceeds character limit.
            return "Message exceeds characters by an amount of " + excess + ", please reduce size.";

        } else {

            //Displays a success message if message is within character limit.
            return "Message ready to send.";
        }

    }

    /**
     * Method to manage how a message is handled: Allows the user to choose what
     * to do with a message: - Send the message - Disregard the message - Store
     * the message
     *
     * If the user selects to disregard the message, another prompt is shown,
     * allowing them to confirm discard (by entering 0) or return to the main
     * menu (any other input).
     *
     * @param recipientNumber
     * @param message The message content that the user will decide to send,
     * disregard, or store.
     * @return A confirmation message that shows the result of the user's choice
     * (e.g., message sent, discarded, stored, or invalid option).
     */
    public String sentMessage(String recipientNumber, String message) {

        while (true) {

            //Prompts the user to choose an option for the entered message and stores that option.
            String messageOpt = JOptionPane.showInputDialog("Options for the following message: \n\n" + message + "\n\n*Enter 1 to send the message \n*Enter 2 to disregard the message \n*Enter 3 to store the message");

            //Handles the user's option using a switch statement.
            switch (messageOpt) {

                case "1":

                    //Message is passed through the storeMessage method after it has been sent.
                    storeMessage(recipientNumber, message);

                    //If user chooses to send the message.
                    return "Message successfully sent.";

                case "2":
                    String deleteOpt;

                    //Allows the user to confirm if they want to discard or return to main menu.
                    while (true) {
                        deleteOpt = JOptionPane.showInputDialog("Enter 0 to discard the message.\nEnter any other to continue to next message.");

                        //Checks if the user entered "0" to discard the message, ignoring any extra spaces before or after the input.
                        if ("0".equals(deleteOpt.trim())) {

                            //Confirms message is discarded.
                            return "Message discarded.";

                        } else {

                            //Returns to main menu without discarding.
                            return "Continuing to next message.";

                        }
                    }

                case "3":

                    //If the user chooses to store the message, the message is passed through the storeMessage method.
                    storeMessage(recipientNumber, message);

                    //Message is diplayed to inform the user their message was successfully stored.
                    return "Message successfully stored";

                default:

                    //Handles any invalid option not listed above by displaying error message.
                    JOptionPane.showMessageDialog(null, "Invalid option! Enter 1, 2, or 3.");
            }

        }

    }

    /**
     * This method is a modified version of the original "sentMessage" method,
     * made specifically for testing.
     *
     * Instead of relying on JOptionPane for user input, this version removes
     * all input prompts and replaces them with arguments that simulate the
     * user’s choices. This makes it possible to run automated unit tests via
     * GitHub Actions) without needing any manual interaction.
     *
     * This method does exactly the same thing as the original method as it
     * allows the choice to send, discard, or store a message but takes in the
     * choices directly through parameters.
     *
     * @param recipientNumber The number the message is being sent to.
     * @param message The content of the message.
     * @param option Simulates the user's menu selection: "1" to send, "2" to
     * discard, "3" to store.
     * @param discardOption If the option is "2", this simulates the discard
     * confirmation step: "0" to discard, anything else to skip.
     * @return A message describing what happened based on the simulated input
     * (e.g., sent, discarded, stored, or invalid option).
     */
    public String sentMessageForAutoTest(String recipientNumber, String message, String option, String discardOption) {

        //Handles the user's option using a switch statement.
        switch (option) {
            case "1":

                //Message is passed through the storeMessage method after it has been sent.
                storeMessage(recipientNumber, message);

                //If user chooses to send the message.
                return "Message successfully sent.";

            case "2":

                //Checks if the user entered "0" to discard the message, ignoring any extra spaces before or after the input.
                if ("0".equals(discardOption.trim())) {

                    //Confirms message is discarded.
                    return "Message discarded.";

                } else {

                    //Returns to main menu without discarding.
                    return "Continuing to next message.";

                }

            case "3":

                //If the user chooses to store the message, the message is passed through the storeMessage method.
                storeMessage(recipientNumber, message);

                //Message is diplayed to inform the user their message was successfully stored.
                return "Message successfully stored";

            default:

                //Handles any invalid option not listed above by displaying error message.
                return "Invalid option. Please enter 1, 2, or 3.";
        }
    }

    /**
     * This method generates a random 10-digit number ID.
     *
     * It creates a string containing only digits from 0 to 9, and then uses a
     * loop to randomly select 10 digits from that string to build the message
     * ID.
     *
     * Each digit is chosen by generating a random index from 0 to 9, and the
     * digit at that index is added to the message ID string.
     *
     * This ensures that the message ID is always exactly 10 digits long and
     * contains only numbers.
     *
     * This method was also put in message class for testing purposes.
     *
     * @return A randomly generated 10-digit numeric message ID.
     */
    public static String generateMessageID() {

        //This string has all the digits used to make the message ID.
        String digits = "0123456789";

        //Variable to store the final generated message ID.
        String messageID = "";

        //The loop will loop 10 times to get 10 random digits.
        for (int i = 0; i < 10; i++) {

            //Picks a random number between 0 and 9 to use as an index.
            int index = (int) (Math.random() * 10);

            //Add the digit from that index to the message ID.
            messageID += digits.substring(index, index + 1);
        }

        //Returns the generated 10-digit message ID.
        return messageID;

    }

    /**
     * Method to check if a generated message ID is valid.
     *
     * This method is used to validate that the message ID created is exactly 10
     * digits long. It uses a regular expression to check if the ID only
     * contains numbers and is the correct length.
     *
     * If the ID is not valid, an error message will be displayed to show the
     * incorrect ID.
     *
     * This validation is useful for making sure all message IDs follow the
     * correct format before being accepted or stored.
     *
     * @param messageID The generated ID string that needs to be validated.
     * @return true if the message ID is exactly 10 digits, false if not.
     */
    public boolean checkMessageID(String messageID) {

        //Checks if the ID is exactly 10 digits using a regular expression.
        boolean isValid = messageID.matches("\\d{10}");

        if (!isValid) {

            //If not exactly 10 digits, an error message is displayed.
            System.out.println("Message ID: " + messageID + " generated is invalid.");
        }

        //Returns whether the ID is valid or not.
        return isValid;

    }

    /**
     * Method to create the Message Hash: This method takes in the Message ID,
     * the message number, and the actual message content. It then creates a
     * message hash using parts of that information.
     *
     * First, it takes the first two characters from the Message ID. Then it
     * goes through the message content and checks to find the first word and
     * the last word. If there's only one word in the message, it uses that same
     * word for both the first and last word.
     *
     * It does this by using loops to check for the first and last space in the
     * message. If spaces are found, it extracts the first and last words based
     * on the positions of those spaces.
     *
     * Lastly, it puts everything together in the format:
     * "IDPART:messageNum:FIRSTWORDLASTWORD" (everything is made uppercase).
     * Then it returns that full message hash as a string.
     *
     * @param messageID The ID of the message, we use the first 2 characters of
     * this
     * @param messageNum The number of the message (used in the hash)
     * @param messageContent The full message itself (used to get the first and
     * last word)
     * @return A formatted string with the ID part, number, and the first + last
     * word in caps
     */
    public String createMessageHash(String messageID, int messageNum, String messageContent) {

        //Gets first two characters of message ID.
        String idPart = messageID.substring(0, 2);

        //Initialises first and last words.
        String firstWord = "";

        String lastWord = "";

        //Remove any extra spaces from the start or end of the message to make it easier to find the actual words.
        String trimmedContent = messageContent.trim();

        //Finds the first space in the message to get the first word.
        int firstSpaceIndex = -1;

        //Loop used to find the first word.
        for (int i = 0; i < trimmedContent.length(); i++) {

            if (Character.isWhitespace(trimmedContent.charAt(i))) {

                firstSpaceIndex = i;

                //Stops as soon as the first space is found.
                break;
            }
        }

        //Checks if there are no spaces in the message.
        if (firstSpaceIndex == -1) {

            //If there's no space, the whole message is just one word.
            firstWord = trimmedContent;
            lastWord = trimmedContent;

        } else {

            //If there is a space, uses the first word from the start to that space.
            firstWord = trimmedContent.substring(0, firstSpaceIndex);

            //finds the last space so it can get the last word.
            int lastSpaceIndex = -1;

            //Loop backwards through the message to find the last space.
            for (int i = trimmedContent.length() - 1; i >= 0; i--) {

                //If the character is a space, its position is saved as the last space found.
                if (Character.isWhitespace(trimmedContent.charAt(i))) {

                    lastSpaceIndex = i;

                    //Stops as soon as the last space is found.
                    break;
                }
            }

            if (lastSpaceIndex == -1) {

                //If there is no space, the whole message is used.
                lastWord = trimmedContent;

            } else {

                //Uses the last word after the last space.
                lastWord = trimmedContent.substring(lastSpaceIndex + 1);

            }
        }

        //Returns first two characters of ID + message number + first and last word of message and converts everything to uppercase.
        return (idPart + ":" + messageNum + ":" + firstWord + lastWord).toUpperCase();
    }

    /**
     * Method to return the total number of valid messages sent.
     *
     * This method takes the number of valid messages sent as input and uses a
     * simple loop to count up to that number. It then returns the total count.
     *
     * @param validMessagesSent The number of valid messages sent.
     * @return The total count of valid messages sent.
     */
    public int returnTotalMessages(int validMessagesSent) {

        int count = 0;

        //Loop to increment count from 0 up to validMessagesSent.
        for (int i = 0; i < validMessagesSent; i++) {

            count++;
        }

        //Returns the total count of messages.
        return count;

    }

    /**
     * This method displays all messages that were sent or stored during the
     * current session.
     *
     * It uses only the sentMessages[] and storedMessages[] arrays to build a
     * list of messages along with the recipient's number and the message
     * content.
     *
     * It checks for each message and only includes those that were either sent
     * or stored. If no messages exist in the current session, it returns a
     * message saying so.
     */
    public String printMessages() {

        //Variable to keep track of whether there are any messages to display.
        boolean messagesExist = false;

        //A StringBuilder is used to build the final string that will list all messages.
        StringBuilder messagesList = new StringBuilder("Messages sent/stored in this session:\n\n");

        //Loops through every message slot used in the current session.
        for (int i = 0; i < totalMessages; i++) {

            //This checks for and only includes messages that were sent or stored.
            if (sentMessages[i] != null || storedMessages[i] != null) {

                //If a message was sent or stored, this flag is marked true.
                messagesExist = true;

                //Uses the sent message if it exists, otherwise it will use the stored message.
                String message = sentMessages[i] != null ? sentMessages[i] : storedMessages[i];

                //Uses the recipient from the messageRecipients[] array. If empty, "Unknown Recipient" is displayed.
                String recipient = messageRecipients[i] != null ? messageRecipients[i] : "Unknown Recipient";

                //Adds the recipient number and message content to the string being built.
                messagesList.append("To: ").append(recipient).append("\nMessage: ").append(message).append("\n\n");
            }
        }

        //If no messages were found in the loop above, a message is returned to say that.
        if (!messagesExist) {
            return "No messages have been sent or stored in this session.";
        }

        //Returns the full list of messages that were sent or stored during this session.
        return messagesList.toString();
    }

    /**
     * This method lists all the recipients for messages sent by the user.
     *
     * If no messages have been sent yet, a message is returned. Otherwise, it
     * builds a simple list showing the sender’s number and each recipient’s
     * number.It goes through all stored messages and adds each sender to a
     * recipient pair to the list.
     *
     * @param senderCell The phone number of the sender whose sent messages you
     * want to list.
     * @return A formatted string showing sender and recipients, or a message if
     * no sent messages exist.
     */
    public static String a_SendersAndRecipients(String senderCell) {

        //If the user does not enter any messages a message is returned saying so.
        if (totalMessages == 0) {

            return "No sent messages during this session.";
        }

        //A StringBuilder is used to build the list that starts with a heading.
        StringBuilder sb = new StringBuilder("Sender to Recipient\n\n");

        //Loop through all messages that have been sent/stored.
        for (int i = 0; i < totalMessages; i++) {

            //For if the message at this index was actually sent or stored.
            if (sentMessages[i] != null) {

                //Adds the sender’s number, the word “to”, then the recipient’s number to be displayed all on one line and also shows which message number it correlates to.
                sb.append("Message ").append(i + 1).append(": ").append(senderCell).append(" to ").append(messageRecipients[i]).append("\n");
            }
        }

        //Returns and displays the full list as a string.
        return sb.toString();
    }

    /**
     * This method finds and returns the longest message that was sent and also
     * the longest message that was stored (if any) during the current session.
     *
     * It separately checks sentMessages[] and storedMessages[] and compares the
     * length of each message to find the longest one in each array.
     *
     * If no messages were sent or stored, it returns a message saying that.
     *
     * @return A formatted string showing the longest sent and stored message,
     * or a message if none were found.
     */
    public static String b_GetLongestSentMessage() {

        //String to store the longest message found in sentMessages[].
        String longest = "";

        //String to store the longest message found in storedMessages[]
        String longestStored = "";

        //A loop is used to go through all the messages that have been sent so far in the session.
        for (int i = 0; i < totalMessages; i++) {

            //Checks if each message exists and if it’s longer than what is stored already.
            if (sentMessages[i] != null && sentMessages[i].length() > longest.length()) {

                //If it is longer, this message gets saved as the new longest one.
                longest = sentMessages[i];
            }

            //Checks if each message was stored and if it’s longer than what is stored already.
            if (storedMessages[i] != null && storedMessages[i].length() > longestStored.length()) {

                longestStored = storedMessages[i];

            }
        }

        if (longest.isEmpty() && longestStored.isEmpty()) {

            //If no messages were found, a message is returned saying this.
            return "No longest message found. \n\n This could be due to the fact that no messages have been sent yet in this session.";

        }

        //StringBuilder is used to format the result neatly.
        StringBuilder result = new StringBuilder();

        //If a sent message was found, it is added to the result.
        if (!longest.isEmpty()) {

            result.append("Longest Sent Message:\n").append(longest).append("\n\n");

        } else {

            result.append("No sent messages were found.\n\n");
        }

        //If a stored message was found, it is also added.
        if (!longestStored.isEmpty()) {

            result.append("Longest Stored Message:\n").append(longestStored).append("\n");

        } else {

            result.append("Longest Stored Message: No stored messages were found.\n");
        }

        //Returns and displays the result as a string.
        return result.toString();

    }

    /**
     * This method Searches for a message using its unique message ID.
     *
     * This method looks through all sent messages to find one that matches the
     * given ID. If it finds a match, it returns the recipient's number and the
     * message content. If no message with the given ID is found, it returns a
     * "message ID not found" message.
     *
     * @param id The unique message ID to search for.
     * @return A string showing the recipient and message if found, or a not
     * found message otherwise.
     */
    public static String c_FindByMessageID(String id) {

        //A Loop to search through all messages sent in the session.
        for (int i = 0; i < totalMessages; i++) {

            //Checks if the current message ID matches the one that is being searched for.
            if (id.equals(messageIDs[i])) {

                //If it matches, the recipient and the actual message is returned.
                return "Recipient: " + messageRecipients[i] + "\nMessage  : " + sentMessages[i];
            }
        }

        //If no matching message ID was found, a message is returned saying so.
        return "Message ID not found.";
    }

    /**
     * This method lists all messages that were sent/stored to a specific
     * recipient.
     *
     * It goes through all the sent messages, checks if the recipient matches
     * the number provided and adds each matching message to the list.
     *
     * @param cell The phone number of the recipient whose messages want to
     * find.
     * @return A formatted list of messages sent to the given number.
     */
    public static String d_FindByRecipient(String cell) {

        //A StringBuilder is used to build the list that starts with a heading of messages sent to the recipient.
        StringBuilder sb = new StringBuilder("Messages sent to " + cell + ":\n\n");

        //Variable to see if any messages were found.
        boolean msgFound = false;

        //Loops through all messages that have been sent/stored.
        for (int i = 0; i < totalMessages; i++) {

            //Checks if the message at this index was sent to the entered number.
            if (cell.equals(messageRecipients[i])) {

                //If there is a sent message at this index, it is added to the list.
                if (sentMessages[i] != null) {

                    //Adds the message with a dash in front to display it under the heading.
                    sb.append("-Sent: ").append(sentMessages[i]).append("\n");

                    //Used to show that a message was found.
                    msgFound = true;
                }

                //If there is a stored message at this index, it is added to the list.
                if (storedMessages[i] != null) {

                    //Adds the message with a dash in front to display it under the heading.
                    sb.append("-Stored: ").append(storedMessages[i]).append("\n");

                    //Used to show that a message was found.
                    msgFound = true;
                }

            }
        }

        //If messages were found, the messages are displayed, otherwise an error message is displayed.
        if (msgFound) {

            return sb.toString();

        } else {

            return "Either the entered number is invalid or no messages were sent to this recipient.";
        }

    }

    /**
     * This method deletes a message using its message hash.
     *
     * It loops through all stored message hashes to find a match. If a matching
     * hash is found, it deletes the message and all the messages details such
     * as the message ID, the message recipient, and its stored/disregarded
     * messages.
     *
     * @param hash The unique hash of the message want to delete.
     * @return True if the message was found and deleted, false if no match was
     * found.
     */
    public static boolean e_DeleteByHash(String hash) {

        //Loops through all the stored messages to check for a matching hash.
        for (int i = 0; i < totalMessages; i++) {

            if (hash.equals(messageHashes[i])) {

                //If the hash matches, everything linked to that message is cleared, therefore all values at that index are set to null. 
                sentMessages[i] = null;
                storedMessages[i] = null;
                disregardedMessages[i] = null;
                messageHashes[i] = null;
                messageIDs[i] = null;
                messageRecipients[i] = null;

                //Returns true to confirm that everything was deleted.
                return true;
            }
        }

        //If no matching hash was found, false is returned to show nothing was deleted.
        return false;
    }

    /**
     * This method displays a report of all sent messages and all of its
     * details.
     *
     * It lists the full message hash, the message ID, the recipient's number,
     * and the complete message for each message sent.
     *
     * If no messages have been sent, it returns a message stating that.
     *
     * @return A formatted string showing all sent messages with full details.
     */
    public static String f_FullMessageReport() {

        //If no messages have been sent a message is returned.
        if (totalMessages == 0) {
            return "No messages sent in this session yet.";
        }

        //A stringBuilder to build the report string with a heading.
        StringBuilder sb = new StringBuilder("Message Hash | Message ID | Recipient       | Message\n");

        sb.append("---------------------------------------------------------------------------------\n");

        //Loops through all messages stored so in the session.
        for (int i = 0; i < totalMessages; i++) {

            //Checks if the message at each index was actually sent.
            if (sentMessages[i] != null) {

                //Adds the complete message hash to the report, then adds a " | " to separate it from the next part.
                sb.append(messageHashes[i]).append(" | ");

                //Adds the message ID next, then adds " | " to separate it from the recipient.
                sb.append(messageIDs[i]).append(" | ");

                //Adds the recipient’s phone number, then adds " | " to separate it from the message.
                sb.append(messageRecipients[i]).append(" | ");

                //Adds the full message, then adds a newline to move to the next line in the report.
                sb.append(sentMessages[i]).append("\n");
            }
        }

        //Returns the complete report as a string.
        return sb.toString();
    }

}

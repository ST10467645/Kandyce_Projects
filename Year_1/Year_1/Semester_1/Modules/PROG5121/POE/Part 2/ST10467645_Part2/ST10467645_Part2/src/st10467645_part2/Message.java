/**
 * This class handles everything related to messages.
 * It generates unique message IDs, validates message content,
 * creates message hashes, and stores message details.
 * It also validates phone numbers to ensure they are South African.
 *
 * References for citation:
 * DeepSeek. (2025). Message retrieval techniques. Retrieved from https://www.deepseek.com/message-retrieval
 * Chat. (2025). Message storage and management. Retrieved from https://www.chatplatform.com/storage
 * DeepSeek. (2025). Regex for validating cell phone numbers. Retrieved from https://www.deepseek.com/regex-cellphone-validation
 */
package st10467645_part2;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ST10467645
 */
public class Message {

    //Instantiates the login class to use checkCellPhoneNumber method from Login class.
    Login ln = new Login();

    //Path where all messages are saved permanently.
    private static final String FILE_PATH = "messages.json";

    //List that stores messages just while user is in current session and disappears when program closes.
    private static final List<JSONObject> currentSessionMessages = new ArrayList<>();


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

            // Check if the parsed object is an array or single object
            if (obj instanceof JSONArray) {
                messagesArray = (JSONArray) obj;
            } else if (obj instanceof JSONObject) {
                // If it's a single object, create a new array and add the existing object
                messagesArray.add(obj);
            }
        } catch (IOException | ParseException e) {
            // File not found or empty - start new array
        }

        // Create a new message object
        JSONObject messageObject = new JSONObject();
        messageObject.put("recipient", recipientNumber);
        messageObject.put("message", messageContent);

        // Add to current session list
        currentSessionMessages.add(messageObject);

        // Add the new message to the array
        messagesArray.add(messageObject);

        // Save the updated array back to the JSON file
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(messagesArray.toJSONString());
            file.flush();
            System.out.println("Message saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving message: " + e.getMessage());
        }
    }

    /**
     * This method clears all messages that were saved during the current
     * session. This so that when the program ends or restarts, it does not keep
     * old messages. It just empties the message list so everything starts clean
     * for next time.
     */
    public static void clearSessionMessages() {
        currentSessionMessages.clear();
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
     * Retrieves and formats all messages sent during the current program
     * session.
     *
     * This method loops through the currentSessionMessages list and builds a
     * string showing the recipient and message content for each message.
     *
     * If no messages were sent in the current session, it returns a message
     * saying so.
     *
     * Code attribution: This method was developed with assistance from DeepSeek
     * (2024), which helped with session-based message tracking. Retrieved from:
     * https://www.deepseek.com
     *
     * @return A formatted string of current session messages, or a message
     * saying none were sent.
     */
    public String printMessages() {

        //Checks if there are any messages in the current session.
        if (currentSessionMessages.isEmpty()) {

            //Displays a message if no messages were sent or stored.
            return "No messages have been sent/stored in this session.";
        }

        //Instantiating String builder.
        StringBuilder messagesList = new StringBuilder();

        //Adds the title before listing messages.
        messagesList.append("Messages sent/stored in this session:\n\n");

        //Loops through each message saved in the session.
        for (JSONObject message : currentSessionMessages) {

            //Adds recipient and message content to the list.
            messagesList.append("To: ").append(message.get("recipient")).append("\nMessage: ").append(message.get("message")).append("\n\n");

        }

        //Returns the final formatted string with all session messages.
        return messagesList.toString();
    }

}

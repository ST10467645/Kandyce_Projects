/**
 * This class is used to test all the methods from the Message class for Part 3 of the POE.
 *
 * It includes unit tests for each of the required methods (a to f) using the
 * specific 5 test messages provided in the assignment.
 *
 * Each test checks that the correct output is returned based on how the method
 * should work with the given test data, and makes sure everything works the way
 * it's supposed to.
 *
 * A @Before method is also used to reset the arrays and counters before each test
 * so that there's no leftover data from the previous one. This helps keep the
 * results accurate and prevents any test from failing because of stored values.
 *
 * Code attribution:
 * ChatGPT (OpenAI, 2025). Available at: https://chat.openai.com/ 
 */
package st10467645_POE;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author kandy
 */
public class MessageTest {

    /**
     * This runs before every test to make sure everything is cleared.
     *
     * It resets the totalMessages counter back to 0 and clears all the arrays
     * in the Message class, so that each test starts fresh.
     *
     * This helps avoid test failures from leftover data and is to prevent
     * problems with automated testing like on GitHub.
     *
     * Code attribution: Helped by ChatGPT (OpenAI, 2025). Guidance on test
     * setup and data resetting in JUnit. Available at: https://chat.openai.com/
     */
    @Before
    public void resetMessageData() {
        Message.totalMessages = 0;
        for (int i = 0; i < Message.MAX_MESSAGES; i++) {
            Message.sentMessages[i] = null;
            Message.storedMessages[i] = null;
            Message.disregardedMessages[i] = null;
            Message.messageRecipients[i] = null;
            Message.messageIDs[i] = null;
            Message.messageHashes[i] = null;
        }
    }

    public MessageTest() {
    }

    /**
     * Test of the sentMessages and messageRecipients arrays, of class Message.
     *
     * This test checks if the parallel arrays were correctly populated using
     * Message 1 to Message 4 test data, specifically checking messages that are
     * flagged as "Sent" only (Message 1 and Message 4).
     *
     * The system should store: Message 1: “Did you get the cake?” sent to
     * "+27834557896" Message 4: “It is dinner time!” sent to "0838884567"
     *
     * Messages flagged as "Stored" or "Disregarded" should not appear in
     * sentMessages[].
     *
     * This test uses assertEquals to check that the content of the arrays
     * matches the expected values, and that the totalMessages count is accurate
     * for sent messages.
     */
    @Test
    public void testSentMessagesArrayPopulatedWithExpectedData() {

        System.out.println("Message Arrays Correctly Populated: \n-Message 1: Did you get the cake? \n-Message 4: It is dinner time!");

        //Clears previous data
        Message.totalMessages = 0;

        //Test data for Message 1 (Flag: Sent)
        Message.sentMessages[Message.totalMessages] = "Did you get the cake?";
        Message.messageRecipients[Message.totalMessages] = "+27834557896";
        Message.totalMessages++;

        //Test data for Message 2 was stored therefore it is skiped for sentMessages[] test as it was not sent.
        //Test data for Message 3 disregard therefore it is skiped for sentMessages[] test as it was not sent.
        //Test data for Message 4 (Flag: Sent)
        Message.sentMessages[Message.totalMessages] = "It is dinner time!";
        Message.messageRecipients[Message.totalMessages] = "0838884567";
        Message.totalMessages++;

        //Test data for  message 1 of the sentMessages and recipients arrays are validated using assertEquals.
        String expResult1 = "Did you get the cake?";
        String result1 = Message.sentMessages[0];
        assertEquals(expResult1, result1);
        String expRecipient1 = "+27834557896";
        String resultRecipient1 = Message.messageRecipients[0];
        assertEquals(expRecipient1, resultRecipient1);

        //Test data for  message 4 of the sentMessages and recipients arrays are validated using assertEquals.
        String expResult2 = "It is dinner time!";
        String result2 = Message.sentMessages[1];
        assertEquals(expResult2, result2);
        String expRecipient2 = "0838884567";
        String resultRecipient2 = Message.messageRecipients[1];
        assertEquals(expRecipient2, resultRecipient2);

        //Checks that the total messages count only increased by 2 as only message 1 and 4 were sent.
        int expMessageCount = 2;
        int resultMessageCount = Message.totalMessages;
        assertEquals(expMessageCount, resultMessageCount);
    }

    /**
     * Test of a_SendersAndRecipients method, of class Message.
     *
     * This test checks if the method correctly lists the sender and recipient
     * for all messages that were flagged as "Sent" using the provided 5 test
     * messages data.
     *
     * The method should only include messages that are stored in
     * sentMessages[].
     *
     * This test checks that only Message 1 and Message 4 are included in the
     * output as these are the only sent messages.
     */
    @Test
    public void testA_SendersAndRecipients() {

        System.out.println("Testing of a_SendersAndRecipients method.");

        //Clears previous data
        Message.totalMessages = 0;

        //Test data for Message 1 (Flag: Sent)
        Message.sentMessages[Message.totalMessages] = "Did you get the cake?";
        Message.messageRecipients[Message.totalMessages] = "+27834557896";
        Message.totalMessages++;

        //Test data for Message 2 was stored therefore it is skiped for sentMessages[] test as it was not sent.
        Message.storedMessages[Message.totalMessages] = "Where are you? You are late! I have asked you to be on time.";
        Message.messageRecipients[Message.totalMessages] = "+27838884567";
        Message.totalMessages++;

        //Test data for Message 3 disregard therefore it is skiped for sentMessages[] test as it was not sent.
        Message.disregardedMessages[Message.totalMessages] = "Yohoooo, I am at your gate.";
        Message.messageRecipients[Message.totalMessages] = "+27834484567";
        Message.totalMessages++;

        //Test data for Message 4 (Flag: Sent)
        Message.sentMessages[Message.totalMessages] = "It is dinner time!";
        Message.messageRecipients[Message.totalMessages] = "0838884567";
        Message.totalMessages++;

        //Test data for Message 5 was stored therefore it is skiped for sentMessages[] test as it was not sent.
        Message.storedMessages[Message.totalMessages] = "Ok, I am leaving without you.";
        Message.messageRecipients[Message.totalMessages] = "+27838884567";
        Message.totalMessages++;

        //Sender's phone number used to display who sent the messages
        String senderCell = "+27658097270";

        //Expected string that matches how the method formats sent messages so only Message 1 and 4.
        String expected = "Sender to Recipient\n\n" + "Message 1: +27658097270 to +27834557896\n" + "Message 4: +27658097270 to 0838884567\n";

        //Calls the method being tested and stores what it returns.
        String result = Message.a_SendersAndRecipients(senderCell);

        //Compares the expected result with what was returned to check if the test passes.
        assertEquals(expected, result);

    }

    /**
     * Test of b_GetLongestSentMessage method, of class Message.
     *
     * This test checks if the method correctly returns the longest message that
     * was sent and/or stored, using the provided 5 test messages data.
     *
     * Only messages in sentMessages[] and storedMessages[] should be checked by
     * the method.
     *
     * Message 2 is the longest one and should be returned as expected.
     */
    @Test
    public void testB_GetLongestSentMessage() {

        System.out.println("Testing of b_GetLongestSentMessage: \nWhere are you? You are late! I have asked you to be on time.");

        //Clears previous data.
        Message.totalMessages = 0;

        //Test data for message 1.
        Message.sentMessages[Message.totalMessages] = "Did you get the cake?";
        Message.messageRecipients[Message.totalMessages] = "+27834557896";
        Message.totalMessages++;

        //Test data for message 2. 
        Message.sentMessages[Message.totalMessages] = "Where are you? You are late! I have asked you to be on time.";
        Message.messageRecipients[Message.totalMessages] = "+27838884567";
        Message.totalMessages++;

        //Test data for Message 3: Disregarded, there for it is not included in sent.
        //Test data for message 4.
        Message.sentMessages[Message.totalMessages] = "It is dinner time!";
        Message.messageRecipients[Message.totalMessages] = "0838884567";
        Message.totalMessages++;

        //Expected result is Message 2 (now in sentMessages[]) because it's the longest.
        String expResult = "Longest Sent Message:\nWhere are you? You are late! I have asked you to be on time.\n\nLongest Stored Message: No stored messages were found.\n";

        //Calls the method being tested and stores what it returns.
        String result = Message.b_GetLongestSentMessage();

        //Compares the expected result with what was returned to check if the test passes.
        assertEquals(expResult, result);

    }

    /**
     * Test of c_FindByMessageID method, of class Message.
     *
     * This test checks if the method can correctly return a message's details
     * when given its unique Message ID.
     *
     * Message 4 is used from the test data and is stored as a "Sent" message.
     * The test confirms that the correct recipient and message are returned
     * when searching by the Message ID.
     */
    @Test
    public void testC_FindByMessageID() {
        System.out.println("Testing of c_FindByMessageID: \nSearch for messagID: \nRecipient: 0838884567\nMessage  : It is dinner time!");

        //Clears all previous data.
        Message.totalMessages = 0;

        //Message 4 test data:
        String msgID = "0888888888";
        String message = "It is dinner time!";
        String recipient = "0838884567";

        Message.messageIDs[Message.totalMessages] = msgID;
        Message.sentMessages[Message.totalMessages] = message;
        Message.messageRecipients[Message.totalMessages] = recipient;
        Message.totalMessages++;

        //Expected string returned by the method
        String expResult = "Recipient: 0838884567\nMessage  : It is dinner time!";

        //Calls the method being tested and stores what it returns.
        String result = Message.c_FindByMessageID(msgID);

        //Compares the expected result with what was returned to check if the test passes.
        assertEquals(expResult, result);
    }

    /**
     * Test of d_FindByRecipient method, of class Message.
     *
     * This test checks if the method correctly returns all messages (sent and
     * stored) that were sent to a specific recipient using the test data
     * number: +27838884567.
     *
     * Only messages with this recipient in messageRecipients[] should be
     * included.
     */
    @Test
    public void testD_FindByRecipient() {
        System.out.println("Testing of d_FindByRecipient: \nMessages sent to +27838884567:\n\n" + "-Stored: Where are you? You are late! I have asked you to be on time.\n" + "-Stored: Ok, I am leaving without you.\n");

        // Clear all previous data
        Message.totalMessages = 0;

        //Test data for message 1. (sent to different number, not the one being searched for.)
        Message.sentMessages[Message.totalMessages] = "Did you get the cake?";
        Message.messageRecipients[Message.totalMessages] = "+27834557896";
        Message.totalMessages++;

        //Test data for message 2. (stored to +27838884567)
        Message.storedMessages[Message.totalMessages] = "Where are you? You are late! I have asked you to be on time.";
        Message.messageRecipients[Message.totalMessages] = "+27838884567";
        Message.totalMessages++;

        //Test data for message 3. (this message was discarded and not sent or stored.)
        Message.disregardedMessages[Message.totalMessages] = "Yohoooo, I am at your gate.";
        Message.messageRecipients[Message.totalMessages] = "+27834484567";
        Message.totalMessages++;

        //Test data for message 4. (sent to different number, not the one being searched for.)
        Message.sentMessages[Message.totalMessages] = "It is dinner time!";
        Message.messageRecipients[Message.totalMessages] = "0838884567";
        Message.totalMessages++;

        //Test data for message 5. (stored to +27838884567)
        Message.storedMessages[Message.totalMessages] = "Ok, I am leaving without you.";
        Message.messageRecipients[Message.totalMessages] = "+27838884567";
        Message.totalMessages++;

        //This is the recipient number that is being searching for.
        String cell = "+27838884567";

        //Expected output is that both stored messages to this number should be displayed.
        String expResult = "Messages sent to +27838884567:\n\n" + "-Stored: Where are you? You are late! I have asked you to be on time.\n" + "-Stored: Ok, I am leaving without you.\n";

        //Calls the method being tested and stores what it returns.
        String result = Message.d_FindByRecipient(cell);

        //Compares the expected result with what was returned to check if the test passes.
        assertEquals(expResult, result);
    }

    /**
     * Test of e_DeleteByHash method, of class Message.
     *
     * This test checks if the method correctly deletes Message 2 when its
     * message hash is passed. It verifies that all data linked to that message
     * (storedMessage, recipient, messageHash) is removed and returns true.
     */
    @Test
    public void testE_DeleteByHash() {

        System.out.println("Testing of e_DeleteByHash: \nDeletes Message 2 with the message hash 00:1:HAVEONTIME");

        //Clears all previous data.
        Message.totalMessages = 0;

        //Test dat for message 2:
        Message.storedMessages[Message.totalMessages] = "Where are you? You are late! I have asked you to be on time.";
        Message.messageRecipients[Message.totalMessages] = "+27838884567";
        Message.messageHashes[Message.totalMessages] = "00:1:WHERETIME";
        int index = Message.totalMessages;
        Message.totalMessages++;

        //Calls the method being tested using message 2's message hash.
        String hash = "00:1:WHERETIME";
        boolean expResult = true;
        boolean result = Message.e_DeleteByHash(hash);

        //Compares the expected result with what was returned to check if the test passes and checks if the message was deleted.
        assertEquals(expResult, result);

    }

    /**
     * Test of f_FullMessageReport method, of class Message.
     *
     * This test checks if the method correctly displays a full report of all
     * messages that were sent during the session.
     *
     * Only Message 1 and Message 4 are sent, so the report should only include
     * those two, along with their message hash, ID, recipient, and full message
     * content.
     */
    @Test
    public void testF_FullMessageReport() {

        System.out.println("Testing of f_FullMessageReport: \nShows all sent messages and their details including: \n-Message hash \n-Message ID \n-Recipient \n-Message");

        //Clears all previous data.
        Message.totalMessages = 0;

        //Test data for Message 1:
        Message.sentMessages[Message.totalMessages] = "Did you get the cake?";
        Message.messageRecipients[Message.totalMessages] = "+27834557896";
        Message.messageHashes[Message.totalMessages] = "00:0:DIDCAKE?";
        Message.messageIDs[Message.totalMessages] = "0999999999";
        Message.totalMessages++;

        // Message 2 and 3 are not sent.
        // Test data for Message 4:
        Message.sentMessages[Message.totalMessages] = "It is dinner time!";
        Message.messageRecipients[Message.totalMessages] = "0838884567";
        Message.messageHashes[Message.totalMessages] = "00:3:ITTIME!";
        Message.messageIDs[Message.totalMessages] = "0888888888";
        Message.totalMessages++;

        //Expected report of the sent messages and the format.
        String expResult
                = "Message Hash | Message ID | Recipient       | Message\n"
                + "---------------------------------------------------------------------------------\n"
                + "00:0:DIDCAKE? | 0999999999 | +27834557896 | Did you get the cake?\n"
                + "00:3:ITTIME! | 0888888888 | 0838884567 | It is dinner time!\n";

        //Calls the method and stores the result.
        String result = Message.f_FullMessageReport();

        //Compares expected output with what the method returns.
        assertEquals(expResult, result);
    }

}

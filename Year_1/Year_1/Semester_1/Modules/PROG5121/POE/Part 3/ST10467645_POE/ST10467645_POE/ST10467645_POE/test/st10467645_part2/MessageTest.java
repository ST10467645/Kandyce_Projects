/*
 * Testing of Message Class for Part 2.
 *
 * This JUnit test class is used to test all methods of the Message class from Part 2 of the QuickChat POE.
 * It checks everything related to message validation, length checking, message sending options,
 * ID creation and checking, and hash generation.
 *
 */
package st10467645_part2;

import st10467645_POE.Message;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ST10467645
 */
public class MessageTest {

    public MessageTest() {
    }

    /**
     * Test of all methods for Message 1.
     */
    /**
     * Test of checkRecipientCell method, of class Message.
     *
     * Test for correctly formatted cell number for message one with
     * assertEquals.
     */
    @Test
    public void testCheckRecipientCell_CorrectFormatM1() {
        System.out.println("Message 1 recipient number: \nSuccess: \nCell phone number successfully captured.");
        String cellPhoneNumber = "+27718693002";
        Message instance = new Message();
        int expResult = 1;
        int result = instance.checkRecipientCell(cellPhoneNumber);
        assertEquals(expResult, result);

    }

    /**
     * Test of checkRecipientCell method, of class Message.
     *
     * Test for incorrectly formatted cell number for message one with
     * assertEquals.
     */
    @Test
    public void testCheckRecipientCell_IncorrectFormatM1() {
        System.out.println("Message 1 recipient number: \nFailure: \nCell phone number is incorrectly formatted or does not contain an international code. \nPlease correct the number and try again. ");
        String cellPhoneNumber = "0718693002";
        Message instance = new Message();
        int expResult = 0;
        int result = instance.checkRecipientCell(cellPhoneNumber);
        assertEquals(expResult, result);

    }

    /**
     * Test of messageLength method, of class Message.
     *
     * Test for correct length of message for message one that is 250 or less
     * characters with assertEquals.
     */
    @Test
    public void testMessageLength_CorrectLengthM1() {
        System.out.println("Message 1 message length: \nSuccess: \nMessage ready to send.");
        String message = "Hi mike, can you join us for dinner tonight";
        Message instance = new Message();
        String expResult = "Message ready to send.";
        String result = instance.messageLength(message);
        assertEquals(expResult, result);

    }

    /**
     * Test of messageLength method, of class Message.
     *
     * Test for incorrect length of message for message one that is more than
     * 250 characters with assertEquals.
     */
    @Test
    public void testMessageLength_IncorrectLengthM1() {
        System.out.println("Message 1 message length: \nFailure: \nMessage exceeds characters by an amount of 50, please reduce size.");
        String message = "Hey there! I hope you're having a great day. Just wanted to remind you about the team meeting scheduled for tomorrow at 10am. Please make sure to bring your reports and be prepared to discuss your progress on the recent project assignments. This is some additional text to make sure the message goes over 250 characters. Here are some more words to pad out the length. The quick brown fox jumps over the lazy dog. This sentence is just filler to reach our target length.";
        int excess = message.length() - 250;
        Message instance = new Message();
        String expResult = "Message exceeds characters by an amount of " + excess + ", please reduce size.";
        String result = instance.messageLength(message);
        assertEquals(expResult, result);

    }

    /**
     * Test of sentMessageForAutoTest method, of class Message.
     *
     * Test for option one (Select send, given as test data) of messageSent for
     * message one with assertEquals.
     */
    @Test
    public void testSentMessageForAutoTestM1Opt1() {
        System.out.println("Message 1: \nUser selected -Send Message- : \nMessage successfully sent. ");
        String recipientNumber = "+27718693002";
        String message = "Hi mike, can you join us for dinner tonight";
        String option = "1";
        String discardOption = "0";
        Message instance = new Message();
        String expResult = "Message successfully sent.";
        String result = instance.sentMessageForAutoTest(recipientNumber, message, option, discardOption);
        assertEquals(expResult, result);

    }

    /**
     * Option three "Disregard Message" is tested under message 2.
     *
     * Was not included for message one's test data but was still a required
     * test.
     */
    /**
     * Test of sentMessageForAutoTest method, of class Message.
     *
     * Test for option three (Store message) of messageSent for message one with
     * assertEquals.
     *
     */
    @Test
    public void testSentMessageForAutoTestM1Opt3() {
        System.out.println("Extra test data: \nUser selected -Store Message- : \nMessage successfully stored.");
        String recipientNumber = "+27718693002";
        String message = "Hi mike, can you join us for dinner tonight";
        String option = "3";
        String discardOption = "0";
        Message instance = new Message();
        String expResult = "Message successfully stored";
        String result = instance.sentMessageForAutoTest(recipientNumber, message, option, discardOption);
        assertEquals(expResult, result);

    }

    /**
     * Test of generateMessageID method, of class Message.
     *
     * Test if message ID is created for message one with assertEquals.
     */
    @Test
    public void testGenerateMessageIDM1() {
        String result = Message.generateMessageID();
        System.out.println("Message 1 message ID: \nMessage ID generated: <" + result + ">");
        // Checks if length = 10
        assertEquals(10, result.length());
        //Checks that all characters are digits (0-9)
        assertTrue(result.matches("\\d{10}"));

    }

    /**
     * Test of checkMessageID method, of class Message.
     *
     * Test when message ID is exactly 10 digits (valid) for message one with
     * assertEquals.
     */
    @Test
    public void testCheckMessageIDM1_Valid() {
        System.out.println("Message 1 message ID check: \nValid Message ID: ");
        String messageID = "9669697441";
        System.out.println("Generated Message ID, " + messageID + ", is valid and is no more than 10 digits.");
        Message instance = new Message();
        boolean expResult = true;
        boolean result = instance.checkMessageID(messageID);
        assertEquals(expResult, result);

    }

    /**
     * Test of checkMessageID method, of class Message.
     *
     * Test when message ID is more than 10 digits (invalid) for message one
     * with assertEquals.
     */
    @Test
    public void testCheckMessageIDM1_Invalid() {
        System.out.println("Message 1 message ID check: \nInvalid Message ID: ");
        String messageID = "96696974419";
        Message instance = new Message();
        boolean expResult = false;
        boolean result = instance.checkMessageID(messageID);
        assertEquals(expResult, result);

    }

    /**
     * Test of createMessageHash method, of class Message.
     *
     * Test if message hash is correct for message one with assertEquals.
     */
    @Test
    public void testCreateMessageHashM1() {
        String messageID = "0069697441";
        int messageNum = 0;
        String messageContent = "Hi mike, can you join us for dinner tonight";
        Message instance = new Message();
        String expResult = "00:0:HITONIGHT";
        System.out.println("Message 1 message hash: \n" + expResult);
        String result = instance.createMessageHash(messageID, messageNum, messageContent);
        assertEquals(expResult, result);

    }

    /**
     * Test of all methods for Message 2.
     */
    /**
     * Test of checkRecipientCell method, of class Message.
     *
     * Test for correctly formatted cell number for message two with
     * assertEquals.
     */
    @Test
    public void testCheckRecipientCell_CorrectFormatM2() {
        System.out.println("Message 2 recipient number: \nSuccess: \nCell phone number successfully captured.");
        String cellPhoneNumber = "+27857597588";
        Message instance = new Message();
        int expResult = 1;
        int result = instance.checkRecipientCell(cellPhoneNumber);
        assertEquals(expResult, result);

    }

    /**
     * Test of checkRecipientCell method, of class Message.
     *
     * Test for incorrectly formatted cell number for message two with
     * assertEquals.
     */
    @Test
    public void testCheckRecipientCell_IncorrectFormatM2() {
        System.out.println("Message 2 recipient number: \nFailure: \nCell phone number is incorrectly formatted or does not contain an international code. \nPlease correct the number and try again.");
        String cellPhoneNumber = "08575975889";
        Message instance = new Message();
        int expResult = 0;
        int result = instance.checkRecipientCell(cellPhoneNumber);
        assertEquals(expResult, result);

    }

    /**
     * Test of messageLength method, of class Message.
     *
     * Test for correct length of message for message two that is 250 or less
     * characters with assertEquals.
     */
    @Test
    public void testMessageLength_CorrectLengthM2() {
        System.out.println("Message 2 message length: \nSuccess: \nMessage ready to send");
        String message = "Hi Keggan, did you recieve the payment?";
        Message instance = new Message();
        String expResult = "Message ready to send.";
        String result = instance.messageLength(message);
        assertEquals(expResult, result);

    }

    /**
     * Test of messageLength method, of class Message.
     *
     * Test for incorrect length of message for message two that is more than
     * 250 characters with assertEquals.
     */
    @Test
    public void testMessageLength_IncorrectLengthM2() {
        System.out.println("Message 2 message length: \nFailure: \nMessage exceeds characters by an amount of 50, please reduce size.");
        String message = "Hi Keggan, did you recieve the payment? This is a follow-up to ensure the transaction was completed. Please confirm once processed. Also, here are additional details about the invoice: Invoice #12345, Amount: $500, Due Date: 2023-12-01. For reference, this is a test message to verify the character limit functionality in the system. Ignore if already resolved. This text ensures we exceed 250 characters for testing. 1234567890";
        int excess = message.length() - 250;
        Message instance = new Message();
        String expResult = "Message exceeds characters by an amount of " + excess + ", please reduce size.";
        String result = instance.messageLength(message);
        assertEquals(expResult, result);

    }

    /**
     * Test of sentMessageForAutoTest method, of class Message.
     *
     * Test for option two (Select discard, given as test data) of messageSent
     * for message two with assertEquals.
     */
    @Test
    public void testSentMessageForAutoTestM2Opt2() {
        System.out.println("User selected -Discard Message- : \nPress 0 to delete message.");
        String recipientNumber = "+27857597588";
        String message = "Hi Keegan, did you recieve the payment?";
        String option = "2";
        String discardOption = "0";
        Message instance = new Message();
        String expResult = "Message discarded.";
        String result = instance.sentMessageForAutoTest(recipientNumber, message, option, discardOption);
        assertEquals(expResult, result);

    }

    /**
     * Test of generateMessageID method, of class Message.
     *
     * Test if message ID is created for message two with assertEquals.
     */
    @Test
    public void testGenerateMessageIDM2() {
        String result = Message.generateMessageID();
        System.out.println("Message 2 message ID: \nMessage ID generated: <" + result + ">");
        // Checks if length = 10
        assertEquals(10, result.length());
        //Checks that all characters are digits (0-9)
        assertTrue(result.matches("\\d{10}"));

    }

    /**
     * Test of checkMessageID method, of class Message.
     *
     * Test when message ID is exactly 10 digits (valid) for message two with
     * assertEquals.
     */
    @Test
    public void testCheckMessageIDM2_Valid() {
        System.out.println("Message 2 message ID check: \nValid Message ID: ");
        String messageID = "0880140400";
        System.out.println("Generated Message ID,  " + messageID + ", is valid and is no more than 10 digits");
        Message instance = new Message();
        boolean expResult = true;
        boolean result = instance.checkMessageID(messageID);
        assertEquals(expResult, result);

    }

    /**
     * Test of checkMessageID method, of class Message.
     *
     * Test when message ID is more than 10 digits (invalid) for message two
     * with assertEquals.
     */
    @Test
    public void testCheckMessageIDM2_Invalid() {
        System.out.println("Message 2 message ID check: \nInvalid Message ID: ");
        String messageID = "08014040079";
        Message instance = new Message();
        boolean expResult = false;
        boolean result = instance.checkMessageID(messageID);
        assertEquals(expResult, result);

    }

    /**
     * Test of createMessageHash method, of class Message.
     *
     * Test if message hash is correct for message two with assertEquals.
     */
    @Test
    public void testCreateMessageHashM2() {
        String messageID = "0880140400";
        int messageNum = 0;
        String messageContent = "Hi Keegan, did you recieve the payment?";
        Message instance = new Message();
        String expResult = "08:0:HIPAYMENT?";
        System.out.println("Message 2 message hash: \n" + expResult);
        String result = instance.createMessageHash(messageID, messageNum, messageContent);
        assertEquals(expResult, result);

    }
}

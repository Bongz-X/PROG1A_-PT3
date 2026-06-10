package poe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MessageTest {

    //Testing Message Length
    @Test
    public void testMessageLengthValid(){

        Messages msg = new Messages (1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");

        boolean actualResult = msg.checkMessageText();
        boolean expectedResult = true;

        System.out.println("Test Message Length Valid: " + actualResult + ", Message ready to send.");
        assertEquals(expectedResult, actualResult);
    }

    //Testing Invalid Message Length
    @Test
    public void testMessageLengthInvalid(){

        String longMessage = "a".repeat(260);
        Messages msg = new Messages (1, "+27718693002", longMessage);

        boolean actualResult = msg.checkMessageText();
        boolean expectedResult = false;

        System.out.println("Test Message Length Invalid: " + actualResult + ", Message exceeds 250 characters by " + (longMessage.length() - 250) + " characters, please reduce the size.");
        assertEquals(expectedResult, actualResult);
    }

    //Recipient Number Test
    @Test
    public void testRecipientNumberValid(){

       Messages msg = new Messages (1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");

       boolean actualResult = msg.checkRecipientCell();
       boolean expectedResult = true;

       System.out.println("Recipient Number is Valid: " + actualResult + ", Cellphone number successfully captured.");
       assertEquals(expectedResult, actualResult);
    }

    //Invalid Recipient Number Test
    @Test
    public void testRecipientNumberInvalid(){

        Messages msg = new Messages (2, "08575975889", "Hi Keegan, did you receive the payment?");

        boolean actualResult = msg.checkRecipientCell();
        boolean expectedResult = false;

        System.out.println("Recipient Number is Invalid: " + actualResult + ", Cellphone number is incorrectly formatted or does not contain an international code.Please correct the number and try again.");
        assertEquals(expectedResult, actualResult);
    }

    //Message Hash Test case 1
    @Test
    public void testMessageHashCreated(){
        Messages msg = new Messages ("00ABCDEF31", 1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");

        String hash = msg.createMessageHash();
        String expectedResult = "00:HITONIGHT";

        System.out.println("Generated Hash test 1: " + hash);
        assertEquals(expectedResult, hash);
    }

    //Message Hash Test with Loops
    @Test
    public void testMultipleMessageHashCreated(){
       Messages [] messages = {
        new Messages ("00ABCDEF31", 1, "+27718693002", "Hi Mike, can you join us for dinner tonight?"),

        new Messages ("01GHIJKL42", 2, "08575975889", "Hi Keegan, did you receive the payment?")
       };

       for (Messages msg : messages){
        String hash = msg.createMessageHash();

        System.out.println("Generated Hash: " + hash);
        assertNotNull(hash);
       }
    }

    //MessageID test
    @Test
    public void testMessageIDGenerated(){
        Messages msg = new Messages (1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");

        String messageID = msg.getMessageID();

        System.out.println("Generated Message ID: " + messageID);
        assertNotNull(messageID);
        assertEquals(10, messageID.length());
    }

    //Message Sent Test (Send Message)
    @Test
    public void testSendMessage(){

        Messages msg = new Messages (1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");

        String actualResult = msg.SentMessage(1);
        String expectedResult = "Message successfully sent.";

        System.out.println("Message Sent Result: " + actualResult);
        assertEquals(expectedResult, actualResult);
    }

    //Message Sent Test (Discard Message)
    @Test
    public void testDiscardMessage(){

        Messages msg = new Messages (2, "08575975889", "Hi Keegan, did you receive the payment?");

        String actualResult = msg.SentMessage(2);
        String expectedResult = "Press 0 to delete message.";

        System.out.println("Message Discard Result: " + actualResult);
        assertEquals(expectedResult, actualResult);
    }

    //Message Sent Test (Store Message)
    @Test
    public void testStoreMessage(){

        Messages msg = new Messages (3, "+27718693002", "Hi Keegan, did you receive the payment?");

        String actualResult = msg.SentMessage(3);
        String expectedResult = "Message successfully stored.";

        System.out.println("Message Store Result: " + actualResult);
        assertEquals(expectedResult, actualResult);
    }
}
package poe;

import java.util.Scanner;
import java.util.ArrayList;
public class App
{

//Storing a userNumber for user who has logged into the system
    private static String currentUserNumber;

//sentMessages Array
    private static ArrayList<Messages> sentMessages = new ArrayList<>();

//disregardedMessages Array
    private static ArrayList<Messages> disregardedMessages = new ArrayList<>();

//main method
    public static void main( String[] args )
    {

    //Registration of User details
    
    //Scanner to allow user input
    try (Scanner input = new Scanner(System.in)) {
        Login user = null;

        //Menu to select registration or login
        int choice;
        do {
            System.out.println("---Menu---");
            System.out.println("Please select an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            choice= input.nextInt();
            input.nextLine();

            switch (choice){

                //Registration Phase
                case 1:

                //condition for registration loop
                boolean isValid;

                do {
                    System.out.println("Please enter your username (Contains _ and is a max of 5 characters long) ");
                    String username = input.nextLine();

                    System.out.println("Please enter your password (8 characters long, contains a capital letter, a number and a special character)");
                    String password = input.nextLine();

                    System.out.println("Please enter your SA cell phone number (Must start with +27 and be 10 characters long) ");
                    String cellPhone = input.nextLine();

                    user = new Login(username, password, cellPhone);

                    //Assigning methods from Login Class

                    boolean validUsername = user.CheckUsername();
                    boolean validPassword = user.CheckPassword();
                    boolean validCellPhone = user.CheckCellPhone();


                    //Showing registration results to user

                    //Username
                    if(validUsername){
                        System.out.println("Username successfully captured.");
                    } else {
                        System.out.println("Username is not correctly formatted; please ensure that it contains an underscore and is no more than five characters in length.");
                    }

                    //Password
                    if(validPassword){
                        System.out.println("Password successfully captured.");
                    } else {
                        System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
                    }

                    //Cell Phone
                    if(validCellPhone){
                        System.out.println("Cell phone number successfully captured.");
                    } else {
                        System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
                    }
                    //Check overall validation of registration details
                    isValid = validUsername && validPassword && validCellPhone;

                    if (!isValid){
                        System.out.println("Registration unsuccessful. Please check the requirements for each field and try again.");
                    }
                } while (!isValid);

                //Final registration result confirming details are correct and user is registered
                System.out.println("Above conditions have been met. User successfully registered.");
                break;


                //Login Phase

                //Letting user know they can't log in without registering first
                case 2:
                    if (user == null){
                        System.out.println("No user registered. Register first before attempting to login!");
                        break;
                    }

                //Prompt user to enter login details
                System.out.println("Please enter your username to login: ");
                String enteredUsername = input.nextLine();

                System.out.println("Please enter your password to login: ");
                String enteredPassword = input.nextLine();

                System.out.println("Please enter your cell phone number to login: ");
                String enteredCellPhone = input.nextLine();

                //Login feedback to user
                boolean loginSuccess = user.loginUser(enteredUsername, enteredPassword, enteredCellPhone);

                if (loginSuccess){

                //Assigning userNumber to CellPhone
                    currrentUserNumber = enteredCellPhone;

                    System.out.println("Welcome back to QuickChat.");

                //Displaying QuickChat Menu
                    showQuickChatMenu(input);
                }
  
                //Login unsuccessful message
                else {
                    System.out.println("Login unsuccessful. Please check your credentials and try again.");
                }
                break;

                //Exit message
                case 3:
                    System.out.println("Thank you for using the system. Goodbye!");
                    break;

                //Default case for invalid menu option
                default:
                    System.out.println("Invalid option. Please select 1, 2, or 3.");

            }

        } while (choice != 3);
      }
    }

            //QuickChat Menu method
                public static void showQuickChatMenu(Scanner input){

                //Looping QuickChat Menu
                int MessageOptions = 0;

                while (MessageOptions != 4) {
                    System.out.println("---QuickChat Menu---");
                    System.out.println("\nPlease select an option:");
                    System.out.println("1. Send a message");
                    System.out.println("2. Show recently sent messages");
                    System.out.println("3. Stored Messages");
                    System.out.println("4. Exit");

                    MessageOptions = input.nextInt();
                    input.nextLine();
                
                switch (MessageOptions) {
                    case 1:
                       sendMessages (input);
                       break;

                    case 2:
                        if (sentMessages.isEmpty()){
                            System.out.println("No messages have been sent.");
                        }
                        else {
                            for (Messages msg : sentMessages){
                                msg.printMessages();
                                System.out.println();
                            }
                        }
                        break;

                    case 3:
                        StoredMessagesMenu(input);
                        break;

                    case 4:
                        System.out.println("Exiting QuickChat.");
                        break;

                    default:
                        System.out.println("Invalid option. Please select 1, 2, or 3.");

                 }
                }
              }

                //Adding a Send Messages method
                public static void sendMessages(Scanner input){
                //Asking the user for message count
                    System.out.print("How many messages would you like to send? ");
                    int total = input.nextInt ();
                    input.nextLine();
                
                //Arrays for stored messages from JSON File
                ArrayList<Messages> storedMessages = MessageStore.loadMessages();

                for (int m = 1; m <= total; m++){
                    System.out.println("\n---Message " + m + "---");
                    System.out.print("Enter the recipient's number: ");
                    String recipient = input.nextLine();

                    System.out.print("Enter the message text: ");
                    String messageText = input.nextLine();

                    Messages msg = new Messages(m, recipient, messageText);

                //Recipient Validation
                    if (!msg.checkRecipientCell()) {
                        System.out.println("Cell phone number is incorrectly formatted or does not contain an international code.");
                        continue;
                    }

                //Ensure message length is validated first before being counted
                    if (!msg.checkMessageText()){
                        System.out.println("Please enter a message of less than 250 characters.");
                        continue;
                    } else {
                        System.out.println("Message is valid.");
                    }
                //Messaging Menu Options
                    System.out.println("\nChoose an option:");
                    System.out.println("1. Send Message");
                    System.out.println("2. Discard Message");
                    System.out.println("3. Store Message");

                //Allowing user to select option for Message menu
                    int messageOption = input.nextInt();
                    input.nextLine();
                
                //Displaying Menu option results
                    switch (messageOption) {
                        case 1:
                        sentMessages.add(msg);
                        System.out.println(msg.SentMessage(1));
                        msg.printMessages();
                        break;

                        case 2:
                            System.out.println("Press 0 to delete message.");
                            int confirm = input.nextInt();
                            input.nextLine();
                            if (confirm == 0) {
                                disregardedMessages.add(msg);
                                System.out.println("Message deleted.");
                            } else{
                                System.out.println("Message is not discarded.");
                            }
                            break;

                        case 3:
                            storedMessages.add(msg);
                            MessageStore.saveMessages(storedMessages);
                            System.out.println(msg.SentMessage(3));
                            break;

                        default:
                            System.out.println("Invalid option. Please select 1, 2, or 3.");
                        }
                    }

                    //Displaying total messages processed & created at the end of the loop
                    System.out.println("\nTotal messages sent: " + Messages.returnTotalMessages());
                }
            }

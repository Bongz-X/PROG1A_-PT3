package poe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MessageStore{
    
    //Declarations for MessageStore
    private static final String FILE_NAME = "messages.json";
    private static Gson gson = new Gson();
    
    //Method to save messages to a JSON file
    public static void saveMessages(ArrayList<Messages> messages) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(messages, writer);
        } catch (IOException e) {
            System.out.println("An error occurred while saving messages.");
        }
    }
    //Method to load messages from a JSON file
    public static ArrayList<Messages> loadMessages() {
        try (FileReader reader = new FileReader(FILE_NAME)) {

            Type messageListType = new TypeToken<ArrayList<Messages>>() {}.getType();
            //Converting Json to ArrayList of Messages
            ArrayList<Messages> messages = gson.fromJson(reader, messageListType);

            //If messages is null (e.g., file is empty), return an empty list instead
            return messages != null ? messages : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
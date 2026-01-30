import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * A class capable of storing multiple line of texts and display them as chatbot's messages
 */
public class Message {
    protected static final String LINING =
            "____________________________________________________________";
    protected List<String> messages;

    /**
     * Default constructor
     */
    Message() {
        this.messages = new ArrayList<String>();
    }

    /**
     * Instantiate a Message with a single line of text
     * @param message The message to be stored in the object
     */
    Message(String message) {
        // Preprocess the message
        message = StringTrimmer.trim(message);

        this.messages = new ArrayList<String>();
        this.messages.add(message);
    }

    /**
     * Instantiate a Message with a multiple lines of text
     * @param messages The messages to be stored in the object
     */
    Message(List<String> messages) {
        this.messages = messages;
    }

    /**
     * Displays the message
     */
    public void display() {
        System.out.println(LINING);
        for (String s : messages) {
            System.out.println(s);
        }
        System.out.println(LINING);
    }

    /**
     * Displays the message from another Message
     */
    public static void display(Message message) {
        message.display();
    }

    /**
     * Displays a string as a message
     */
    public static void display(String string) {
        Message message = new Message(string);
        message.display();
    }

    /**
     * Gets message from the user
     * @param sc Scanner from the chatbot
     * @return Message inputted by the user
     */
    public static UnitMessage getMessage(Scanner sc) {
        String message = sc.nextLine();
        return new UnitMessage(message);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return this.messages.equals(message.messages);
    }
}

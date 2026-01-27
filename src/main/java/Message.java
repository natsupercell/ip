import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Message {
    protected static final String lining =
            "____________________________________________________________";
    protected List<String> message;

    Message() {
        this.message = new ArrayList<String>();
    }

    Message(String message) {
        this.message = new ArrayList<String>();
        this.message.add(message);
    }

    Message(List<String> message) {
        this.message = message;
    }

    public void display() {
        System.out.println(lining);
        for (String s : message) {
            System.out.println(s);
        }
        System.out.println(lining);
    }

    public static void display(Message message) {
        message.display();
    }

    public static void display(String string) {
        Message message = new Message(string);
        message.display();
    }

    public static UnitMessage getMessage(Scanner sc) {
        String message = sc.nextLine();
        return new UnitMessage(message);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return this.message.equals(message.message);
    }
}

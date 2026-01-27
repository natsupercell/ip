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

    public void Display() {
        System.out.println(lining);
        for (String s : message) {
            System.out.println(s);
        }
        System.out.println(lining);
    }

    public static void Display(Message message) {
        message.Display();
    }

    public static void Display(String string) {
        Message message = new Message(string);
        message.Display();
    }

    public static UnitMessage GetMessage(Scanner sc) {
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

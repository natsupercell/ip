import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Message {
    private static final String lining =
            "____________________________________________________________";
    private final List<String> message;

    Message(String message) {
        this.message = new ArrayList<String>();
        this.message.add(message);
    }

    Message(List<String> message) {
        this.message = message;
    }

    public void Display() {
        System.out.println(lining);
        for (int i = 0; i < message.size(); i++) {
            System.out.println(message.get(i));
        }
        System.out.println(lining);
    }

    public static void Display(Message message) {
        message.Display();
    }

    public static Message GetMessage(Scanner sc) {
        String message = sc.nextLine();
        return new Message(message);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return this.message.equals(message.message);
    }
}

public class MessageContainer extends Message {
    public void add(String message) {
        this.message.add(message);
    }

    public void add(UnitMessage message) {
        this.message.add(message.get());
        message.displayAddMessage();
    }

    @Override
    public void display() {
        System.out.println(lining);
        for (int i = 0; i < message.size(); i++) {
            System.out.printf("%d. %s\n", i+1, message.get(i));
        }
        System.out.println(lining);
    }

    public static void display(MessageContainer mc) {
        mc.display();
    }
}

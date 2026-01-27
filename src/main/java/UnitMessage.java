public class UnitMessage extends Message {
    UnitMessage(String message) {
        super(message);
    }

    public String toString() {
        return message.get(0);
    }
}

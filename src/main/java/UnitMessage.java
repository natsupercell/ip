/**
 * An extension of Message class, capable of storing exactly one message
 */
public class UnitMessage extends Message {
    /**
     * Instantiates an UnitMessage
     * @param message The message to be stored in the object
     */
    UnitMessage(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return messages.get(0);
    }
}

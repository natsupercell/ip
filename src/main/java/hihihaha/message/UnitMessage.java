package hihihaha.message;

/**
 * An extension of Message class, capable of storing exactly one message.
 */
public class UnitMessage extends Message {
    /**
     * Instantiates an UnitMessage.
     * 
     * @param message
     *            The message to be stored in the object.
     */
    public UnitMessage(String message) {
        super(message);
    }

    @Override
    public String toString() {
        assert messages.size() == 1 : "UnitMessage cannot contain more than 1 string";
        return messages.get(0);
    }
}

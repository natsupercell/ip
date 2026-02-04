package hihihaha.message;

import hihihaha.StringTrimmer;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Task to be done within a period of time
 */
public class Event extends Task {
    private static final char SIGNATURE = 'E';
    private LocalDateTime from;
    private LocalDateTime to;

    Event(String task, LocalDateTime from, LocalDateTime to) {
        super(task);
        this.taskType = SIGNATURE;
        this.from = from;
        this.to = to;
    }

    /**
     * Produce an Event based on the user input
     * 
     * @param string
     *            Message from user to be processed
     * @return An Event based on the user input
     * @throws IllegalArgumentException
     *             Throw exception when the input string is invalid
     */
    public static Event produce(String string) throws IllegalArgumentException {
        String attributeName1 = "from";
        String attributeName2 = "to";
        List<TaskAttribute> list = TaskAttribute.split(string);
        if (list.size() != 3 || !list.get(1).getAttributeName().equals(attributeName1)
                || !list.get(2).getAttributeName().equals(attributeName2)) {
            throw new IllegalArgumentException();
        }
        String task = StringTrimmer.trim(list.get(0).getDetail());
        String fromString = StringTrimmer.trim(list.get(1).getDetail());
        String toString = StringTrimmer.trim(list.get(2).getDetail());

        try {
            LocalDateTime from = LocalDateTime.parse(fromString, Task.READ_FORMAT);
            LocalDateTime to = LocalDateTime.parse(toString, Task.READ_FORMAT);
            return new Event(task, from, to);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String taskToData() {
        return "" + taskType + " || " + isDone + " || " + super.toString() + " || " + from.format(READ_FORMAT) + " || "
                + to.format(READ_FORMAT);
    }

    /**
     * Merge the details of task into string representation
     */
    @Override
    public String toString() {
        String text = String.format("%s (from: %s to: %s)", super.toString(), from.format(WRITE_FORMAT),
                to.format(WRITE_FORMAT));
        return String.format("[%c][%c] %s", this.taskType, super.checker(), text);
    }
}

package hihihaha.message;

import hihihaha.StringTrimmer;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * A task that happens during a time period (represented as a date range).
 */
public class Event extends Task {
    private static final char SIGNATURE = 'E';
    private final LocalDate from;
    private final LocalDate to;

    Event(String task, LocalDate from, LocalDate to) {
        super(task);
        this.taskType = SIGNATURE;

        if (from.isAfter(to)) {
            throw new IllegalArgumentException();
        }
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an {@link Event} from the user's input.
     *
     * @param string
     *            The string after the {@code event} command.
     * @return A new {@link Event}.
     * @throws IllegalArgumentException
     *             If the format is invalid or dates cannot be parsed.
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

        if (task.isBlank() || fromString.isBlank() || toString.isBlank()) {
            throw new IllegalArgumentException();
        }

        try {
            LocalDate from = LocalDate.parse(fromString, Task.READ_FORMAT);
            LocalDate to = LocalDate.parse(toString, Task.READ_FORMAT);
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

    @Override
    public String toString() {
        String text = String.format("%s (from: %s to: %s)", super.toString(), from.format(WRITE_FORMAT),
                to.format(WRITE_FORMAT));
        return String.format("[%c][%c] %s", this.taskType, super.checker(), text);
    }
}
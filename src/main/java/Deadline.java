import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Task with a deadline
 */
public class Deadline extends Task {
    private static final char SIGNATURE = 'D';
    private LocalDateTime by;

    Deadline(String task, LocalDateTime by) {
        super(task);
        this.taskType = SIGNATURE;
        this.by = by;
    }

    /**
     * Produce a Deadline based on the user input
     * @param string Message from user to be processed
     * @return A Deadline based on the user input
     * @throws IllegalArgumentException Throw exception when the input string is invalid
     */
    public static Deadline produce(String string) throws IllegalArgumentException {
        String attributeName1 = "by";
        List<TaskAttribute> list = TaskAttribute.split(string);
        if (list.size() != 2
                || !list.get(1).getAttributeName().equals(attributeName1)) {
            throw new IllegalArgumentException();
        }
        String task = StringTrimmer.trim(list.get(0).getDetail());
        String byString = StringTrimmer.trim(list.get(1).getDetail());

        try {
            LocalDateTime by = LocalDateTime.parse(byString, Task.READ_FORMAT);
            return new Deadline(task, by);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String taskToData() {
        return ""
                + taskType + " || "
                + isDone + " || "
                + super.toString() + " || "
                + by.format(READ_FORMAT);
    }

    /**
     * Merge the details of task into string representation
     */
    @Override
    public String toString() {
        String text = String.format(
                "%s (by: %s)", super.toString(),
                by.format(WRITE_FORMAT)
        );
        return String.format("[%c][%c] %s", this.taskType, super.checker(), text);
    }
}

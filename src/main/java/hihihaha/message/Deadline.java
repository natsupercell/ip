package hihihaha.message;

import java.util.List;

/**
 * Task with a deadline
 */
public class Deadline extends Task {
    private static final char SIGNATURE = 'D';

    Deadline(String task) {
        super(task);
        this.taskType = SIGNATURE;
    }

    /**
     * Produce a Deadline based on the user input
     * @param string Message from user to be processed
     * @return A Deadline based on the user input
     * @throws IllegalArgumentException Throw exception when the input string is invalid
     */
    public static Deadline produce(String string) throws IllegalArgumentException {
        if (string == null) throw new IllegalArgumentException();
        String attributeName1 = "by";
        List<TaskAttribute> list = TaskAttribute.split(string);
        if (list.size() != 2) throw new IllegalArgumentException();
        if (!list.get(1).getAttributeName().equals(attributeName1)) throw new IllegalArgumentException();
        String task = list.get(0).getDetail();
        String by = list.get(1).getDetail();
        return Deadline.merge(task, by);
    }

    /**
     * Merge the detail of task into task description
     * @param task Task description
     * @param by Deadline of the task
     */
    public static Deadline merge(String task, String by) {
        return new Deadline(String.format(
                "%s(by:%s)", task, by
        ));
    }
}

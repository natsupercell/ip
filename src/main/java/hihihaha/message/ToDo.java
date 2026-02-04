package hihihaha.message;

import hihihaha.StringTrimmer;
import java.util.List;

/**
 * Task to be done
 */
public class ToDo extends Task {
    private static final char SIGNATURE = 'T';

    /**
     * @param task
     *            Task description
     */
    ToDo(String task) {
        super(task);
        this.taskType = SIGNATURE;
    }

    /**
     * Produce a ToDo based on the user input
     * 
     * @param string
     *            Message from user to be processed
     * @return A ToDo based on the user input
     * @throws IllegalArgumentException
     *             Throw exception when the input string is invalid
     */
    public static ToDo produce(String string) throws IllegalArgumentException {
        List<TaskAttribute> list = TaskAttribute.split(string);
        if (list.size() != 1)
            throw new IllegalArgumentException();
        String task = StringTrimmer.trim(list.get(0).getDetail());
        return new ToDo(task);
    }

    @Override
    public String taskToData() {
        return "" + taskType + " || " + isDone + " || " + super.toString();
    }

    /**
     * Merge the details of task into string representation (oh this one no need to
     * merge)
     */
    @Override
    public String toString() {
        return String.format("[%c][%c] %s", this.taskType, super.checker(), super.toString());
    }
}

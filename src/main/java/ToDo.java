import java.util.List;

/**
 * Task to be done
 */
public class ToDo extends Task {
    private static final char SIGNATURE = 'T';

    /**
     * @param task Task description
     */
    ToDo(String task) {
        super(task);
        this.taskType = SIGNATURE;
    }

    /**
     * Produce a ToDo based on the user input
     * @param string Message from user to be processed
     * @return A ToDo based on the user input
     * @throws IllegalArgumentException Throw exception when the input string is invalid
     */
    public static ToDo produce(String string) throws IllegalArgumentException {
        if (string == null) throw new IllegalArgumentException();
        List<TaskAttribute> list = TaskAttribute.split(string);
        if (list.size() != 1) throw new IllegalArgumentException();
        String task = list.get(0).getDetail();
        return new ToDo(task);
    }
}

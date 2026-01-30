package hihihaha.message;

import java.util.List;

/**
 * Hihihaha.Task to be done within a period of time
 */
public class Event extends Task {
    private static final char SIGNATURE = 'E';


    Event(String task) {
        super(task);
        this.taskType = SIGNATURE;
    }

    /**
     * Produce an Hihihaha.Event based on the user input
     * @param string Hihihaha.Message from user to be processed
     * @return An Hihihaha.Event based on the user input
     * @throws IllegalArgumentException Throw exception when the input string is invalid
     */
    public static Event produce(String string) throws IllegalArgumentException {
        if (string == null) throw new IllegalArgumentException();
        String attributeName1 = "from";
        String attributeName2 = "to";
        List<TaskAttribute> list = TaskAttribute.split(string);
        if (list.size() != 3) throw new IllegalArgumentException();
        if (!list.get(1).getAttributeName().equals(attributeName1))
            throw new IllegalArgumentException();
        if (!list.get(2).getAttributeName().equals(attributeName2))
            throw new IllegalArgumentException();
        String task = list.get(0).getDetail();
        String from = list.get(1).getDetail();
        String to = list.get(2).getDetail();
        return Event.merge(task, from, to);
    }

    /**
     * Merge the detail of task into task description
     * @param task Hihihaha.Task description
     * @param from Starting time of the task
     * @param to Finishing time of the task
     */
    public static Event merge(String task, String from, String to) {
        return new Event(String.format(
                "%s(from:%sto:%s)", task, from, to
        ));
    }
}

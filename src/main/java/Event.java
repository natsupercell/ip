import java.util.List;

public class Event extends Task {
    private String from;
    private String to;
    private static char signature = 'E';

    Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
        this.taskType = signature;
    }

    public static Event produce(String string) {
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
        return new Event(task, from, to);
    }
}

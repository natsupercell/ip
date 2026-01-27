import java.util.List;

public class Deadline extends Task {
    private String by;
    private static char signature = 'D';

    Deadline(String task, String by) {
        super(task);
        this.by = by;
        this.taskType = signature;
    }

    public static Deadline produce(String string) {
        String attributeName1 = "by";
        List<TaskAttribute> list = TaskAttribute.split(string);
        if (list.size() != 2) throw new IllegalArgumentException();
        if (!list.get(1).getAttributeName().equals(attributeName1)) throw new IllegalArgumentException();
        String task = list.get(0).getDetail();
        String by = list.get(1).getDetail();
        return new Deadline(task, by);
    }
}

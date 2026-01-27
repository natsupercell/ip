import java.util.List;

public class ToDo extends Task {
    private static char signature = 'T';

    ToDo(String task) {
        super(task);
        this.taskType = signature;
    }

    public static ToDo produce(String string) {
        List<TaskAttribute> list = TaskAttribute.split(string);
        if (list.size() != 1) throw new IllegalArgumentException();
        String task = list.get(0).getDetail();
        return new ToDo(task);
    }
}

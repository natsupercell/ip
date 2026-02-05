package hihihaha.message;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskAttributeTest {
    @Test
    public void split_sampleInput_expectedReturn() {
        List<TaskAttribute> output = TaskAttribute.split("wae /by idk /to haha /from hihi");
        List<TaskAttribute> expected = List.of(
                new TaskAttribute("task", "wae"),
                new TaskAttribute("by", "idk"),
                new TaskAttribute("to", "haha"),
                new TaskAttribute("from", "hihi")
        );
        for (int i = 0; i < output.size(); i++) {
            // System.out.println(output.get(i).getAttributeName() + " " + output.get(i).getDetail());
            // System.out.println(expected.get(i).getAttributeName() + " " + expected.get(i).getDetail());
            assertEquals(output.get(i), expected.get(i), "" + i);
        }
    }
}

package hihihaha.message;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskAttributeTest {

    @Test
    public void split_nullInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> TaskAttribute.split(null));
    }

    @Test
    public void split_todoOnly_singleDefaultAttributeReturned() {
        List<TaskAttribute> attrs = TaskAttribute.split("read book");
        assertEquals(1, attrs.size());
        assertEquals("task", attrs.get(0).getAttributeName());
        assertEquals("read book", attrs.get(0).getDetail());
    }

    @Test
    public void split_deadlineBy_twoAttributesReturned() {
        List<TaskAttribute> attrs = TaskAttribute.split("hihihaha /by 20-11-2022");
        assertEquals(2, attrs.size());
        assertEquals("task", attrs.get(0).getAttributeName());
        assertEquals("hihihaha", attrs.get(0).getDetail());
        assertEquals("by", attrs.get(1).getAttributeName());
        assertEquals("20-11-2022", attrs.get(1).getDetail());
    }

    @Test
    public void split_missingAttributeName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> TaskAttribute.split("task / 20-11-2022"));
    }
}

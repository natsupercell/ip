import java.util.List;
import java.util.ArrayList;

public class TaskAttribute {
    private static String defaultAttributeName = "task";
    private String attributeName;
    private String detail;

    TaskAttribute(String attributeName, String detail) {
        this.attributeName = attributeName;
        this.detail = detail;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getDetail() {
        return detail;
    }

    public static List<TaskAttribute> split(String string) {
        List<TaskAttribute> out = new ArrayList<TaskAttribute>();
        String attributeName = defaultAttributeName;
        String detail = "";
        for (int i = 0; i <= string.length(); i++) {
            if (i == string.length() || string.charAt(i) == '/') {
                out.add(new TaskAttribute(attributeName, detail));
                if (i != string.length()) {
                    i++;
                    attributeName = "";
                    for (; i < string.length(); i++) {
                        if (string.charAt(i) == ' ') {
                            i++;
                            break;
                        } else {
                            attributeName += string.charAt(i);
                        }
                    }
                    if (i == string.length()) throw new IllegalArgumentException();
                    detail = "";
                }
                continue;
            }
            detail += string.charAt(i);
        }
        return out;
    }
}

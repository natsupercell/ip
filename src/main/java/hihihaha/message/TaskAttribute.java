package hihihaha.message;

import hihihaha.StringTrimmer;

import java.util.List;
import java.util.ArrayList;

/**
 * Capable of storing important information of a task
 */
public class TaskAttribute {
    private static String defaultAttributeName = "task";
    private String attributeName; // name of the attribute type
    private String detail; // detail of the attribute

    /**
     * @param attributeName
     *            Name of the attribute type
     * @param detail
     *            Detail of the attribute
     */
    TaskAttribute(String attributeName, String detail) {
        this.attributeName = StringTrimmer.trim(attributeName); // handle exceptions from raw input
        this.detail = StringTrimmer.trim(detail);
    }

    /**
     * @return The name of the attribute type
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * @return Detail of the attribute
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Split the input string (from the user) into list of task attributes
     * 
     * @param string
     *            Input string from the user
     */
    public static List<TaskAttribute> split(String string) throws IllegalArgumentException {
        if (string == null) {
            throw new IllegalArgumentException();
        }
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
                            i--;
                            break;
                        } else {
                            attributeName += string.charAt(i);
                        }
                    }
                    if (i == string.length())
                        throw new IllegalArgumentException();
                    detail = "";
                }
                continue;
            }
            detail += string.charAt(i);
        }
        return out;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;
        TaskAttribute t = (TaskAttribute) o;
        return (this.attributeName.equals(t.attributeName)) && (this.detail.equals(t.detail));
    }
}

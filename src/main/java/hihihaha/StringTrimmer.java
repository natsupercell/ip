package hihihaha;

/**
 * Utility class, used to trim off excess whitespace of a string
 */
public class StringTrimmer {
    public static String trim(String s) {
        int l = s.length(); // begin of new string
        int r = 0; // end of new string
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                l = i;
                break;
            }
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                r = i + 1;
                break;
            }
        }
        return s.substring(l, r);
    }
}

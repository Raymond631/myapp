package cn.edu.csu.ycepspring.common.utils;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
    public static String substring(final String str, int start, int end) {
        if (str == null) {
            return "";
        }

        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return "";
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }
}

package com.nengjun.avatar.utils.lang;

/**
 * Created by Henry on 2017/7/14.
 */
public class StringUtil {
    public static String camelhumpToUnderline(String str) {
        if (str == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (isUppercaseAlpha(c)) {
                sb.append("_");
            }
            sb.append(c);
        }
        return sb.charAt(0) == '_' ? sb.substring(1) : sb.toString();
    }

    private static boolean isUppercaseAlpha(char c) {
        return (c >= 'A') && (c <= 'Z');
    }
}

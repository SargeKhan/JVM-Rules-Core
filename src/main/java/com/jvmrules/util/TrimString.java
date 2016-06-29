package com.jvmrules.util;


public class TrimString {
    public static String trim(String value) {

        if (!value.isEmpty()) {
            char[] chars = value.toCharArray();
            if (chars.length > 2) {
                if (chars[0] == '\'' && chars[chars.length - 1] == '\'') {
                    if (value.length() >= 1) {
                        value = value.substring(1, value.length() - 1);
                    }
                }
            }

        }
        return value;
    }
}

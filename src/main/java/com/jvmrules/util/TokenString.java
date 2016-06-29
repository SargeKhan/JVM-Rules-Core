package com.jvmrules.util;


public class TokenString {

    public static String tokenToString(String[] tokens, int pos) {
        StringBuilder stringBuilder = new StringBuilder("");

        for (int i = 0; i < pos; i++) {
            stringBuilder.append(tokens[i] + " ");
        }
        return stringBuilder.toString().trim();
    }

}

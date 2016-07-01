package com.jvmrules.util;

import com.jvmrules.expression.veriable.*;


public class ValueDataType {
    public static Class computeType(String value) {
        if ("true".equals(value) || "false".equals(value)) {
            return BooleanVeriable.class;
        } else if (value.startsWith("'")) {
            return StringVeriable.class;
        } else if (value.startsWith("[R") && value.endsWith("]")) {
            return RegexVeriable.class;
        }else if (value.startsWith("[") && value.endsWith("]")) {

            String date="\\[\\s*(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[0-2])-([1-9][0-9][0-9][0-9])\\s*\\]";
            String dateTime="\\[\\s*(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[0-2])-([1-9][0-9][0-9][0-9])\\s{1}(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])\\s*\\]";
            if (value.matches(dateTime))
            {
                return DateTimeVeriable.class;
            }else if (value.matches(date)){
                return DateVeriable.class;
            }else{
                return StringVeriable.class;
            }

        } else if (value.contains(".")) {
            try {
                return FloatVeriable.class;
            } catch (NumberFormatException e) {
            }
        } else {
            try {
                return IntegerVeriable.class;
            } catch (NumberFormatException e) {
            }
        }
        return StringVeriable.class;
    }
}

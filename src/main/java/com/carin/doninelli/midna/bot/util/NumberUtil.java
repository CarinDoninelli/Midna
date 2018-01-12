package com.carin.doninelli.midna.bot.util;

import java.util.regex.Pattern;

public enum NumberUtil {
    ;

    private static final Pattern pattern = Pattern.compile("\\d+");

    public static boolean isInt(String string) {
        return string != null &&
               pattern.matcher(string).matches();
    }
}

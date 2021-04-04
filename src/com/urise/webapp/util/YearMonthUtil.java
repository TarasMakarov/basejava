package com.urise.webapp.util;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class YearMonthUtil {
    public static final YearMonth NOW = YearMonth.of(3000, 1);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    public static String formatDate(YearMonth date) {
        if (date == null)
            return "";
        return date.equals(NOW) ? "По настоящее время" : date.format(DATE_TIME_FORMATTER);
    }
}
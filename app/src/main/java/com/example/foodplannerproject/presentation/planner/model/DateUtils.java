package com.example.foodplannerproject.presentation.planner.model;

import java.util.Calendar;
import java.util.Locale;

public class DateUtils {

    public static String getStartOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return format(cal);
    }

    public static String getEndOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.add(Calendar.DAY_OF_MONTH, 6);
        return format(cal);
    }

    private static String format(Calendar cal) {
        return String.format(
                Locale.US,
                "%04d-%02d-%02d",
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DAY_OF_MONTH)
        );
    }
}

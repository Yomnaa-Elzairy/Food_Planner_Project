package com.example.foodplannerproject.presentation.planner.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final SimpleDateFormat DB_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    private static final SimpleDateFormat UI_FORMAT =
            new SimpleDateFormat("EEEE, MMM d", Locale.getDefault());

    // e.g. 2026-02-10
    public static String getToday() {
        return DB_FORMAT.format(new Date());
    }

    public static String getStartOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        return DB_FORMAT.format(cal.getTime());
    }

    public static String getEndOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return DB_FORMAT.format(cal.getTime());
    }

    // Convert "2026-02-10" → "Monday, Feb 10"
    public static String formatForUI(String dbDate) {
        try {
            Date date = DB_FORMAT.parse(dbDate);
            return UI_FORMAT.format(date);
        } catch (ParseException e) {
            return dbDate;
        }
    }
}


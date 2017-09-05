package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by Ruslan on 05.09.2017.
 */
public class LocalTime implements Formatter<java.time.LocalTime> {
    @Override
    public java.time.LocalTime parse(String s, Locale locale) throws ParseException {
        return java.time.LocalTime.parse(s, DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String print(java.time.LocalTime localTime, Locale locale) {
        return localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}

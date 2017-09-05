package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by Ruslan on 05.09.2017.
 */

public class LocalDate implements Formatter<java.time.LocalDate> {
    @Override
    public java.time.LocalDate parse(String s, Locale locale) throws ParseException {
        return java.time.LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String print(java.time.LocalDate localDate, Locale locale) {
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}

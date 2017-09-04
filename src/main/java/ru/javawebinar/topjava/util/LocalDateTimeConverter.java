package ru.javawebinar.topjava.util;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Ruslan on 05.09.2017.
 */
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public LocalDateTime convert(String source) {
        return LocalDateTime.from(formatter.parse(source));
    }
}

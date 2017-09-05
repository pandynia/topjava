package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by Ruslan on 06.09.2017.
 */
@Component(value = "applicationConversionService")
public class FormattingFactory extends FormattingConversionServiceFactoryBean {

    public void installFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldType(java.time.LocalDate.class,
                new LocalDate());
        registry.addFormatterForFieldType(java.time.LocalTime.class,
                new LocalTime());
    }



}

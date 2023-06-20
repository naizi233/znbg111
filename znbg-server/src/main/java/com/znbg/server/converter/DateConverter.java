package com.yzn.server.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @description: 全局日期格式转换
 */
@Component
public class DateConverter implements Converter<String,LocalDate>{

    @Override
    public LocalDate convert(String source) {
        LocalDate d=null;
        try {
            //return  LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
            d=LocalDate.parse(source,formatter);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }
}
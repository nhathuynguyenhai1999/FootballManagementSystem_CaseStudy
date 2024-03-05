package com.cg.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ParseUtils {
    public static LocalDate parseDate(String date){
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException dateTimeParseException) {
            dateTimeParseException.printStackTrace();
        }
        return null;
    }
}

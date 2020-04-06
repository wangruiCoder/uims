package org.uims.tools.splittxt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SplitUtil {
    private static final String DATE_PATTERN = "yyyyMMdd";

    public static String nowDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate now = LocalDate.now();
        String dateStr = now.format(formatter);

        return dateStr;
    }
}

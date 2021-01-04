package com.produtos.apirest.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;

public class DateUtil {

    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    public static String format(LocalDate date){
        return format(date, DD_MM_YYYY);
    }

    public static String format(LocalDate date, String pattern){

        if (Objects.isNull(date)){
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(date);
    }
}